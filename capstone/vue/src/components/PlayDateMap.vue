<template>
  <div id="main-container">
    <h1>Map Test</h1>

    <!-- documentation for Gmap: https://www.npmjs.com/package/vue2-google-maps -->
    <GmapMap
      id="map"
      :center="{ lat: startingLat, lng: startingLong }"
      :zoom="11"
      map-type-id="roadmap"
    >
      <GmapMarker
        v-for="location in filteredLocations"
        :key="location.id"
        :position="{
          lat: parseFloat(location.latitude),
          lng: parseFloat(location.longitude),
        }"
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
    <div class="slide-container">
      <label for="radius">Search Radius: {{ searchRadius }} miles</label><br />
      <input
        type="range"
        min="1"
        max="100"
        value="25"
        id="radius"
        name="radius"
        v-model="searchRadius"
      />
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
    };
  },
  computed: {
    filteredLocations() {
      const locations = this.$store.state.playDateLocations;
      const filteredList = locations.filter((location) => {
        return this.isLocationWithinRadius(location);
      });
      console.log(filteredList);
      return filteredList;
    },
  },
  methods: {
    isLocationWithinRadius(location) {
      const lat = parseFloat(location.latitude);
      const lng = parseFloat(location.longitude);
      const distance = this.getDistanceFromLatLonInMi(
        lat,
        lng,
        this.startingLat,
        this.startingLong
      );
      console.log(distance);
      return distance <= this.searchRadius;
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
#map {
  width: 70%;
  height: 600px;
}

#main-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>