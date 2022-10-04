import Vue from 'vue'
import App from './App.vue'
import { initializeApp } from 'firebase/app'
import router from './router/index'
import store from './store/index'
import axios from 'axios'
import * as VueGoogleMaps from 'vue2-google-maps'

Vue.config.productionTip = false

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

Vue.use(VueGoogleMaps, {
  load: {
    key: 'AIzaSyAH3vglNrAne06DiZX94DP_KT0uMNxYD4k',
    libraries: 'places',
  },
})

new Vue({
  router,
  store,
  render: h => h(App),
  created() {
    initializeApp({
      apiKey: "AIzaSyCF761UUIgthZYgEDoUwUdSp_o_IPGnf04",
      authDomain: "petplaypals-a7a47.firebaseapp.com",
      databaseURL: "https://petplaypals-a7a47-default-rtdb.firebaseio.com",
      projectId: "petplaypals-a7a47",
      storageBucket: "gs://petplaypals-a7a47.appspot.com",
      messagingSenderId: "550417461118",
      appId: "1:550417461118:web:7769c9ce06592543f4cd2f",
      measurementId: "G-LL5MPH26SW"
    });
  }
}).$mount('#app')
