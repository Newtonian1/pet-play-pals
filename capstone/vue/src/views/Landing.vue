<template>
  <div id="logo-img">
      <img src="../assets/images\logos/PetPlayPalsLogo.png" alt="">
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
    img {
        max-height: 93vh;
        max-width: 93vw;
        display: block;
        margin: auto;
    }

    @media(max-width: 860px) {
        img {
            max-width: 80%;
            height: auto;
            
        }

        #logo-img {
            max-width: fit-content;
        }
    }

</style>