import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    playDateLocations: [
      {
        id: 1,
        address1: '135 Garfield Place',
        address2: '',
        city: 'Cincinnati',
        state: 'OH',
        zip: '45202',
        latitude: '39.103830',
        longitude: '-84.516410',
      },
      {
        id: 2,
        address1: '1 W Corry St',
        address2: '',
        city: 'Cincinnati',
        state: 'OH',
        zip: '45219',
        latitude: '39.128870',
        longitude: '-84.509940',
      },
      {
        id: 3,
        address1: '456 Belmonte Park N',
        address2: '',
        city: 'Dayton',
        state: 'OH',
        zip: '45405',
        latitude: '39.765570',
        longitude: '-84.201800',
      },
    ]
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    }
  }
})
