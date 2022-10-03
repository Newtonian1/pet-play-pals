import axios from "axios";

export default {
  uploadImage(imageFile) {
    const formData = new FormData();
    formData.append('image', imageFile, imageFile.name);
    axios.post('/images/upload', formData);
  }
}