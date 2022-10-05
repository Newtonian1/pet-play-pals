<template>
  <div
    class="play-date-card-container-container"
    @playDateUpdated="handlePlayDateUpdated"
  >
    <div id="pending" v-show="pendingPlayDates.length">
      <h2>Pending Play Dates:</h2>
      <div class="full scrollable">
        <play-date-card
          v-for="playDate in pendingPlayDates"
          :key="playDate.playDateId"
          :playDate="playDate"
        />
      </div>
    </div>
    <div id="upcoming" v-show="acceptedPlayDates.length">
      <h2>Upcoming Play Dates:</h2>
      <div class="full scrollable">
        <play-date-card
          v-for="playDate in acceptedPlayDates"
          :key="playDate.playDateId"
          :playDate="playDate"
        />
      </div>
    </div>
  </div>
</template>

<script>
import playDateService from "../services/PlayDateService";
import petService from "../services/petService";
import PlayDateCard from "./PlayDateCard.vue";
export default {
  components: { PlayDateCard },
  data() {
    return {
      currentUserId: 0,
      playDates: [],
      userPetList: [],
      userPetIdList: [],
    };
  },
  computed: {
    acceptedPlayDates() {
      return this.filteredPlayDates.filter(
        (playDate) => playDate.status === "approved"
      );
    },
    pendingPlayDates() {
      return this.filteredPlayDates.filter((playDate) => playDate.status === "pending");
    },
    filteredPlayDates() {
      return this.playDates.filter((playDate) =>
        this.userPetIdList.includes(playDate.hostPetId)
      );
    },
  },
  methods: {
    handlePlayDateUpdated() {
      console.log("It works!");
      petService.getAllPetsByOwnerId(this.currentUserId).then((res) => {
        if (res.status === 200) {
          this.userPetList = res.data;
          this.userPetIdList = this.userPetList.map((pet) => pet.petId);
          playDateService.getPlayDates().then((res) => {
            if (res.status === 200) {
              this.playDates = res.data;
            }
          });
        }
      });
    },
  },
  created() {
    this.currentUserId = this.$store.state.user.id;
    petService.getAllPetsByOwnerId(this.currentUserId).then((res) => {
      if (res.status === 200) {
        this.userPetList = res.data;
        this.userPetIdList = this.userPetList.map((pet) => pet.petId);
        playDateService.getPlayDates().then((res) => {
          if (res.status === 200) {
            this.playDates = res.data.filter((playDate) =>
              this.userPetIdList.includes(playDate.hostPetId)
            );
          }
        });
      }
    });
  },
};
</script>

<style>
.play-date-card-container-container {
  display: grid;
  grid-template-columns: 1fr;
}
/* .play-date-card-container-container > * {
  grid-column: 2 / -2;
} */
.play-date-card-container-container > .full {
  grid-column: 1 / -1;
}
.scrollable {
  display: grid;
  grid-gap: 10px;
  grid-auto-flow: column;
  grid-auto-columns: 40%;
  grid-template-rows: minmax(150px, 1fr);
  padding: 0;
  width: 100%;
  overflow-x: auto;
  overflow-y: hidden;
}

#pending,
#upcoming {
  text-align: center;
  width: 95vw;
}

@media (max-width: 840px) {
  .scrollable {
    max-width: 95vw;
  }
}

@media (max-width: 683px) {
  .scrollable {
    display: flex;
  }
}
</style>