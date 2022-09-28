<template>
  <div class="home">
    <h1>Welcome {{currentUserName}}!</h1>
  </div>
</template>

<script>
export default {
  name: "home",
  computed: {
    currentUserName() {
      return this.$store.state.user.username;
    }
  }
};
</script>
