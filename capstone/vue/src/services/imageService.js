import { fireDb, fireStorage } from '@/firebase';
import { ref as dbRef, set, onValue, } from 'firebase/database';
import { ref as storeRef, uploadBytes, getDownloadURL } from 'firebase/storage'
import store from '../store'

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
        this.getImageUrls();
        // store.commit('ADD_TO_PET_PICTURES', {
        //   id: petImage.id,
        //   url: petImage.url
        // })
      });
    });
  },
  // getImageUrlById(id) {
  //   onValue(dbRef(fireDb, `pet_pictures/${id}`), (snapshot) => {
  //     if (snapshot.exists()) {
  //       store.commit('ADD_TO_PET_PICTURES', {
  //         id: snapshot.val().id,
  //         url: snapshot.val().url
  //       })
  //     } 
  //   }, {
  //     onlyOnce: true
  //   });
  // },
  getImageUrls() {
    
    onValue(dbRef(fireDb, 'pet_pictures/'), (snapshot) => {
      if (snapshot.exists()) {
        if (snapshot.exists()) {
          store.commit('RESET_PET_PICTURES');
          snapshot.forEach(child => {
            console.log(child.val());
            store.commit('ADD_TO_PET_PICTURES', {
              id: child.val().id,
              url: child.val().url
            })
          })
        }
      }
    }, {
      onlyOnce: true
    });
  },
}