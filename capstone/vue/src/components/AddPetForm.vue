<template>
  <div class="reg-pet">
        <form id="pet-reg-form">
            <div class="form-element name-field">
                <label for="pet-name">Name </label>
                <input type="text" id="pet-name" placeholder="Pet Name" required autofocus v-model="newPet.name"/>
            </div>
            <div class="form-element type-field">
                <label for="dog">Dog</label>
                <input type="radio" name="cat-or-dog" id="dog" value="dog" v-model="newPet.type">
                <label for="cat">Cat</label>
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
                <label for="vax">Up to date on all vaccines?</label>
                <input type="checkbox" id="vax" value="" v-model="newPet.vaccine" />
            </div>
            <div class="form-element fixed-field">
                <label for="fixed">Spayed or Neutered?</label>
                <input type="checkbox" id="fixed" value="" v-model="newPet.fixed"/>
            </div>
            <div class="form-element personality-field">
                <p>What is your pet's personality?</p>
                <input type="checkbox" id="friendly" name="personality-list" value="friendly" v-model="newPet.personality"/>
                <label for="friendly">Friendly</label>
                <br>
                <input type="checkbox" id="hyper" name="personality-list" value="hyper" v-model="newPet.personality"/>
                <label for="hyper">Hyper</label>
                <br>
                <input type="checkbox" id="serious" name="personality-list" value="serious" v-model="newPet.personality"/>
                <label for="serious">Serious</label>
                <br>
                <input type="checkbox" id="shy" name="personality-list" value="shy" v-model="newPet.personality"/>
                <label for="shy">Shy</label>
                <br>
                <input type="checkbox" id="calm" name="personality-list" value="calm" v-model="newPet.personality"/>
                <label for="calm">Calm</label>
                <br>
                <input type="checkbox" id="loner" name="personality-list" value="loner" v-model="newPet.personality"/>
                <label for="loner">Loner</label>
                <br>
                <input type="checkbox" id="ball" name="personality-list" value="ball" v-model="newPet.personality"/>
                <label for="ball">BALL</label>
                <br>
                <input type="checkbox" id="playful" name="personality-list" value="playful" v-model="newPet.personality"/>
                <label for="playful">Playful</label>
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
    }
    /* .form-element {
        text-align: center;
    } */

</style>