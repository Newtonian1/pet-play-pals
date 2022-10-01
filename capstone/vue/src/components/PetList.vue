<template>
    <div id="pet-container">
        <div class="flip-card" id="myCard" v-for="pet in filterPets" :key="pet.name">
            <div class="flip-card-inner">
                <div class="flip-card-front">
                    <img class="front-img" src="../assets/doggo3.jpg" alt="doggo" >
                    <!-- <img class="front-img" :src="getImgUrl(pic)" v-bind:alt="pic"> -->
                </div>
                <div class="flip-card-back" >
                    <h4 id="pet-name">{{ pet.petName }}</h4>
                    <p id="pet-type">I am a {{ pet.petType }}</p>
                    <p id="pet-breed" v-show="pet.breed !== ''">My breed is {{pet.breed}}</p>
                    <p>My personality</p>
                    <div id="pers-types">
                        <ul id="pers-list">
                            <li id="pers-item" v-for="n in pet.personalityTypes" :key="n">{{ n }}</li>
                        </ul>
                    </div>
                    <div id="about-me" v-show="pet.description !== ''">
                        <p>About Me</p>
                        <p>{{ pet.description }}</p>  
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import petService from '../services/petService';
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
                        personalityTypes: ['Friendly', 'Hyper', 'BALL'],
                        description: "Doggo ipsum you are doing me a frighten the neighborhood pupper heckin good boys doing me a frighten boofers most angery pupper I have ever seen, such treat borking doggo fat boi you are doing me a frighten. Woofer you are doing me the shock snoot waggy wags what a nice floof blop doing me a frighten, pats heckin good boys"
                    },
                    {
                        petName: 'Stella',
                        petType: 'dog',
                        breed: 'Lab mix',
                        personalityTypes: ['Serious', 'Shy'],
                        description: "Doggo ipsum you are doing me a frighten"
                    },
                    {
                        petName: 'Kitty',
                        petType: 'cat',
                        breed: 'Maine Coon',
                        personalityTypes: ['Friendly', 'Calm', 'Shy', 'Playful'],
                        description: "angery pupper I have ever seen, such treat borking doggo fat boi you are doing me a frighten. Woofer you are doing me the shock snoot waggy wags what a nice floof blop doing me a frighten, pats heckin good boys"
                    }
                ]
            }
        },
        created () {
            petService.getAllPets().then(res => {
                if(res.status === 200) {
                    this.pets = res.data;
                }
            });
            this.currentOwnerId = this.$store.state.user.id;
        },
        computed: {
            filterPets() {
                return this.pets.filter(pet => pet.ownerId == this.currentOwnerId);
            },
            flipCard() {
               return document.querySelector("#myCard").classList.toggle("flip");
            }
        },
        methods: {
            // getImgUrl(pet) {
            //     var images = require.context('../assets/', false, /\.jpg$/)
            //     return images('./' + pet + ".jpg")
            // },
            
        }
    }
</script>

<style scoped>
    #pet-container {
        display: flex;
        justify-content: center;
        flex-wrap: wrap;
    }

    #pet-name {
        border-bottom: 1px solid #2C3333;
        margin: 0.4em;
        padding: 5px;
    }

    #pet-type, #pet-breed {
        margin: 0;
        padding: 0.3em;
        background-color: rgba(177, 215, 180, 0.8);
        /* color: #3F4E4F; */
    }
    
    #pet-type {
        border-radius: 5px 5px 0 0;
    }

    #pet-breed {
        border-radius: 0 0 5px 5px;
    }

    #about-me {
        background-color: rgba(177, 215, 180, 0.8);
        text-align: left;
        border-radius: 5px;
        padding: 0.3em;
    }
    
    #about-me p {
        margin: 0.4em;
    }

    #pet-breed + p {
        margin: 0.3em
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
        margin: 1em; 
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
    .flip-card-front, .flip-card-back {
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
        background-color: #A5C9CA;
        background-color: #D3EBCD;
        height: auto;
        color: #2C3333;
        transform: rotateY(180deg);
    }

    @media(max-width: 683px) {
        .flip-card {
            margin: 1em 0;
        }

        #pet-container {
            flex-direction: column;
            align-content: center;
        }

        /* .flip-card:hover .flip-card-inner {
            transform: none;
        }
            
        .flip-card:active .flip-card-inner {
            transform: rotateY(180deg);
        }

        .flip-card-back:active {
            transform: rotateY(180deg);
        } */

    }
</style>