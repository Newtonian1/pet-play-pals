<template>
  <div id="app">
    <!-- <app-header/> -->
    <div id="nav">
      <div>
        <router-link v-bind:to="{ name: 'home' }"><app-header/></router-link>
      </div>
      <div id="links-in-nav">
        <router-link v-bind:to="{ name: 'home' }" v-if="$store.state.token != ''">
          Home
        </router-link>
        <!-- IF LOGGED OUT -->
        <router-link v-bind:to="{ name: 'login' }" v-else>
          Login
        </router-link>
        <!--  -->

        <router-link v-bind:to="{ name: 'registerPet' }" v-if="$store.state.token != ''">
          Register Pet
        </router-link>

        <!-- IF LOGGED OUT -->
        <router-link v-bind:to="{ name: 'register' }" v-else>
          Create Account
        </router-link>
        <!--  -->

        <router-link to="/createPlaydate" v-if="$store.state.token != ''">
          Schedule Play Date
        </router-link>
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">
          Logout
        </router-link>
      </div>
    </div>
    <router-view />
  </div>
</template>

<script>
import AppHeader from './components/AppHeader.vue';
import imageService from './services/imageService';
export default {
  components: { AppHeader },
  created() {
    imageService.getImageUrls();
  }};
</script>
<style>
  #links-in-nav > .router-link-exact-active {
    border: 1px solid #2C3333;
    border-radius: 3px; 
  }

  #app {
    width: 95vmax;
    display: block;
    margin: auto;
    font-family: 'Montserrat', sans-serif;
  }

  #nav {
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-radius: 5px;
    background: linear-gradient(
      180deg,
      /* #382602c5, */
      #ffd04eab,
      #ffd04e8a,
      #ebdc847a,
      #FFF
      /* #44b4a1c9 */
    );
    
  }

  #nav > div {
    display: inline;
  }

  #links-in-nav > a {
    padding: 4px;
    margin: 0 4px;
    font-weight: 500;
    font-size: 1em;
    border-radius: 5px;
    text-decoration: none;
    color: #2C3333;
  }

  #links-in-nav > a:hover {
    display: inline-block;
    color: #97c4c5;
    transform: scale(1.1);
    
  }

  #logo-img-div {
    display: inline;
  }

  #logo-img-div > img {
    max-height: 20px;
    /* max-width: 100px; */
  }

  @media(max-width: 860px) {
    #nav {
      
      max-width: 95vw;
    }
    #app {
      max-width: 95vw;
    }
  }
  @media(max-width: 680px) {
    #nav {
      flex-direction: column;
      /* max-width: 95vw; */
    }

  }

  @media(max-width: 430px) {
    #links-in-nav > a {
      font-size: 0.8em;
      margin: 0;
    }
  }

</style>