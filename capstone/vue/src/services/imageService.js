import firebase from "firebase/compat";

export default {
  uploadImage(petImage) {
    return firebase.database().ref('pet_pictures').push(petImage);
  },
  storeImage(extension, petPicture) {
    return firebase.storage().ref('pet_pictures/' + extension).put(petPicture);
  },
}