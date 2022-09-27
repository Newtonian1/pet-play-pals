<template>
  <div class="home">
    <h1>Welcome {{currentUserName}}!</h1>
    <play-date-map />
  </div>
</template>

<script>
import PlayDateMap from '../components/PlayDateMap.vue';
export default {
  components: { PlayDateMap },
  name: "home",
  computed: {
    currentUserName() {
      return this.$store.state.user.username;
    }
  }
};
</script>
