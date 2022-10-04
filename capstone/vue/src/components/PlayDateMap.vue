<template>
  <div id="main-container">
    <h1>Play Dates Near You</h1>

    <div id="map-controls">
      <div id="set-location">
        <p id="search-form-label" v-if="!couldNotFindAddress">
          <strong>Search Location:</strong>
        </p>
        <p id="address-error" v-if="couldNotFindAddress">
          <strong>ADDRESS NOT FOUND</strong>
        </p>
        <form id="search-form">
          <label for="search-address">Address: </label>
          <input
            type="text"
            name="search-address"
            id="search-address"
            placeholder="Optional"
          />
          <label for="search-city">City: </label>
          <input
            type="text"
            name="search-city"
            id="search-city"
            placeholder="Los Angeles"
          />
          <label for="search-state">State: </label>
          <input
            type="text"
            name="search-state"
            id="search-state"
            placeholder="CA"
            @input="truncateState"
          />
          <button id="search-map-btn" @click.prevent="setSearchAddress">Search</button>
        </form>
      </div>

      <div id="slide-container">
        <label for="radius" id="radius-label"
          ><strong>Search Radius:</strong></label
        >
        <p>
          <input
            type="number"
            min="1"
            :max="maxRadius"
            value="25"
            v-model="searchRadius"
            id="radius-box"
            @input="truncateRadius"
          />
          mile<span id="s-for-mile" v-if="searchRadius != 1">s</span>
        </p>
        <input
          type="range"
          min="1"
          :max="maxRadius"
          value="25"
          id="radius"
          name="radius"
          v-model="searchRadius"
        />
      </div>
    </div>

    <div id="map-container">
      <!-- documentation for Gmap: https://www.npmjs.com/package/vue2-google-maps -->
      <GmapMap
        id="map"
        :center="{ lat: startingLat, lng: startingLong }"
        :zoom="10"
        map-type-id="roadmap"
        @click="setSelectedId(0)"
      >
        <GmapMarker
          v-for="location in filteredLocations"
          :key="location.locationId"
          :position="{
            lat: parseFloat(location.latitude),
            lng: parseFloat(location.longitude),
          }"
          :clickable="true"
          @click="setSelectedId(location.locationId)"
          :title="location.locationName"
        />
        <GmapMarker
          :position="{
            lat: this.startingLat,
            lng: this.startingLong,
          }"
          :title="'Your Location'"
          :icon="'http://maps.google.com/mapfiles/kml/pal4/icon47.png'"
          @click="setSelectedId(0)"
        />
      </GmapMap>
      <div id="playdate-list">
        <div
          class="playdate-card"
          v-for="location in filteredLocations"
          :key="location.id"
          :class="{ 'selected-card': selectedId === location.locationId }"
        >
          <div class="address">
            <h3>
              {{ location.locationName }}
            </h3>
            <h4>
              {{ location.address1 }}
            </h4>
          </div>
          <div class="distance">
            <h4>Distance: {{ location.distance }} mi.</h4>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import mapService from "../services/MapService";
import geocodeService from "../services/GeocodeService";

export default {
  data() {
    return {
      startingLat: 40.73061,
      startingLong: -73.935242,
      searchRadius: 25,
      maxRadius: 150,
      selectedId: 0,
      couldNotFindAddress: false,
      playDates: [],
      locations: [],
    };
  },
  computed: {
    filteredLocations() {
      //filter out past playdates
      const filteredPlayDates = this.playDates.filter(playDate => {
        return Date.now() < Date.parse(playDate.playDateTimeStamp);
      });
      //create list of location ids for future playdates
      const playDateLocationIds = filteredPlayDates.map(playDate => {
        return playDate.locationId;
      });
      //create list of locations that correspond with a future playdate
      const locations = this.locations.filter(location => {
        return playDateLocationIds.includes(location.locationId);
      });
      //add distance property to each location
      locations.forEach((location) => {
        location.distance = this.getLocationDistance(location).toFixed(2);
      });
      //filter out locations based on search radius
      const filteredList = locations.filter((location) => {
        return this.isLocationWithinRadius(location);
      });
      //sort locations by distance
      filteredList.sort(this.compareDistances);
      return filteredList;
    },
  },
  methods: {
    setSearchAddress() {
      let searchAddress =
        document.getElementById("search-address").value.trim() +
        ", " +
        document.getElementById("search-city").value.trim() +
        ", " +
        document.getElementById("search-state").value.trim();
      searchAddress = searchAddress.replaceAll(" ", "+");
      geocodeService
        .getCoords(searchAddress)
        .then((response) => {
          this.startingLat = response.data.results[0].geometry.location.lat;
          this.startingLong = response.data.results[0].geometry.location.lng;
          this.couldNotFindAddress = false;
        })
        .catch(() => {
          this.couldNotFindAddress = true;
        });
    },
    //prevent user from entering more than 2 characters in the state input
    truncateState() {
      if (document.getElementById("search-state").value.length > 2) {
        document.getElementById("search-state").value = document
          .getElementById("search-state")
          .value.slice(0, 2);
      }
    },
    truncateRadius() {
      if (this.searchRadius > this.maxRadius) {
        this.searchRadius = this.maxRadius;
      }
    },
    compareDistances(location1, location2) {
      return location1.distance - location2.distance;
    },
    getLocationDistance(location) {
      const lat = parseFloat(location.latitude);
      const lng = parseFloat(location.longitude);
      return this.getDistanceFromLatLonInMi(
        lat,
        lng,
        this.startingLat,
        this.startingLong
      );
    },
    isLocationWithinRadius(location) {
      return this.getLocationDistance(location) <= this.searchRadius;
    },
    getDistanceFromLatLonInMi(lat1, lon1, lat2, lon2) {
      //Haversine formula for calculating the distance between 2 points on a sphere
      const R = 3958.8; // Radius of the earth in mi
      const dLat = this.deg2rad(lat2 - lat1); // deg2rad below
      const dLon = this.deg2rad(lon2 - lon1);
      const a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(this.deg2rad(lat1)) *
          Math.cos(this.deg2rad(lat2)) *
          Math.sin(dLon / 2) *
          Math.sin(dLon / 2);
      const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
      // Due to trig identities, this is equivalent to const c = 2 * Math.asin(Math.sqrt(a));
      // Using atan2 is less ambiguous
      const d = R * c; // Distance in mi
      return d;
    },
    deg2rad(deg) {
      return deg * (Math.PI / 180);
    },
    setSelectedId(id) {
      this.selectedId = id;
    },
  },
  created() {
    //Set center of map to user's current location
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.startingLat = position.coords.latitude;
        this.startingLong = position.coords.longitude;
      });
    } else {
      console.log("Could not get user coordinates");
    }

    //Get play dates from API
    mapService
      .getPlayDates()
      .then(response => {
        this.playDates = response.data;
        this.$store.commit('SET_PLAY_DATES', this.playDates);
      })
      .catch(error => {
        console.log(error);
      });
    
    //Get locations from API
    mapService
      .getLocations()
      .then(response => {
        this.locations = response.data;
        this.$store.commit('SET_LOCATIONS', this.locations);
      })
      .catch(error => {
        console.log(error);
      });

  },
};
</script>


