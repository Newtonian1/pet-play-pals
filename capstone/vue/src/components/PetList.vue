<template>
  <div id="pet-container">
    <pet-card
      class="flip-card"
      id="myCard"
      v-for="pet in filterPets"
      :key="pet.name"
      :pet="pet"
    />
  </div>
</template>

<script>
import petService from "../services/petService";
import PetCard from './PetCard.vue';
export default {
  components: { PetCard },
  name: "pet-list",
  data() {
    return {
      currentOwnerId: 0,
      pets: [],
    };
  },
  created() {
    petService.getAllPets().then((res) => {
      if (res.status === 200) {
        this.pets = res.data;
      }
    });
    this.currentOwnerId = this.$store.state.user.id;
  },
  computed: {
    filterPets() {
      return this.pets.filter((pet) => pet.ownerId == this.currentOwnerId);
    },
  },
};
</script>

<style scoped>
#pet-container {
  margin-top: 20px;
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}

@media (max-width: 700px) {
  #pet-container {
    flex-direction: column;
    align-content: center;
  }
}
</style>