<template>
  <div>
    <div id="map-container">
      <h1>Map Test</h1>
      <GmapMap id="map"
        :center="{ lat: startingLat, lng: startingLong }"
        :zoom="11"
        map-type-id="roadmap"
      >
        <GmapMarker
          v-for="location in this.$store.state.playDateLocations"
          :key="location.id"
          :position="{ lat: parseFloat(location.latitude), lng: parseFloat(location.longitude) }"
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
    </div>
  </div>
</template>

<script>
export default {
  data() {
      return {
            startingLat: 40.730610,
            startingLong: -73.935242,
      }
  },
  created() {
    //Set center of map to user's current location
    if(navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(position => {
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

#map-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
</style>