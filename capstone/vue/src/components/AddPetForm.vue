<template>
    <div class="reg-pet">
        <form id="pet-reg-form">
            <div class="form-element name-field">
                <label for="pet-name">Name </label>
                <input type="text" id="pet-name" placeholder="Pet Name" required autofocus v-model="newPet.name"/>
            </div>
            <div class="form-element type-field">
                <label for="dog" class="inline">Dog</label>
                <input type="radio" name="cat-or-dog" id="dog" value="dog" v-model="newPet.type">
                <label for="cat" class="inline">Cat</label>
                <input type="radio" name="cat-or-dog" id="cat" value="cat" v-model="newPet.type">
            </div>
            <div class="form-element breed-field">
                <label for="breed">Breed </label>
                <input type="text" id="breed" placeholder="Yorkie" required autofocus v-model="newPet.breed"/>
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
                <input type="checkbox" id="vax" value="" v-model="newPet.vaccine" />
            </div>
            <div class="form-element fixed-field">
                <label for="fixed" class="inline">Spayed or Neutered?</label>
                <input type="checkbox" id="fixed" value="" v-model="newPet.fixed"/>
            </div>
            <div class="form-element personality-field">
                <p>What is your pet's personality?</p>
                <div class="trait-container">
                    <div class="group-1">
                        <input type="checkbox" id="friendly" name="personality-list" value="friendly" v-model="newPet.personality"/>
                        <label for="friendly" class="inline">Friendly</label>
                        <br>
                        <input type="checkbox" id="hyper" name="personality-list" value="hyper" v-model="newPet.personality"/>
                        <label for="hyper" class="inline">Hyper</label>
                        <br>
                        <input type="checkbox" id="serious" name="personality-list" value="serious" v-model="newPet.personality"/>
                        <label for="serious" class="inline">Serious</label>
                        <br>
                        <input type="checkbox" id="shy" name="personality-list" value="shy" v-model="newPet.personality"/>
                        <label for="shy" class="inline">Shy</label>
                        <br>
                    </div>
                    <div class="group-2">
                        <input type="checkbox" id="calm" name="personality-list" value="calm" v-model="newPet.personality"/>
                        <label for="calm" class="inline">Calm</label>
                        <br>
                        <input type="checkbox" id="loner" name="personality-list" value="loner" v-model="newPet.personality"/>
                        <label for="loner" class="inline">Loner</label>
                        <br>
                        <input type="checkbox" id="ball" name="personality-list" value="ball" v-model="newPet.personality"/>
                        <label for="ball" class="inline">BALL</label>
                        <br>
                        <input type="checkbox" id="playful" name="personality-list" value="playful" v-model="newPet.personality"/>
                        <label for="playful" class="inline">Playful</label>
                    </div>
                </div>
            </div>
            <div class="form-element about-field">
                <p><label for="abt-me">A little bit more about me!</label></p>
                <textarea id="abt-me" cols="35" rows="10" maxlength="1500" v-model="newPet.about"></textarea>
            </div>

            <button id="create-pet-btn">Create Pet</button>


        </form>
    </div>
</template>

<script>

export default {
name: "add-pet",
data() {
    return {
      newPet: {
          name: "",
          type: "",
          breed: "",
          size: "",
          vaccine: false,
          fixed: false,
          personality: [],
          about: ""
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