<template>

<!-- Container -->
  <div id="register" class="text-center">

    <!-- Register Form -->
    <form class="form-register" @submit.prevent="register">

        <!-- Header -->
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>

        <!-- Alert message for registration errors -->
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>

      <!-- First Name -->
       <b-field label="Name">
            <b-input v-model="user.firstName"></b-input>
        </b-field>

               <b-field label="Last Name">
            <b-input v-model="user.lastName"></b-input>
        </b-field>


<!-- Email Address -->
        <b-field>
            <b-input placeholder="Email"
                type="email"
                icon-pack="fas"
                icon="envelope"
                
                v-model="user.emailAddress">
                <i class="fas fa-envelope"></i>


            </b-input>
        </b-field>

  



      <!--Username Input -->
       <b-field label="Username"
            type="is-success"
            message="This username is available">
            <b-input maxlength="15" 
            v-model="user.username" 
            required 
            autofocus></b-input>
        </b-field>
     

      
      
      <!-- Password Input -->
      <b-field>
            <b-input type="password"
                placeholder="Password reveal input"
                password-reveal>
            </b-input>
        </b-field>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />

      <!-- Password Confirmation Input -->
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />

      <!-- Have an account link, links back to login page -->
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
     
      <!-- submit button - Create Account -->
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>

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
        firstName:'',
        lastName:'',
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
      //Checks is passwords match
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        
        // if registration comes back successful
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })

          //catches error
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },

    //When is this used?
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style></style>