<style scoped>
* {
  font-family: "Montserrat", sans-serif;
  color: #2C3333;
  box-sizing: border-box;
}

h1 {
  color: #A5C9CA;
}

#radius-label > strong, #search-form-label > strong, h3 {
  color: #395B64;
}

#search-form > label, #slide-container > p, #s-for-mile {
  color: #395B64;
}

input[type="text"], input[type="number"] {
  border: 2px solid #395B64;
  height: 25px;
  border-radius: 5px;
}

#main-container {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#map-controls {
  background-color: rgb(250, 251, 253);
  /* background-color: #a5c9cacc; */
  padding: 12px 0;
  border: 1px solid #2C3333;
  border-radius: 5px;
  margin: 10px;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: flex-start;
}

#set-location {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-left: 10px;
  flex-basis: 50%;
}

#search-form-label {
  margin: 8px 0;
}

#address-error {
  color: rgb(184, 5, 5);
  margin-bottom: 8px;
}

#search-form {
  display: flex;
  flex-direction: column;
}

#search-form input {
  margin: 4px 8px 4px 0;
}

#search-address {
  width: 150px;
}

#search-city {
  width: 100px;
}

#search-state {
  width: 30px;
}

#radius-box {
  width: 48px;
}

#slide-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  flex-basis: 50%;
}

#radius-label {
  margin: 8px 0 6px 0;
}

#map-container {
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 16px;
}

#map {
  width: 100%;
  height: 400px;
  border: 1px solid #2C3333;
  border-radius: 5px;
}

#playdate-list {
  width: 100%;
  height: 130px;
  max-height: 600px;
  display: flex;
  overflow-x: auto;
  overflow-y: hidden;
  padding: 5px 0;
  
}

.playdate-card {
  height: 100%;
  padding: 0 10px;
  border: 1px solid #2C3333;
  background-color: rgb(250, 251, 253);
  font-size: 12px;
  flex: 0 0 36%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin: 10px;
  margin-right: 5px;
  margin-top: 0;
  
  margin-left: 0;

  border-radius: 5px;
}

.address {
  display: flex;
  flex-direction: column;
  height: 52px;
}

.address h4 {
  margin-top: -10px;
}

.selected-card.playdate-card {
  background-color: rgb(248, 248, 188);
}

#search-map-btn {
  background-color: #395B64;
  font-family: monospace, sans-serif;
  color: white;
  /* width: 100%; */
  border: none;
  border-radius: 5px;
  letter-spacing: 2px;
  height: 25px;
  transition: all .3s ease;
  
}

#search-map-btn:hover {
  background-color: #A5C9CA;
  color: #2C3333;
}

@media only screen and (min-width: 768px) {
  #map {
    height: 500px;
  }

  .playdate-card {
    flex: 0 0 22%;
    margin-left: 0;
  }

  #slide-container {
    width: 360px;
  }

  #radius {
    margin-left: 8px;
  }
}

@media only screen and (min-width: 992px) {
  #map-controls {
    justify-content: space-evenly;
  }

  #map-container {
    flex-direction: row;
  }

  #map {
    flex: 2 1 80%;
    height: 600px;
    border-radius: 5px;
  }

  #playdate-list {
    flex-direction: column;
    height: 100%;
    max-height: 600px;
    flex: 1 1 20%;
    overflow-y: auto;
    overflow-x: hidden;
    padding: 0;
  }

  #search-form {
    flex-direction: row;
    align-items: center;
  }

  #search-form input {
    margin: 4px 8px 4px 8px;
  }

  #slide-container p,
  #slide-container input {
    margin-top: 0px;
    margin-bottom: 4px;
  }

  .playdate-card {
    margin-left: 10px;
    margin-right: 0px;
  }
}
</style>