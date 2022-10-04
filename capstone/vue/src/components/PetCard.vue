<template>
  <div class="flip-card" id="myCard">
    <div class="flip-card-inner">
      <div class="flip-card-front">
        <img class="front-img" :src="petImageUrl" alt="pic" />
      </div>
      <div class="flip-card-back">
        <h4 id="pet-name">{{ pet.petName }}</h4>
        <p id="pet-type">I am a {{ pet.petType }}</p>
        <p id="pet-breed" v-show="pet.breed !== ''">
          My breed is {{ pet.breed }}
        </p>
        <p>My personality</p>
        <div id="pers-types">
          <ul id="pers-list">
            <li id="pers-item" v-for="n in pet.personalityTypes" :key="n">
              {{ n }}
            </li>
          </ul>
        </div>
        <div id="about-me" v-show="pet.description !== ''">
          <p>About Me</p>
          <p>{{ pet.description }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "pet-card",
  data() {
    return {
      currentOwnerId: 0,
      currentPetId: 0,
      currentPetUrl: "",
    };
  },
  props: ["pet"],
  created() {
    this.currentOwnerId = this.$store.state.user.id;
    this.currentPetId = this.pet.petId;
  },
  computed: {
    flipCard() {
      return document.querySelector("#myCard").classList.toggle("flip");
    },
    petImageUrl() {
      let url = this.$store.state.petPictures.find(
        (pet) => pet.id == this.currentPetId
      );
      if (url !== undefined && url !== null) {
        return url.url;
      } else {
        let type = "doggo";
        if (this.pet.petType === "cat") {
          type = "kitty";
        }
        url = require("../assets/" +
          type +
          (Math.floor(Math.random() * 3) + 1) +
          ".jpg");
        return url;
      }
    },
  },
};
</script>

<style scoped>
#pet-name {
  border-bottom: 1px solid #2c3333;
  margin: 0.4em;
  padding: 5px;
}

#pet-type,
#pet-breed {
  margin: 0;
  padding: 0.3em;
  /* color: #3F4E4F; */
}

#pet-type,
#pet-breed,
#about-me {
  background-color: rgb(245, 255, 233);
  background-color: #f3e8a9;
}

#pet-type {
  border-radius: 5px 5px 0 0;
}

#pet-breed {
  border-radius: 0 0 5px 5px;
}

#about-me {
  text-align: left;
  border-radius: 5px;
  padding: 0.3em;
}

#about-me p {
  margin: 0.4em;
}

#pet-breed + p {
  margin: 0.3em;
}

#pers-list {
  margin: 0.3em;
  padding: 0;
  display: flex;
  list-style: none;
  justify-content: space-evenly;
  flex-wrap: wrap;
}

#pers-item {
  margin: 4px 7px;
}

.front-img {
  /* Note from Kat: I found this object-fit property and used it to crop
  the image to a square and get rid of the overlap on the pics. I had to 
  change the height property here and in .flip-card, as well as the wrap
  setting in pet-container. Let me know what you think! */
  object-fit: cover;
  width: 300px;
  height: 300px;
  border-radius: 5px;
}

.flip-card {
  background-color: transparent;
  width: 300px;
  height: 300px;
  margin: 0.4em;
  /* original height 450px - changing height to auto effects background */
  /* border: 1px solid #f1f1f1; */
  perspective: 1000px; /* Remove this if you don't want the 3D effect */
}

/* This container is needed to position the front and back side */
.flip-card-inner {
  position: relative;
  width: 100%;
  height: 100%;
  text-align: center;
  transition: transform 0.8s;
  transform-style: preserve-3d;
}

/* Do an horizontal flip when you move the mouse over the flip box container */
.flip-card:hover .flip-card-inner {
  transform: rotateY(180deg);
}

.flip .flip-card-inner {
  transform: rotateY(180deg);
}

/* Position the front and back side */
.flip-card-front,
.flip-card-back {
  position: absolute;
  width: 100%;
  height: 100%;
  -webkit-backface-visibility: hidden; /* Safari */
  backface-visibility: hidden;
  border-radius: 5px;
}

/* Style the front side (fallback if image is missing) */
.flip-card-front {
  background-color: #bbb;
  color: black;
}

/* Style the back side */
.flip-card-back {
  background-color: #a5c9ca;
  height: auto;
  color: #2c3333;
  padding-bottom: 5px;
  transform: rotateY(180deg);
}

@media (max-width: 700px) {
  .flip-card {
    margin: 1em 0;
  }
}
</style>