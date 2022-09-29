<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link> &nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'registerPet' }" >Register Pet</router-link> &nbsp;|&nbsp;
      <router-link to="/createPlaydate">Schedule Play Date</router-link>
    </div>
    <router-view />
    <play-date-map></play-date-map>
  </div>
</template>

<script>
import playDateMap from './components/PlayDateMap.vue';

export default {
  components: {
    playDateMap
  }
}
</script>
