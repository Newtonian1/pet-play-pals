<template>
  <form class="form">
    <h2>Schedule a Play Date:</h2>
    <div class="fields">
      <label for="address-1">Address line 1:</label>
      <input type="text" required v-model="addressOne"/>
      <label for="address-2">Address line 2:</label>
      <input type="text" v-model="addressTwo"/>
      <label for="city">City:</label>
      <input type="text" required v-model="city"/>
      <label for="state">State:</label>
      <input type="text" required v-model="state"/>
      <label for="zip">Zip:</label>
      <input type="text" required v-model="zip"/>
      <select v-model="hostPet">
          <option disabled value="">Hosting Pet</option>
          <option>Doggo</option>
          <option>Doggy</option>
          <option>Sir Patrick Stewart</option>
      </select>
      <label for="time">Time: </label>
      <input type="time" class="timepicker" name="timepicker" min="00:00" max="23:59" v-model="time" required>
      <button type="submit">Submit Form</button>
    </div>
  </form>
</template>

<script>
export default {
data(){
    return {
        addressOne: '',
        addressTwo: '',
        city: '',
        state: '',
        zip: '',
        hostPet: ''

    }
}



};
</script>

<style>
</style>