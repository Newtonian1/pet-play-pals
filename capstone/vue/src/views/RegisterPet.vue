<template>
    <div class="register-pet">
       <h1>Register A Pet</h1>
       <add-pet-form />
    </div> 
</template>

<script>
import AddPetForm from '../components/AddPetForm.vue'
export default {
  components: { AddPetForm },

}
</script>

<style scoped>
  h1 {
    text-align: center;
  }
</style>