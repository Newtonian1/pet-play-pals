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
      {
        id: 4,
        address1: '114 W Elder St',
        address2: '',
        city: 'Cincinnati',
        state: 'OH',
        zip: '45202',
        latitude: '39.115530',
        longitude: '-84.518960',
      },
      {
        id: 5,
        address1: '156 Pavilion Pkwy',
        address2: '',
        city: 'Newport',
        state: 'KY',
        zip: '41071',
        latitude: '39.089560',
        longitude: '-84.476040',
      },
      {
        id: 6,
        address1: '5075 Montgomery Rd',
        address2: '',
        city: 'Norwood',
        state: 'OH',
        zip: '45212',
        latitude: '39.167122',
        longitude: '-84.453583',
      },
      {
        id: 7,
        address1: '6300 Kings Island Dr',
        address2: '',
        city: 'Mason',
        state: 'OH',
        zip: '45040',
        latitude: '39.341419',
        longitude: '-84.277840',
      },
      {
        id: 8,
        address1: '8715 Hamilton Cleves Pike Rd',
        address2: '',
        city: 'Cleves',
        state: 'OH',
        zip: '45002',
        latitude: '39.223270',
        longitude: '-84.727220',
      },
      {
        id: 9,
        address1: '200 Georgesville Rd',
        address2: '',
        city: 'Columbus',
        state: 'OH',
        zip: '43228',
        latitude: '39.950520',
        longitude: '-83.110603',
      },
      {
        id: 10,
        address1: '2401 Ontario St',
        address2: '',
        city: 'Cleveland',
        state: 'OH',
        zip: '44115',
        latitude: '41.494930',
        longitude: '-81.685257',
      },
      {
        id: 11,
        address1: '420 Presidential Dr',
        address2: '',
        city: 'Marion',
        state: 'OH',
        zip: '43302',
        latitude: '40.580280',
        longitude: '-83.121430',
      },
      {
        id: 12,
        address1: '1840 Columbus Pike',
        address2: '',
        city: 'Delaware',
        state: 'OH',
        zip: '43015',
        latitude: '40.264469',
        longitude: '-83.069740',
      },
      {
        id: 13,
        address1: '1660 Marion Rd',
        address2: '',
        city: 'Bucyrus',
        state: 'OH',
        zip: '44820',
        latitude: '40.787970',
        longitude: '-82.986410',
      },
      {
        id: 14,
        address1: '4779 Great Northern Blvd',
        address2: '',
        city: 'North Olmsted',
        state: 'OH',
        zip: '44070',
        latitude: '41.419580',
        longitude: '-81.901100',
      },
      {
        id: 15,
        address1: '35830 Detroit Rd',
        address2: '',
        city: 'Avon',
        state: 'OH',
        zip: '44011',
        latitude: '41.458649',
        longitude: '-82.016342',
      },
      {
        id: 16,
        address1: '203 Market Street',
        address2: '',
        city: 'Westlake',
        state: 'OH',
        zip: '44145',
        latitude: '41.457481',
        longitude: '-81.951080',
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
