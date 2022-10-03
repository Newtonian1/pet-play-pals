<template>
  <div class="play-date">
    <form id="play-date-form" class="form" @submit.prevent="getAddressCoords">
      <div class="form-element">
        <label for="location-name">Location Name </label>
        <input
          id="location-name"
          type="text"
          required
          v-model="locationName"
          placeholder="Location Name"
        />
      </div>
      <div class="form-element">
        <label for="add-1">Address </label>
        <input
          id="add-1"
          type="text"
          required
          v-model="addressOne"
          placeholder="Address 1"
        />
      </div>
      <div class="form-element">
        <label for="add-2">Address </label>
        <input
          id="add-2"
          type="text"
          v-model="addressTwo"
          placeholder="Address 2"
        />
      </div>
      <div class="form-element">
        <label for="city">City </label>
        <input
          id="city"
          type="text"
          required
          v-model="city"
          placeholder="City"
        />
      </div>
      <div class="form-element">
        <label for="search-state">State </label>
        <input
          type="text"
          id="search-state"
          required
          v-model="state"
          placeholder="State"
        />
      </div>
      <div class="form-element">
        <label for="zip-code">Zip Code </label>
        <input
          id="zip-code"
          type="text"
          required
          v-model="zip"
          placeholder="Zip"
        />
      </div>
      <div class="form-element">
        <label for="pets">Hosting Pet </label>
        <select
          id="pets"
          placeholder="Hosting Pet"
          required
          v-model="chosenPetId"
        >
          <option value="" disabled>---</option>
          <option :value="n.petId" v-for="n in filterPets" :key="n">
            {{ n.petName }}
          </option>
        </select>
      </div>
      <div class="form-element">
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
      <div class="form-element">
        <label for="date">Date </label>
        <input type="date" required v-model="date" />
      </div>

      <button id="submit-btn" type="submit" v-if="!playDateCreated">Add Play Date</button>
      <div v-if="playDateCreated">
        <h3 class="return-home">Playdate Successfully Created!</h3>
        <button class="return-home" id="return-btn">Return to home</button>
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
      locationName: "",
      addressOne: "",
      addressTwo: "",
      city: "",
      state: "",
      zip: "",
      latitude: "",
      longitude: "",
      chosenPetId: "",
      time: "",
      date: "",
      //end of form
      dateTime: "",
      playDateCreated: false,
      couldNotFindAddress: false,
      currentOwnerId: 0,
      pets: [],
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
          locationName: this.locationName,
          address1: this.addressOne,
          address2: this.addressTwo,
          city: this.city,
          stateAbbreviation: this.state,
          zipCode: this.zip,
          latitude: parseFloat(this.latitude),
          longitude: parseFloat(this.longitude),
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
        this.addressOne.trim() +
        ", " +
        this.city.trim() +
        ", " +
        this.state.trim();
      searchAddress = searchAddress.replaceAll(" ", "+");
      geocodeService
        .getCoords(searchAddress)
        .then((response) => {
          this.latitude = response.data.results[0].geometry.location.lat;
          this.longitude = response.data.results[0].geometry.location.lng;
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
          this.addressOne.toLowerCase().trim();
        let isAddressTwoSame = true;
        if (this.addressTwo != "") {
          isAddressTwoSame =
            location.address2.toLowerCase().trim() ==
            this.addressTwo.toLowerCase().trim();
        }

        const isCitySame =
          location.city.toLowerCase().trim() == this.city.toLowerCase().trim();
        const isStateSame =
          location.stateAbbreviation.toLowerCase().trim() ==
          this.state.toLowerCase().trim();
        const isZipSame =
          location.zipCode.toLowerCase().trim() ==
          this.zip.toLowerCase().trim();

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
      if (document.getElementById("search-state").value.length > 2) {
        document.getElementById("search-state").value = document
          .getElementById("search-state")
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
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>

<style scoped>
.return-home {
  display: inline;
  margin-top: 10px;
  margin-bottom: 10px;
}

#return-btn {
  margin-left: 10px;
  background-color: #395B64;
  font-family: monospace;
  color: white;
  border: none;
  border-radius: 5px;
  height: 25px;
  letter-spacing: 2px;
  transition: all .3s ease;
}

#return-btn:hover {
  border: 2px solid #395B64;
  background-color: #FFF;
  color: #2C3333;
}

.play-date {
  max-width: 80%;
  margin: auto;
  font-family: "Montserrat", sans-serif;
  color: #2c3333;
}

#play-date-form {
  font-size: 15px;
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

#submit-btn {
  background-color: #395B64;
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
  background-color: #A5C9CA;
  color: #2c3333;
}

@media (min-width: 670px) {
  label {
    display: inline;
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