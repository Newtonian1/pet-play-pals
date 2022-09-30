<template>
  <form class="form">
    <h2>Schedule a Play Date:</h2>
    <div class="fields">
      <input
        type="text"
        required
        v-model="addressOne"
        placeholder="Address 1"
      />
      <input type="text" v-model="addressTwo" placeholder="Address 2" />
      <input type="text" required v-model="city" placeholder="City" />
      <input
        type="text"
        id="search-state"
        required
        v-model="state"
        placeholder="State"
      />
      <input type="text" required v-model="zip" placeholder="Zip" />
      <select>
        <option disabled value="" v-for="n in pet.pets" :key="n">{{ n }}>Hosting Pet</option>
        
      </select>
      <label for="time">Time: </label>
      <input
        type="time"
        class="timepicker"
        name="timepicker"
        min="00:00"
        max="23:59"
        v-model="time"
        required
      />
      <button type="submit">Submit Form</button>
    </div>
  </form>
</template>

<script>
import geocodeService from "../services/GeocodeService";
import mapService from "../services/MapService";
import playDateService from "../services/PlayDateService";
export default {
  data() {
    return {
      addressOne: "",
      addressTwo: "",
      city: "",
      state: "",
      zip: "",
      latitude: "",
      longitude: "",
      couldNotFindAddress: false,
      pets: [],
    };
  },
  methods: {
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
        mapService.getLocationById(locationId);
      }
      //add locationId to new playdate
      //create new playdate with found locationId
      playDateService.createPlayDate();
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
};
</script>

<style>
</style>