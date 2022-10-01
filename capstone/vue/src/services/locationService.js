import axios from "axios";

export default {
  getLocations() {
    return axios.get('/locations');
  },

  getLocationById(locationId){
    return axios.get(`/locations/${locationId}`)
  },
}