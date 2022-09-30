<template>
    <div id="pet-container">
        <div class="flip-card" v-for="pet in filterPets" :key="pet.name">
            <div class="flip-card-inner">
                <div class="flip-card-front">
                    <img class="front-img" src="../assets/doggo3.jpg" alt="doggo" >
                </div>
                <div class="flip-card-back" >
                    <h4>{{ pet.petName }}</h4>
                    <p>I am a {{ pet.petType }}</p>
                    <p>My breed is {{pet.breed}}</p>
                    <p>My personality</p>
                    <div>
                        <ul>
                            <li v-for="n in pet.personalityTypes" :key="n">{{ n }}</li>
                        </ul>
                    </div>
                    <div>
                        <p>About Me</p>
                        <p>{{ pet.description }}</p>  
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import petService from '../services/petService'
    export default {
        name: 'pet-list',
        data() {
            return {
              currentOwnerId: 0,
                pets: [
                    {
                        petName: 'Fido',
                        petType: 'dog',
                        breed: 'Yorkie',
                        img: 'doggo1',
                        personalityTypes: ['Friendly', 'Hyper', 'BALL'],
                        description: "Doggo ipsum you are doing me a frighten the neighborhood pupper heckin good boys doing me a frighten boofers most angery pupper I have ever seen, such treat borking doggo fat boi you are doing me a frighten. Woofer you are doing me the shock snoot waggy wags what a nice floof blop doing me a frighten, pats heckin good boys"
                    },
                    {
                        petName: 'Stella',
                        petType: 'dog',
                        breed: 'Lab mix',
                        img: 'doggo2',
                        personalityTypes: ['Serious', 'Shy'],
                        description: "Doggo ipsum you are doing me a frighten"
                    },
                    {
                        petName: 'Kitty',
                        petType: 'cat',
                        breed: 'Maine Coon',
                        img: 'kitty1.jpg',
                        personalityTypes: ['Friendly', 'Calm', 'Shy', 'Playful'],
                        description: "angery pupper I have ever seen, such treat borking doggo fat boi you are doing me a frighten. Woofer you are doing me the shock snoot waggy wags what a nice floof blop doing me a frighten, pats heckin good boys"
                    }
                ]
            }
        },
        methods: {
            // getImgUrl(pet) {
            //     var images = require.context('../assets/', false, /\.jpg$/)
            //     return images('./' + pet + ".jpg")
            // },
            
        },
        computed: {
          filterPets() {
            return this.pets.filter(pet => pet.ownerId == this.currentOwnerId);
          }
        },
        created() {
          petService.getAllPets().then(res => {
            if (res.status === 200) {
              this.pets = res.data;
            }
          });
          this.currentOwnerId = this.$store.state.user.id;
        }
    }
</script>

<style scoped>
    .front-img {
        width: 300px;
        height: auto;
    }
    .flip-card {
        background-color: transparent;
        width: 300px;
        height: 450px;
        border: 1px solid #f1f1f1;
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

    /* Position the front and back side */
    .flip-card-front, .flip-card-back {
    position: absolute;
    width: 100%;
    height: 100%;
    -webkit-backface-visibility: hidden; /* Safari */
    backface-visibility: hidden;
    }

    /* Style the front side (fallback if image is missing) */
    .flip-card-front {
    background-color: #bbb;
    color: black;
    }

    /* Style the back side */
    .flip-card-back {
    background-color: #A5C9CA;
    color: #2C3333;
    transform: rotateY(180deg);
    }

    .sub-cont {
        border: 2px solid black;
        width: 30%;
        margin: 10px;
    }
    #pet-container {
        display: flex;
        justify-content: flex-end;
    }
</style>