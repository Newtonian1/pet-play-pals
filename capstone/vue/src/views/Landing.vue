<template>
  <div class="landing">
    <div id="logo-img">
        <img src="../assets/images\logos/PetPlayPalsLogo.png" alt="">
    </div>
    <play-date-map />
  </div>
</template>

<script>
import PlayDateMap from '../components/PlayDateMap.vue'
export default {
  components: { PlayDateMap },

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