<template>
  <div id="app">
    <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
      &nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'registerPet' }"
        >Register Pet</router-link
      >
      &nbsp;|&nbsp;
      <router-link to="/createPlaydate">Schedule Play Date</router-link>
    </div>
    <router-view />
  </div>
</template>

<script>
export default {};
</script>
<style>
#app {
  width: 95vmax;
  display: block;
  margin: auto;
}
</style>