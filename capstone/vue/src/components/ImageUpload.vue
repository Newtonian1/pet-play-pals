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
  </div>
</template>

<script>
import imageService from "../services/imageService";
export default {
  data() {
    return {
      selectedFile: null,
    };
  },
  methods: {
    onFileSelected(event) {
      this.selectedFile = event.target.files[0];
    },
    onUpload() {
      if (this.selectedFile) {
        imageService.uploadImage(this.selectedFile).then((res) => {
          console.log(res);
        });
      }
    },
  },
};
</script>

<style>
</style>