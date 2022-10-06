<template>
  <div class="play-date">
    <form id="play-date-form" class="form" @submit.prevent="getAddressCoords">
      <label class="form-element" for="locations">Select a location or enter details below</label>
      <select name="locations" id="locations" v-model="currentLocation">
        <option value="">---</option>
        <option
          v-for="location in locations"
          :key="location.locationId"
          :value="location"
        >
          {{ location.locationName }}
        </option>
      </select>
      <div class="form-element justify-input">
        <label for="location-name">Location Name </label>
        <input
          id="location-name"
          type="text"
          required
          v-model="currentLocation.locationName"
          placeholder="Location Name"
        />
      </div>
      <div class="form-element justify-input">
        <label for="add-1">Address </label>
        <input
          id="add-1"
          type="text"
          required
          v-model="currentLocation.address1"
          placeholder="Address 1"
        />
      </div>
      <div class="form-element justify-input">
        <label for="add-2">Address </label>
        <input
          id="add-2"
          type="text"
          v-model="currentLocation.address2"
          placeholder="Address 2"
        />
      </div>
      <div class="form-element justify-input">
        <label for="city">City </label>
        <input
          id="city"
          type="text"
          required
          v-model="currentLocation.city"
          placeholder="City"
        />
      </div>
      <div class="form-element justify-input">
        <label for="search-stateAbbreviation">State </label>
        <input
          type="text"
          id="search-stateAbbreviation"
          required
          v-model="currentLocation.stateAbbreviation"
          placeholder="State"
        />
      </div>
      <div class="form-element justify-input">
        <label for="zipCode-code">Zip Code </label>
        <input
          id="zipCode-code"
          type="text"
          required
          v-model="currentLocation.zipCode"
          placeholder="Zip"
        />
      </div>
      <div class="form-element justify-input">
        <label for="pets">Hosting Pet </label>
        <select
          id="pets"
          placeholder="Hosting Pet"
          required
          v-model="chosenPetId"
        >
          <option value="" disabled>---</option>
          <option :value="pet.petId" v-for="pet in filterPets" :key="pet.petId">
            {{ pet.petName }}
          </option>
        </select>
      </div>
      <div class="form-element justify-input">
        <label for="time">Time </label>
        <input
          type="time"
          class="timepicker"
          name="timepicker"
          min="00:00"
          max="23:59"
          v-model="time"
          required
        />
      </div>
      <div class="form-element justify-input">
        <label for="date">Date </label>
        <input type="date" required v-model="date" />
      </div>

      <button id="submit-btn" type="submit" v-if="!playDateCreated">
        Add Play Date
      </button>
      <div v-if="playDateCreated">
        <h3 class="return-home">Playdate Successfully Created!</h3>
        <button class="return-home" id="return-btn" v-on:click="sendToHome">
          Return to home
        </button>
      </div>
    </form>
  </div>
</template>

