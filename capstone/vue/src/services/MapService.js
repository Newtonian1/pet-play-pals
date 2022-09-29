import axios from 'axios';

export default {

  getCoords(address) {
    return axios.get(`https://maps.googleapis.com/maps/api/geocode/json?address=${address}&key=AIzaSyAH3vglNrAne06DiZX94DP_KT0uMNxYD4k`)
  },

}