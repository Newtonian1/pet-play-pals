<template>
  <div class="play-date">
    <form id="play-date-form" class="form" @submit.prevent="submitForm">
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
        <input id="add-2" type="text" v-model="addressTwo" placeholder="Address 2" />
      </div>
      <div class="form-element">
        <label for="city">City </label>
        <input id="city" type="text" required v-model="city" placeholder="City" />
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
        <input id="zip-code" type="text" required v-model="zip" placeholder="Zip" />
      </div> 
      <div class="form-element">
        <label for="pets">Hosting Pet </label>
        <select id="pets" placeholder="Hosting Pet" required v-model="hostPetDropDown">
          <option value="" disabled> --- </option>
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

      <button id="submit-btn" type="submit">Add Play Date</button>
    </form>
  </div>
  
</template>

<script>
import geocodeService from "../services/GeocodeService";
import mapService from "../services/MapService";
import playDateService from "../services/PlayDateService";
import petService from "../services/petService"
export default {
  data() {
    return {
      formInfo: {
      addressOne: "",
      addressTwo: "",
      city: "",
      state: "",
      zip: "",
       latitude: "",
      longitude: "",
      time: ''
},
     
      couldNotFindAddress: false,
      currentOwnerId: 0,
      pets: [],
    };
  },
  methods: {
    resetForm(){
      this.formInfo = {};
    },
    sendToHome(){
      this.resetForm();
      this.$router.push("/");
    },


    submitForm() {
      this.getAddressCoords();
      if (this.couldNotFindAddress) {
        return "Error";
      }
      let locationId = this.checkForLocation();
      if (locationId == -1) {
        
        //create new location in database
        mapService.createLocation();


        //get new locationId
        mapService.getLocationById(this.locationId)
      
      //add locationId to new playdate



      //create new playdate with found locationId
      playDateService.createPlayDate();



    }},

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
        })
        .catch(() => {
          this.couldNotFindAddress = true;
        });
    },
    checkForLocation() {
      const locations = this.$store.state.locations;
      const locationResults = locations.filter((location) => {
        const isAddressOneSame =
          location.address1.toLowerCase().trim() ==
          this.addressOne.toLowerCase().trim();
        const isAddressTwoSame =
          location.address2.toLowerCase().trim() ==
          this.addressTwo.toLowerCase().trim();
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
            return this.pets.filter(pet => pet.ownerId == this.currentOwnerId);
          }
        },
        created() {
          petService.getAllPets().then(res => {
            if (res.status === 200) {
              this.pets = res.data;
            }
          });
          this.currentOwnerId = this.$store.state.user.id;
        }
};
</script>

<style scoped>
  .play-date {
    max-width: 80%;
    margin: auto;
    font-family: 'Montserrat', sans-serif;
    color: #2C3333;
  }

  #play-date-form{
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
    background-color: #676FA3;
    font-family: monospace;
    font-size: 18px;
    color: white;
    width: 100%;
    border: none;
    border-radius: 5px;
    height: 35px;
    letter-spacing: 2px;
    transition: all .3s ease;
    max-width: 540px;
    margin-bottom: 30px;
  }

  #submit-btn:hover {
    background-color: #CDDEFF;
    color: #2C3333;
  }

   @media(min-width:670px) {
      label {
        display: inline;
      }
    }

  @media(min-width:920px) {
    .play-date {
      text-align: center;
    }
    
    #play-date-form {
      display: inline-block;
      text-align: left;
        
    }
  }
</style>