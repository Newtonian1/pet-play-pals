<template>
    <div id="pet-container">
        <div class="flip-card" v-for="pet in pets" :key="pet.name">
            <div class="flip-card-inner">
                <div class="flip-card-front" v-for="pic in pics" :key="pic">
                    <img class="front-img" src="../assets/doggo3.jpg" alt="doggo" >
                    <!-- <img class="front-img" :src="getImgUrl(pic)" v-bind:alt="pic"> -->
                </div>
                <div class="flip-card-back" >
                    <h4 id="pet-name">{{ pet.petName }}</h4>
                    <p id="pet-type">I am a {{ pet.petType }}</p>
                    <p id="pet-breed">My breed is {{pet.breed}}</p>
                    <p>My personality</p>
                    <div id="pers-types">
                        <ul id="pers-types">
                            <li id="pers-item" v-for="n in pet.personalityType" :key="n">{{ n }}</li>
                        </ul>
                    </div>
                    <div id="about-me">
                        <p>About Me</p>
                        <p>{{ pet.description }}</p>  
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'pet-list',
        data() {
            return {
                pics: ['doggo1', 'doggo2', 'kitty1'],
                pets: [
                    {
                        petName: 'Fido',
                        petType: 'dog',
                        breed: 'Yorkie',
                        personalityType: ['Friendly', 'Hyper', 'BALL'],
                        description: "Doggo ipsum you are doing me a frighten the neighborhood pupper heckin good boys doing me a frighten boofers most angery pupper I have ever seen, such treat borking doggo fat boi you are doing me a frighten. Woofer you are doing me the shock snoot waggy wags what a nice floof blop doing me a frighten, pats heckin good boys"
                    },
                    {
                        petName: 'Stella',
                        petType: 'dog',
                        breed: 'Lab mix',
                        personalityType: ['Serious', 'Shy'],
                        description: "Doggo ipsum you are doing me a frighten"
                    },
                    {
                        petName: 'Kitty',
                        petType: 'cat',
                        breed: 'Maine Coon',
                        personalityType: ['Friendly', 'Calm', 'Shy', 'Playful'],
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