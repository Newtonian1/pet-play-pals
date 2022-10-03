<template>
  <div class="home">
    <h1>Welcome {{currentUserName}}!</h1>
    <div>
      <play-dates-list/>
    </div>
    <play-date-map/>
    <pet-list/>
  </div>
</template>

<script>
import PetList from '../components/PetList.vue';
import PlayDateMap from '../components/PlayDateMap.vue';
import PlayDatesList from '../components/PlayDatesList.vue';
export default {
  components: { PlayDatesList, PlayDateMap, PetList },
  name: "home",
  computed: {
    currentUserName() {
      return this.$store.state.user.username;
    }
  }
};
</script>

<style scoped>
  h1 {
    padding: 0.3em;
  }

  .home {
    /* background-color: #eeebeb; */
    border-radius: 3px;
  }
</style>
