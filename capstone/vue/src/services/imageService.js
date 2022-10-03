import axios from "axios";

export default {
  uploadImage(petImage) {
    return axios.post('/images/upload', petImage);
  }
}