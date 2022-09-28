<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              authService
                .login(this.user)
                .then(response => {
                  if (response.status == 200) {
                    this.$store.commit("SET_AUTH_TOKEN", response.data.token);
                    this.$store.commit("SET_USER", response.data.user);
                    //this.$router.push("/");
                  }
                })
                .catch(error => {
                  const response = error.response;

                  if (response.status === 401) {
                    this.invalidCredentials = true;
                  }
                });
              this.$router.push({
                path: '/registerPet',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
  #register {
    max-width: 80%;
    margin: auto;
    font-family: 'Montserrat', sans-serif;
    color: #2C3333;
  }

  .form-register {
    margin: 15px 0;
    font-size: 1em;
  }

  label.sr-only {
    font-size: 1.3em;
  }

  #username, #password, #confirmPassword {
    max-width: 325px;
    font-size: 18px;
    border-radius: 5px;
    border: 1px solid black;
    line-height: 25px;
    display: block;
    margin: 15px 0;
  }

  button[type="submit"] {
    background-color: #395B64;
    font-family: monospace;
    color: white;
    border: none;
    border-radius: 5px;
    height: 30px;
    letter-spacing: 2px;
    transition: all .3s ease;
    max-width: 540px;
    display: block;
    margin-bottom: 15px;
  }

  button[type="submit"]:hover {
    background-color: #A5C9CA;
    color: #2C3333;
  }

  @media(min-width:670px) {
    #register {
      text-align: center;
    }

    #username, #password, #confirmPassword {
      margin: 15px 0;
    }

    .form-register {
      display: inline-block;
      text-align: left;
    }

    button[type="submit"] {
      /* margin: 0 auto 15px auto; */
      max-width: 540px;
    }
  }
</style>
