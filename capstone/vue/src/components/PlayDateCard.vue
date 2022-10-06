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
      <!-- v-if - they don't have any pets -->
      <button
        class="join-edit-playdate"
        v-show="$store.state.token != ''"
        @click="joinFormOpen"
      >
        {{ userPetsAttending.length == 0 ? "Join" : "Edit" }}
      </button>
    </div>
    <div v-if="showJoinForm" id="screen-blocker">
      <div id="join-form">
        <h3>Join this Pet Play Date</h3>
        <div id="filtered-pets-flex">
          <div id="name-checkbox" v-for="pet in filterPets" :key="pet.petId">
            <label :for="pet.petName">{{ pet.petName }}</label>
            <input
              :value="pet.petId"
              v-model="petsToUpdate"
              type="checkbox"
              :name="pet.petName"
              :id="pet.petName"
            />
          </div>
        </div>
        <input class="done-playdate-btn" type="button" value="Done" @click="editPlayDate" />
      </div>
    </div>
  </div>
</template>

<script>
import locationService from "../services/locationService";
import petService from "../services/petService";
import moment from "moment";
import playDateService from '../services/PlayDateService';
export default {
  props: ["playDate"],
  data() {
    return {
      location: null,
      hostingPet: null,
      attendingPets: [],
      currentOwnerId: 0,
      pets: [],
      showJoinForm: false,
      petsToUpdate: [],
    };
  },
  computed: {
    playDateDateTime() {
      return moment(this.playDate.playDateTimeStamp).format(
        "dddd, MMM. Do [at] h:mm a"
      );
    },
    filterPets() {
      return this.pets.filter((pet) => pet.ownerId == this.currentOwnerId);
    },
    userPetsAttending() {
      let petIds = this.filterPets.map((pet) => pet.petId);
      return this.playDate.attendingPetIds.filter((id) => petIds.includes(id));
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
    petService.getAllPets().then((res) => {
      if (res.status === 200) {
        this.pets = res.data;
      }
    });
    this.currentOwnerId = this.$store.state.user.id;
  },
  methods: {
    editPlayDate() {
      this.showJoinForm = !this.showJoinForm;
      // let petIdArray = this.playDate.attendingPetIds.filter(id => {
      //   return !this.filterPets.map(pet => pet.petId).includes(id);
      // })
      let petIdsToAdd = [...this.petsToUpdate];
      // this.petsToUpdate.forEach(petId => {
      //   if (!this.playDate.attendingPetIds.includes(petId)) {
      //     petIdsToAdd.push(petId);
      //   }
      // });
      // petIdsToAdd = this.playDate.attendingPetIds.concat(petIdsToAdd)
      let updatedPlayDate = {
        hostPetId: this.playDate.hostPetId,
        attendingPetIds: petIdsToAdd,
        locationId: this.playDate.locationId,
        playDateId: this.playDate.playDateId,
        playDateTimeStamp: this.playDate.playDateTimeStamp,
        status: petIdsToAdd.length != 0 ? "approved" : "pending",
      };
      playDateService.updatePlayDate(updatedPlayDate, this.playDate.playDateId).then(res => {
        if (res.status === 200) {
          console.log('success');
          this.$store.commit('UPDATE_PLAY_DATE', updatedPlayDate);
          // this.$root.$forceUpdate();
          // $emit('playDateUpdated');
        }
      });
      // window.location.reload();
    },
    joinFormOpen() {
      this.showJoinForm = !this.showJoinForm;
      this.petsToUpdate = this.attendingPets.map(pet => pet.petId);
    }
  },
};
</script>

<style>
.party-card {
  min-width: 250px;
}

div.party-card-container > div {
  min-width: 250px;
  border: 2px #2c3333 solid;
  border-radius: 10px;
  text-align: center;
  padding: 0 10px;
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

p.attending {
  margin-bottom: 0;
}

.join-edit-playdate, .done-playdate-btn {
  background-color: #395b645b;
  font-family: monospace, sans-serif;
  color: white;
  /* width: 100%; */
  border: 2px solid #395b64;
  border-radius: 5px;
  letter-spacing: 2px;
  height: 25px;
  margin: 10px;
  transition: all 0.3s ease;
}

.join-edit-playdate:hover {
  background-color: #a5c9ca;
  border: none;
  color: #2c3333;
}

.done-playdate-btn:hover {
  background-color: #395b64;
  border: none;
  /* color: #2c3333; */
}

#screen-blocker {
  position: fixed;
  z-index: 100;
  width: 100vw;
  height: 100vh;
  top: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.5);
  border: none;
  border-radius: 0;
}

#join-form {
  position: fixed;
  z-index: 101;
  max-width: 75vw;
  max-height: 75vh;
  top: 30%;
  right: 40%;
  background-color: #a5c9ca;
  width: 15em;
  border: 2px solid #395b64;
  border-radius: 15px;
  padding: 20px;
  display: flex;
  flex-direction: column;
  align-content: space-between;
}

#name-checkbox {
  display: flex;
  justify-content: space-between;
}

#filtered-pets-flex {
  display: flex;
  flex-direction: column;
  width: 150px;
  margin: auto;
  margin-bottom: 15px;
}
</style>