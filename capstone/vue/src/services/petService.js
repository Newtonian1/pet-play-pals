import axios from 'axios';

export default {
  registerPet(newPet) {
    return axios.post('/pets', newPet);
  }
}