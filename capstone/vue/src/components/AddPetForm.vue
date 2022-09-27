<template>
  <div class="reg-pet">
        <form id="pet-reg-form">
            <label for="pet-name">Name </label>
            <input type="text" id="pet-name" placeholder="Pet Name"
                required autofocus />

            <label for="cat">Cat</label>
            <input type="radio" name="cat-or-dog" id="cat">
            <label for="dog">Dog</label>
            <input type="radio" name="cat-or-dog" id="dog">
            <br>
            <label for="breed">Breed </label>
            <input type="text" id="breed" placeholder="Yorkie"
                required autofocus />

            <label for="gender">Gender </label>
            <select name="gender-dropdown" id="gender" required> 
                <option value="male">Male</option> 
                <option value="female">Female</option> 
            </select> 
            <br>
            <label for="size">Size </label>
            <select name="weight-range" id="size" required> 
                <option value="extra-small">Extra Small 0-10 lbs</option> 
                <option value="small">Small 11-25 lbs</option> 
                <option value="medium">Medium 26-45 lbs</option> 
                <option value="large">Large 46-70 lbs</option> 
                <option value="extra-large">Extra Large 70+ lbs</option> 
            </select> 

            <label for="vax">Up to date on all vaccines?</label>
            <input type="checkbox" id="vax" value="true" />
            <br>
            <label for="fixed">Neutered or Spayed?</label>
            <input type="checkbox" id="fixed" value="true" />
            <br>
            <span>What is your pet's personality?</span>
            <br>
            <input type="checkbox" id="friendly" name="personality-list" value="" />
            <label for="friendly">Friendly</label>
            <br>
            <input type="checkbox" id="hyper" name="personality-list" value="" />
            <label for="hyper">Hyper</label>
            <br>
            <input type="checkbox" id="serious" name="personality-list" value="" />
            <label for="serious">Serious</label>
            <br>
            <input type="checkbox" id="shy" name="personality-list" value="" />
            <label for="shy">Shy</label>
            <br>
            <input type="checkbox" id="calm" name="personality-list" value="" />
            <label for="calm">Calm</label>
            <br>
            <input type="checkbox" id="loner" name="personality-list" value="" />
            <label for="loner">Loner</label>
            <br>
            <input type="checkbox" id="ball" name="personality-list" value="" />
            <label for="ball">BALL</label>
            <br>
            <input type="checkbox" id="playful" name="personality-list" value="" />
            <label for="playful">Playful</label>
            <br>
            <p><label for="abt-me">A little bit more about me!</label></p>
            <textarea id="abt-me" cols="40" rows="10" maxlength="1500"></textarea>

            <button>Create Pet</button>


        </form>
    </div>
</template>

<script>

export default {
name: "add-pet",

}
</script>

<style>

</style>