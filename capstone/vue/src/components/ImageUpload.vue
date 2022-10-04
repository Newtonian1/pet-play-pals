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
      if (this.selectedFile) {
        const petImage = {
          key: 0,
          petId: 2001,
          petPicture: this.selectedFile,
        };
        imageService.storeImage(petImage.petPicture);
        imageService.uploadImage(petImage);

        // }).then(key => {
        //   const filename = petImage.petPicture.name;
        //   const ext = filename.slice(filename.lastIndexOf('.'));
        //   return imageService.storeImage(key + '.' + ext, petImage.petPicture);
        // })
      }
    },
  },
};
</script>

<style>
</style>