<script>
import geocodeService from "../services/GeocodeService";
import mapService from "../services/MapService";
import playDateService from "../services/PlayDateService";
import petService from "../services/petService";
import moment from "moment";
export default {
  data() {
    return {
      //formInfo
      currentLocation: {
        locationName: "",
        address1: "",
        address2: "",
        city: "",
        stateAbbreviation: "",
        zipCode: "",
        latitude: "",
        longitude: "",
      },
      chosenPetId: "",
      time: "",
      date: "",
      //end of form
      dateTime: "",
      playDateCreated: false,
      couldNotFindAddress: false,
      currentOwnerId: 0,
      pets: [],
      locations: [],
    };
  },
  methods: {
    resetForm() {
      this.formInfo = {};
    },
    sendToHome() {
      this.$router.push("/home");
    },
    submitForm() {
      let locationId = this.checkForLocation();
      if (locationId == -1) {
        //create new location in database
        let location = {
          locationId: 0,
          locationName: this.currentLocation.locationName,
          address1: this.currentLocation.address1,
          address2: this.currentLocation.address2,
          city: this.currentLocation.city,
          stateAbbreviation: this.currentLocation.stateAbbreviation,
          zipCode: this.currentLocation.zipCode,
          latitude: parseFloat(this.currentLocation.latitude),
          longitude: parseFloat(this.currentLocation.longitude),
        };
        mapService
          .createLocation(location)
          .then((response) => {
            locationId = response.data.locationId;
            this.storePlayDate(locationId);
          })
          .catch((error) => {
            this.playDateCreated = error.response;
          });
      } else {
        this.storePlayDate(locationId);
      }
    },

    storePlayDate(id) {
      //add locationId to new playdate
      this.dateTime = moment(this.date + " " + this.time);
      const newPlayDate = {
        playDateId: 0,
        hostPetId: this.chosenPetId,
        attendingPetIds: [],
        status: "pending",
        locationId: id,
        playDateTimeStamp: this.dateTime,
      };
      //create new playdate with found locationId
      playDateService.createPlayDate(newPlayDate).then(() => {
        this.playDateCreated = true;
      });
    },

    getAddressCoords() {
      let searchAddress =
        this.currentLocation.address1.trim() +
        ", " +
        this.currentLocation.city.trim() +
        ", " +
        this.currentLocation.stateAbbreviation.trim();
      searchAddress = searchAddress.replaceAll(" ", "+");
      geocodeService
        .getCoords(searchAddress)
        .then((response) => {
          this.currentLocation.latitude =
            response.data.results[0].geometry.location.lat;
          this.currentLocation.longitude =
            response.data.results[0].geometry.location.lng;
          this.couldNotFindAddress = false;
          this.submitForm();
        })
        .catch((error) => {
          this.couldNotFindAddress = true;
          console.log(error);
        });
    },
    checkForLocation() {
      const locations = this.$store.state.locations;
      const locationResults = locations.filter((location) => {
        const isAddressOneSame =
          location.address1.toLowerCase().trim() ==
          this.currentLocation.address1.toLowerCase().trim();
        let isAddressTwoSame = true;
        if (this.currentLocation.address2 != "") {
          isAddressTwoSame =
            location.address2.toLowerCase().trim() ==
            this.currentLocation.address2.toLowerCase().trim();
        }

        const isCitySame =
          location.city.toLowerCase().trim() ==
          this.currentLocation.city.toLowerCase().trim();
        const isStateSame =
          location.stateAbbreviation.toLowerCase().trim() ==
          this.currentLocation.stateAbbreviation.toLowerCase().trim();
        const isZipSame =
          location.zipCode.toLowerCase().trim() ==
          this.currentLocation.zipCode.toLowerCase().trim();

        return (
          isAddressOneSame &&
          isAddressTwoSame &&
          isCitySame &&
          isStateSame &&
          isZipSame
        );
      });
      if (locationResults.length > 0) {
        return locationResults[0].locationId;
      } else {
        return -1;
      }
    },
    truncateState() {
      if (
        document.getElementById("search-stateAbbreviation").value.length > 2
      ) {
        document.getElementById("search-stateAbbreviation").value = document
          .getElementById("search-stateAbbreviation")
          .value.slice(0, 2);
      }
    },
  },
  computed: {
    filterPets() {
      return this.pets.filter((pet) => pet.ownerId == this.currentOwnerId);
    },
  },
  created() {
    petService.getAllPets().then((res) => {
      if (res.status === 200) {
        this.pets = res.data;
      }
    });
    this.currentOwnerId = this.$store.state.user.id;
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
        this.locations = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>

<style scoped>
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

.play-date {
  max-width: 80%;
  margin: auto;
  font-family: "Montserrat", sans-serif;
  color: #2c3333;
}

#play-date-form {
  font-size: 15px;
  max-width: 368px;
}

.form-element {
  margin: 15px 0;
  font-size: 1.3em;
}

label {
  display: block;
}

input[type="text"] {
  line-height: 25px;
  font-size: 18px;
  border-radius: 5px;
  border: 1px solid black;
}

select {
  font-size: 20px;
}

#locations {
  width: 100%;
}

#submit-btn {
  background-color: #395b64;
  font-family: monospace;
  font-size: 18px;
  color: white;
  width: 100%;
  border: none;
  border-radius: 5px;
  height: 35px;
  letter-spacing: 2px;
  transition: all 0.3s ease;
  max-width: 540px;
  margin-bottom: 30px;
}

#submit-btn:hover {
  background-color: #a5c9ca;
  color: #2c3333;
}

@media (min-width: 670px) {
  label {
    display: inline;
  }

  .justify-input {
    display: flex;
    justify-content: space-between;
  }

  .justify-input > input {
    margin-left: 5px;
  }
}

@media (min-width: 920px) {
  .play-date {
    text-align: center;
  }

  #play-date-form {
    display: inline-block;
    text-align: left;
  }
}
</style>