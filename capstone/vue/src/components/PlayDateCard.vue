<template>
  <div class="party-card-container">
    <div class="loading-card" v-if="location == null || hostingPet == null">
      LOADING
    </div>
    <div
      class="party-card"
      v-if="location != null && hostingPet != null"
      :class="{
        pending: playDate.status === 'pending',
        accepted: playDate.status === 'approved',
      }"
    >
      <h3 class="heading">Play Date at {{ location.locationName }}</h3>
      <div class="details">
        <p class="location">
          {{ location.address1 }} {{ location.city }},
          {{ location.stateAbbreviation }}
        </p>
        <p class="time">
          {{ playDateDateTime }}
        </p>
      </div>
      <p class="hosting">Hosted by: {{ hostingPet.petName }}</p>
      <p class="attending" v-if="attendingPets.length">
        Guests:
        <span v-for="pet in attendingPets" :key="pet.petId">
          {{ pet.petName
          }}{{ pet === attendingPets[attendingPets.length - 1] ? "" : ", " }}
        </span>
      </p>
      <p class="attending" v-if="!attendingPets.length">
        No guests have joined this pet play date yet!
      </p>
    </div>
  </div>
</template>

<script>
import locationService from "../services/locationService";
import petService from "../services/petService";
import moment from "moment";
export default {
  props: ["playDate"],
  data() {
    return {
      location: null,
      hostingPet: null,
      attendingPets: [],
    };
  },
  computed: {
    playDateDateTime() {
      return moment(this.playDate.playDateTimeStamp).format(
        "dddd, MMM. Do [at] h:mm a"
      );
    },
  },
  created() {
    locationService.getLocationById(this.playDate.locationId).then((res) => {
      if (res.status === 200) {
        this.location = res.data;
      }
    });
    petService.getPetById(this.playDate.hostPetId).then((res) => {
      if (res.status === 200) {
        this.hostingPet = res.data;
      }
    });
    petService.getAllPets().then((res) => {
      if (res.status === 200) {
        this.attendingPets = res.data.filter((pet) =>
          this.playDate.attendingPetIds.includes(pet.petId)
        );
      }
    });
  },
};
</script>

<style>
div.party-card-container > div {
  border: 2px #2c3333 solid;
  border-radius: 10px;
  text-align: center;
}
div.loading-card {
  font-weight: bold;
}
div > .heading {
  text-align: center;
}
.pending {
  background-color: rgba(228, 163, 125, 0.3);
}
.accepted {
  background-color: rgba(177, 215, 180, 0.3);
}
</style>