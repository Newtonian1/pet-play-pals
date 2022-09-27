<template>
    <div class="reg-pet">
        <form id="pet-reg-form">
            <h1>Register A Pet</h1>
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



        </form>
    </div>
</template>

<script>
export default {

}
</script>

<style>

</style>