import axios from 'axios';

export default {

  getPlayDates() {
    return axios.get('/playdates');
  },

  getLocations() {
    return axios.get('/locations');
  },

  getLocationById(locationId){
    return axios.get(`/locations/${locationId}`)
  },
  createLocation(location){
    return axios.post(`/locations`, location);
  },
  deleteLocation(locationId){
    return axios.delete(`/locations/${locationId}`)
  },
  updateLocation(updatedLocation,locationId){
    return axios.put(`/locations/${locationId}`,updatedLocation)
  }

}