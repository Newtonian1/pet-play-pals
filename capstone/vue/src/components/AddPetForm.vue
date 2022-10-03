<template>
    <div class="reg-pet">
        <form id="pet-reg-form" @submit.prevent="savePet">
            <div class="form-element name-field">
                <label for="pet-name">Name </label>
                <input type="text" id="pet-name" placeholder="Pet Name" required autofocus v-model="newPet.petName"/>
            </div>
            <div class="form-element type-field">
                <label for="dog" class="inline">Dog</label>
                <input type="radio" name="cat-or-dog" id="dog" value="dog" required v-model="newPet.petType">
                <label for="cat" class="inline">Cat</label>
                <input type="radio" name="cat-or-dog" id="cat" value="cat" v-model="newPet.petType">
            </div>
            <div class="form-element breed-field">
                <label for="breed">Breed </label>
                <input type="text" id="breed" placeholder="Yorkie" autofocus v-model="newPet.breed"/>
            </div>
            <div class="form-element gender-field">
                <label for="gender">Gender </label>
                <select name="gender-dropdown" id="gender" required v-model="newPet.gender"> 
                    <option value="" disabled> --- </option>
                    <option value="female">Female</option> 
                    <option value="male">Male</option> 
                </select> 
            </div>
            <div class="form-element size-field">
                <label for="size">Size </label>
                <select name="weight-range" id="size" required v-model="newPet.size"> 
                    <option value="" disabled> --- </option>
                    <option value="extra-small">Extra Small 0-10 lbs</option> 
                    <option value="small">Small 11-25 lbs</option> 
                    <option value="medium">Medium 26-45 lbs</option> 
                    <option value="large">Large 46-70 lbs</option> 
                    <option value="extra-large">Extra Large 70+ lbs</option> 
                </select>
            </div> 
            <div class="form-element vaccine-field">
                <label for="vax" class="inline">Up to date on vaccines?</label>
                <input type="checkbox" id="vax" value="" v-model="newPet.isUpToDateWithVaccinations" />
            </div>
            <div class="form-element fixed-field">
                <label for="fixed" class="inline">Spayed or Neutered?</label>
                <input type="checkbox" id="fixed" value="" v-model="newPet.isFixed"/>
            </div>
            <div class="form-element personality-field">
                <p>What is your pet's personality?</p>
                <div style="visibility:hidden; color:red; " id="chk_option_error">
                    <p id="errMsg" class="displayNone">Please select at least one option.</p>
                </div>
                <div class="trait-container">
                    <div class="group-1">
                        <input type="checkbox" id="friendly" name="personality-list" value="friendly" v-model="newPet.personalityTypes"/>
                        <label for="friendly" class="inline">Friendly</label>
                        <br>
                        <input type="checkbox" id="hyper" name="personality-list" value="hyper" v-model="newPet.personalityTypes"/>
                        <label for="hyper" class="inline">Hyper</label>
                        <br>
                        <input type="checkbox" id="serious" name="personality-list" value="serious" v-model="newPet.personalityTypes"/>
                        <label for="serious" class="inline">Serious</label>
                        <br>
                        <input type="checkbox" id="shy" name="personality-list" value="shy" v-model="newPet.personalityTypes"/>
                        <label for="shy" class="inline">Shy</label>
                        <br>
                    </div>
                    <div class="group-2">
                        <input type="checkbox" id="calm" name="personality-list" value="calm" v-model="newPet.personalityTypes"/>
                        <label for="calm" class="inline">Calm</label>
                        <br>
                        <input type="checkbox" id="loner" name="personality-list" value="loner" v-model="newPet.personalityTypes"/>
                        <label for="loner" class="inline">Loner</label>
                        <br>
                        <input type="checkbox" id="ball" name="personality-list" value="ball" v-model="newPet.personalityTypes"/>
                        <label for="ball" class="inline">BALL</label>
                        <br>
                        <input type="checkbox" id="playful" name="personality-list" value="playful" v-model="newPet.personalityTypes"/>
                        <label for="playful" class="inline">Playful</label>
                    </div>
                </div>
            </div>
            <div class="form-element about-field">
                <p><label for="abt-me">A little bit more about me!</label></p>
                <textarea id="abt-me" cols="35" rows="10" maxlength="1500" v-model="newPet.description"></textarea>
            </div>

            <button type="submit" id="create-pet-btn" >Add Pet</button>


        </form>
        <image-upload />
    </div>
</template>

<script>
import petService from '../services/petService';
import ImageUpload from './ImageUpload.vue';
export default {
  components: { ImageUpload },
    name: "add-pet",
    data() {
        return {
            newPet: {
                ownerId: 0,
                petName: "",
                petType: "",
                breed: "",
                gender: "",
                size: "",
                isUpToDateWithVaccinations: false,
                isFixed: false,
                personalityTypes: [],
                description: ""
            }  
        }
    },
    methods: {
        savePet() {
            this.newPet.ownerId = this.$store.state.user.id;
            if(!this.handleCheckbox()) {
                return
            }
            petService.registerPet(this.newPet)
            .then((response) => {
                if (response.status == 201) {
                    this.sendToHome();
                }
            })
        },
        resetForm() {
            this.newPet = {};
        },
        sendToHome () {
            this.resetForm();
            this.$router.push("/");
        },
        handleCheckbox() {
            //const myForm = this.$el.querySelector("#pet-reg-form");
            if(this.newPet.personalityTypes.length < 1) {
                this.$el.querySelector("#chk_option_error").style.visibility = "visible";
                this.$el.querySelector("#errMsg").classList.remove("displayNone");
                
                return false;
                
            } else {
                this.$el.querySelector("#chk_option_error").style.visibility = "hidden";
                this.$el.querySelector("#errMsg").classList.add("displayNone");
                return true;
            }
        }
    }
}
</script>

<style scoped>
   .reg-pet {
    max-width: 80%;
    margin: auto;
    font-family: 'Montserrat', sans-serif;
    color: #2C3333;
    }

    #pet-reg-form{
        font-size: 15px;
    }

    .form-element {
        margin: 15px 0;
        font-size: 1.3em;
    }

    label {
        display: block;
    }

    .inline {
        display: inline;
    }

    input[type="text"] {
        line-height: 25px;
        font-size: 18px;
        border-radius: 5px;
        border: 1px solid black;
    }

    #dog {
        margin-right: 1.5em;
    }

    #errMsg {
        margin: 0;
    }

    .displayNone {
        display: none;
    }

    select {
        font-size: 20px;
    }

    #create-pet-btn {
        background-color: #395B64;
        font-family: monospace;
        font-size: 18px;
        color: white;
        width: 100%;
        border: none;
        border-radius: 5px;
        height: 35px;
        letter-spacing: 2px;
        transition: all .3s ease;
        max-width: 540px;
        margin-bottom: 30px;
    }

    #create-pet-btn:hover {
        background-color: #A5C9CA;
        color: #2C3333;
    }

    @media(min-width:670px) {
        label {
            display: inline;
        }

        textarea {
            width: 535px;
        }

        .trait-container {
            display: flex;
            justify-content: flex-start;
        }

        .group-1, .group-2 {
            margin: 15px;
        }

    }

    @media(min-width:920px) {
        .reg-pet {
            text-align: center;
        }
        
        #pet-reg-form {
            display: inline-block;
            text-align: left;
            
        }

        #dog {
            margin-right: 3em;
        }

        .trait-container {
            display: flex;
            
        }
    }

</style>