<template>
    <div id="main-container">
        <h2>Upcoming Play Dates at {{ location.locationName }}</h2>
        <h3>
            {{ location.address1 }}<br />
            {{ location.city }}, {{ location.stateAbbreviation }}
        </h3>
        <div class="card-container">
            <play-date-card
                v-for="playDate in upcomingPlayDates"
                :key="playDate.playDateId"
                :playDate="playDate"
            />
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
            .catch((error) => {
                console.log(error);
            });

        //Get locations from API
        mapService
            .getLocations()
            .then((response) => {
                this.$store.commit("SET_LOCATIONS", response.data);
            })
            .catch((error) => {
                console.log(error);
            });
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

play-date-card {
    margin: 8px;
}
</style>