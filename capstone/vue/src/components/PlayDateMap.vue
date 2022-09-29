<template>
  <div id="main-container">
    <h1>Play Dates Near You</h1>

    <div id="map-controls">

      <div id="set-location">
        <p id="search-form-label" v-if="!couldNotFindAddress"><strong>Search Location:</strong></p>
        <p id="address-error" v-if="couldNotFindAddress"><strong>ADDRESS NOT FOUND</strong></p>
        <form id="search-form">
          <label for="search-address">Address: </label>
          <input type="text" name="search-address" id="search-address" placeholder="123 Main St">
          <label for="search-city">City: </label>
          <input type="text" name="search-city" id="search-city" placeholder="Los Angeles">
          <label for="search-state">State: </label>
          <input type="text" name="search-state" id="search-state" placeholder="CA" @input="truncateState">
          <button @click.prevent="setSearchAddress">Search</button>
        </form>
      </div>

      <div id="slide-container">
        <label for="radius" id="radius-label"><strong>Search Radius:</strong></label>
        <p><input type="number" min="1" :max="maxRadius" value="25" v-model="searchRadius" id="radius-box" @input="truncateRadius">
        mile<span v-if="searchRadius != 1">s</span></p>
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
          :key="location.id"
          :position="{
            lat: parseFloat(location.latitude),
            lng: parseFloat(location.longitude),
          }"
          :clickable="true"
          @click="setSelectedId(location.id)"
          :title="location.address1"
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
        <!-- <GmapMarker
            :key="index"
            v-for="(m, index) in markers"
            :position="m.position"
            :clickable="true"
            :draggable="true"
            @click="center = m.position"
          /> -->
      </GmapMap>
      <div id="playdate-list">

        <div class="playdate-card" v-for="location in filteredLocations" :key="location.id" :class="{ 'selected-card': selectedId === location.id }">
          <div class="address">
            <h3>{{location.address1}}<br>
            {{location.city}}, {{location.state}}<br>
            {{location.zip}}</h3>
          </div>
          <div class="distance">
            <h4>Distance: {{location.distance}} mi.</h4>
          </div>
        </div>

      </div>
    </div>

  </div>
</template>

<script>
import mapService from '../services/MapService';

export default {
  data() {
    return {
      startingLat: 40.73061,
      startingLong: -73.935242,
      searchRadius: 25,
      maxRadius: 150,
      selectedId: 0,
      couldNotFindAddress: false,
    };
  },
  computed: {
    filteredLocations() {
      const locations = this.$store.state.playDateLocations;
      locations.forEach(location => {location.distance = this.getLocationDistance(location).toFixed(2)});
      const filteredList = locations.filter((location) => {
        return this.isLocationWithinRadius(location)
      });
      filteredList.sort(this.compareDistances);
      return filteredList;
    },
  },
  methods: {
    setSearchAddress() {
      let searchAddress = document.getElementById("search-address").value.trim() + ", " +
                          document.getElementById("search-city").value.trim() + ", " +
                          document.getElementById("search-state").value.trim();
      searchAddress = searchAddress.replaceAll(" ", "+");
      mapService.getCoords(searchAddress)
      .then(response => {
        this.startingLat = response.data.results[0].geometry.location.lat;
        this.startingLong = response.data.results[0].geometry.location.lng;
        this.couldNotFindAddress = false;
      })
      .catch(() => {
        this.couldNotFindAddress = true;
      });
    },
    truncateState() {
      if (document.getElementById("search-state").value.length > 2) {
        document.getElementById("search-state").value = document.getElementById("search-state").value.slice(0,2);
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
    }
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
  },
};
</script>

<style scoped>
* {
  font-family: 'Montserrat', sans-serif;
  box-sizing: border-box;
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
  padding: 12px 0;
  border: 1px solid black;
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
  border: 1px solid black;
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 16px;
}

#map {
  width: 100%;
  height: 400px;
  border: 1px solid black;
}

#playdate-list {
  width: 100%;
  height: 130px;
  max-height: 600px;
  border: 1px solid black;
  display: flex;
  overflow-x: auto;
  overflow-y: hidden;
}

.playdate-card {
  height: 100%;
  padding: 0 10px;
  border: 1px solid black;
  background-color: rgb(250, 251, 253);
  font-size: 12px;
  flex: 0 0 36%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.address {
  height: 52px;
}

.selected-card.playdate-card {
  background-color: rgb(248, 248, 188);
}

@media only screen and (min-width: 768px) {
  #map {
    height: 500px;
  }

  .playdate-card {
    flex: 0 0 22%;
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
  }

  #playdate-list {
    flex-direction: column;
    height: 100%;
    max-height: 600px;
    flex: 1 1 20%;
    overflow-y: auto;
    overflow-x: hidden;
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

}
</style>