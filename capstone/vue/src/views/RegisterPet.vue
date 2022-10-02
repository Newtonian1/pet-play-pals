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
  .register-pet {
    background-color: #E7F6F2;
    border-radius: 5px;
    font-family: 'Montserrat', sans-serif;
  }
  h1 {
    text-align: center;
    padding-top: 20px;
  }

</style>