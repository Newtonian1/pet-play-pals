import { fireDb, fireStorage } from '@/firebase';
import { ref as dbRef, set, onValue } from 'firebase/database';
import { ref as storeRef, uploadBytes, getDownloadURL } from 'firebase/storage'

export default {
  uploadImage(petImage) {
    return set(dbRef(fireDb, `pet_pictures/${petImage.id}`), petImage);
  },
  storeImage(petPicture, petId) {
    let imageRef = storeRef(
      fireStorage,
      `pet_pictures/${petId}${petPicture.name.slice(
        petPicture.name.lastIndexOf('.')
      )}`);
    uploadBytes(imageRef, petPicture).then(() => {
      getDownloadURL(imageRef).then(url => {
        const petImage = {
          id: petId,
          url: url
        };
        this.uploadImage(petImage);
      });
    });
  },
  getImageUrlById(id) {
    onValue(dbRef(fireDb, `pet_pictures/${id}`), (snapshot) => {
      return snapshot.val().url;
    }, {
      onlyOnce: true
    });
  }
}