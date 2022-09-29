import axios from 'axios';

export default {

  getPlayDates() {
    return axios.get('/playdates');
  },

  getLocations() {
    return axios.get('/locations');
  },

}