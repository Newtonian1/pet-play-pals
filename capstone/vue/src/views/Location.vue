<template>
  <div id="main-container">
    <div v-if="location != null">
      <h2>Upcoming Play Dates at {{ location.locationName }}</h2>
      <h3>
        {{ location.address1 }}<br />
        {{ location.city }}, {{ location.stateAbbreviation }}
      </h3>
    </div>
    <div class="card-container">
      <play-date-card
        v-for="playDate in upcomingPlayDates"
        :key="playDate.playDateId"
        :playDate="playDate"
      />
    </div>
    <div>
      <button class="return-home" id="return-btn" v-on:click="sendToHome">
        Return to home
      </button>
    </div>
  </div>
</template>

<script>
import PlayDateCard from "../components/PlayDateCard.vue";
import mapService from "../services/MapService";

export default {
  components: {
    PlayDateCard,
  },
  computed: {
    locationId() {
      return this.$route.params.locationId;
    },
    location() {
      const results = this.$store.state.locations.filter((location) => {
        return location.locationId == this.locationId;
      });
      return results[0];
    },
    upcomingPlayDates() {
      const locationPlayDates = this.$store.state.playDates.filter(
        (playDate) => {
          return playDate.locationId == this.locationId;
        }
      );
      const filteredPlayDates = locationPlayDates.filter((playDate) => {
        return Date.now() < Date.parse(playDate.playDateTimeStamp);
      });
      return filteredPlayDates;
    },
  },
  created() {
    //Get play dates from API
    mapService
      .getPlayDates()
      .then((response) => {
        this.$store.commit("SET_PLAY_DATES", response.data);
      })
      .catch(() => {
        // console.log(error);
      });

    //Get locations from API
    mapService
      .getLocations()
      .then((response) => {
        this.$store.commit("SET_LOCATIONS", response.data);
      })
      .catch(() => {
        // console.log(error);
      });
  },
  methods: {
    sendToHome() {
      this.$router.push("/home");
    },
  },
};
</script>

<style scoped>
#main-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.card-container {
  display: flex;
  flex-wrap: wrap;
  margin-top: 16px;
}

h3 {
  text-align: center;
}

.card-container > * {
  margin: 10px;
}

div:has(.return-home) {
  text-align: center;
}
.return-home {
  display: inline;
  margin-top: 10px;
  margin-bottom: 10px;
}

#return-btn {
  margin: 10px;
  background-color: #395b64;
  font-family: monospace;
  color: white;
  border: none;
  border-radius: 5px;
  height: 25px;
  letter-spacing: 2px;
  transition: all 0.3s ease;
}

#return-btn:hover {
  border: 2px solid #395b64;
  background-color: #fff;
  color: #2c3333;
}
</style>