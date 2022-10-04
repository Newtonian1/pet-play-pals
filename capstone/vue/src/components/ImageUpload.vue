<template>
  <div>
    <input
      style="display: none"
      type="file"
      @change="onFileSelected"
      ref="fileInput"
      accept="image/*"
    />
    <button @click="$refs.fileInput.click()">Choose File</button>
    <button @click="onUpload">Upload</button>
    <img v-show="imageUrl" :src="imageUrl" height="150px" />
  </div>
</template>

<script>
import imageService from "../services/imageService";
export default {
  data() {
    return {
      selectedFile: null,
      imageUrl: "",
      image: null,
      petId: 2001,
    };
  },
  methods: {
    onFileSelected(event) {
      console.log(event);
      this.selectedFile = event.target.files[0];
      let filename = this.selectedFile.name;
      if (filename.lastIndexOf(".") <= 0) {
        console.log("invalid file");
        return;
      }
      const fileReader = new FileReader();
      fileReader.addEventListener("load", () => {
        this.imageUrl = fileReader.result;
      });
      fileReader.readAsDataURL(this.selectedFile);
    },
    onUpload() {
      // let petPictureUrl;
      let key;
      if (this.selectedFile) {
        const petImage = {
          petId: 2001,
          petPicture: this.selectedFile,
        };
        imageService
          .uploadImage(petImage)
          .then((res) => {
            key = res.key;
            console.log(petImage.petPicture.name)
            return key;
          }).then(key => {
            const filename = petImage.petPicture.name;
            const ext = filename.slice(filename.lastIndexOf('.'));
            return imageService.storeImage(key + '.' + ext, petImage.petPicture);
          }).then(res => {
            // let url = imageService.getDownloadUrl(res.metadata.fullPath);
            console.log(res);
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
  },
};
</script>

<style>
</style>