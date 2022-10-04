import { fireDb, fireStorage } from '@/firebase';
import { ref as dbRef, set } from 'firebase/database';
import { ref as storeRef, uploadBytes, getDownloadURL } from 'firebase/storage'

export default {
  uploadImage(petImage) {
    return set(dbRef(fireDb, 'pet_pictures/'), petImage);
  },
  storeImage(petPicture) {
    let imageRef = storeRef(fireStorage, 'pet_pictures/' + petPicture.name)
    uploadBytes(imageRef, petPicture).then(() => {
      getDownloadURL(imageRef).then(url => {
        return url;
      })
    });
  },
}