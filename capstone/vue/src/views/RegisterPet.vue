<template>
    <div class="register-pet">
       <h1>Register A Pet</h1>
       <add-pet-form />
       <pet-list />
    </div> 
</template>

<script>
import AddPetForm from '../components/AddPetForm.vue'
import PetList from '../components/PetList.vue'
export default {
  components: { AddPetForm, PetList },

}
</script>

<style scoped>
  .register-pet {
    background-color: #E7F6F2;
    font-family: 'Montserrat', sans-serif;
  }
  h1 {
    text-align: center;
    padding-top: 20px;
  }

</style>