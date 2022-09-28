<template>
  <div id="main-container">
    <h1>Map Test</h1>

    <div id="map-controls">

      <div id="set-location">
        <label for="search-location">Search Location: </label>
        <input type="text" name="search-location" id="search-location">
      </div>

      <div id="slide-container">
        <label for="radius">Search Radius: 

          <input type="number" min="1" max="99" value="25" v-model="searchRadius" id="radius-box" @input="truncateRadius">

           mile(s)</label><br />
        <input
          type="range"
          min="1"
          max="99"
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
          <h3>{{location.address1}}<br>
          {{location.city}}, {{location.state}}<br>
          {{location.zip}}</h3>
          <h4>Distance: {{location.distance}} mi.</h4>
        </div>

      </div>
    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      startingLat: 40.73061,
      startingLong: -73.935242,
      searchRadius: 25,
      selectedId: 0,
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
    truncateRadius() {
      if (this.searchRadius.length > 2) {
        this.searchRadius = this.searchRadius.slice(0,2);
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
      //Haversine formula
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
#main-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#map-controls {
  background-color: rgb(250, 251, 253);
  padding: 12px;
  border: 1px solid black;
  width: 100%;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}

#radius-box {
  width: 32px;
}

#slide-container {
  display: flex;
  justify-content: space-between;
  width: 20%;
  padding: 10px;
}

#map-container {
  border: 1px solid black;
  display: flex;
  width: 100%;
  margin-bottom: 16px;
}

#map {
  width: 70%;
  height: 600px;
}

#playdate-list {
  width: 30%;
  height: 600px;
  max-height: 600px;
  overflow-y: auto;
  overflow-x: hidden;
}

.playdate-card {
  width: 99%;
  height: 140px;
  border: 1px solid black;
  background-color: rgb(250, 251, 253);
  padding-left: 16px;
}

.selected-card.playdate-card {
  background-color: rgb(248, 248, 188);
}
</style>