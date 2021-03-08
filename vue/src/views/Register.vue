<template>
  <!-- Container -->
  <div id="register-container">
    <!-- Register Form -->
    <form class="form-register" @submit.prevent="register">
      <!-- Header -->
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>

    
      <!-- Alert message for registration errors -->
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>

      <!-- First Name -->
      <b-field label="First Name">
        <b-input v-model="user.firstName" required></b-input>
      </b-field>

      <b-field label="Last Name">
        <b-input v-model="user.lastName" required></b-input>
      </b-field>

      <!-- Email Address -->
      <b-field label="Email Address">
        <b-input
          placeholder="Email"
          type="email"
          icon-pack="fas"
          icon="envelope"
          v-model="user.email"
        >
          <i class="fas fa-envelope"></i>
        </b-input>
      </b-field>

      <!--Username Input -->
      <b-field
        label="Username"
      
      >
        <b-input
          maxlength="15"
          v-model="user.username"
          required
          autofocus
        ></b-input>
      </b-field>

      <div id="app" class="container"></div>
      <!-- Password Input -->

      <b-field label="Password"
      message="minimum requirements: 8 characters, 1 uppercase letter, 1 lowercase letter">
        <b-input
          type="password"
          placeholder="password"
          v-model="user.password"
          password-reveal
          required
        >
        </b-input>
      </b-field>
      <!-- Password Validator -->
     

      <!-- Password Confirmation -->
      <b-field label="Confirm Password">
        <b-input
          type="password"
          placeholder="Password reveal input"
          v-model="user.confirmPassword"
          v-on:focus="checkPassword"
          password-reveal
          required
        >
        </b-input>
      </b-field>

      <!-- Have an account link, links back to login page -->
      <router-link :to="{ name: 'login' }">Have an account? <br /></router-link>

      <!-- submit button - Create Account -->
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        firstName: "",
        lastName: "",
        email:"",
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      password_length: 0,
      contains_eight_characters: false,
      contains_number: false,
      contains_uppercase: false,
      valid_password: false,
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    checkPassword() {
      this.password_length = this.user.password.length;
      

      if (this.password_length > 7) {
        this.contains_eight_characters = true;
      } else {
        this.contains_eight_characters = false;
      }

      this.contains_number = /\d/.test(this.user.password);
      this.contains_uppercase = /[A-Z]/.test(this.user.password);

      if (
        this.contains_eight_characters === true &&
        this.contains_uppercase === true &&
        this.contains_number === true
      ) {
        this.valid_password = true;
      } else {
        this.valid_password = false;
      }
    },
    register() {

      if(this.valid_password === false){
         this.registrationErrors = true;
        this.registrationErrorMsg = "Your password does not meet the requirements";
      }
      //Checks is passwords match
     else if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        // if registration comes back successful
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })

          //catches error
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },

    //When is this used?
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
ul {
  padding-left: 20px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

li {
  margin-bottom: 8px;
  color: #525f7f;
  position: relative;
}

li:before {
  content: "";
  width: 0%;
  height: 2px;
  background: #2ecc71;
  position: absolute;
  left: 0;
  top: 50%;
  display: block;
  transition: all 0.6s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}

#register-container {
  width: 50%;
}
.is_valid {
  color: rgba(136, 152, 170, 0.8);
}
.is_valid:before {
  width: 100%;
}

.checkmark_container {
  border-radius: 50%;
  position: absolute;
  top: -15px;
  right: -15px;
  background: #2ecc71;
  width: 50px;
  height: 50px;
  visibility: hidden;
  opacity: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: opacity 0.4s ease;
}

.show_checkmark {
  visibility: visible;
  opacity: 1;
}

.checkmark {
  width: 100%;
  height: 100%;
  fill: none;
  stroke: white;
  stroke-width: 15;
  stroke-linecap: round;
  stroke-dasharray: 180;
  stroke-dashoffset: 180;
}

.checked {
  animation: draw 0.5s ease forwards;
}

@keyframes draw {
  to {
    stroke-dashoffset: 0;
  }
}
</style>
