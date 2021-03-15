<template>
  <main class="main-section">
      <div id="edit-profile-container">
      <form class="form-edit-profile" @submit.prevent="editProfile">
          <h1 class ="h3 mb-3 font-weight normal">Edit Profile</h1>

          <div class="alert" role="alert" v-if="editProfileErrors">
              {{editProfileErrorMsg}}
          </div>
          <b-field label="First Name">
              <b-input v-model="user.firstName" required></b-input>
          </b-field>
          <b-field label="Last Name">
              <b-input v-model="user.lastName" required></b-input>
          </b-field>
          <b-field label = "Email Address">
              <b-input v-model="user.email">
                  <i class= "fas fa-envelope"></i>
              </b-input>
          </b-field>
          <b-field label="Username">
              <b-input maxLength="15" v-model="user.username" required></b-input>
          </b-field>
          <b-field label="Address">
              <b-input v-model="user.address"> </b-input>
          </b-field>
          <b-field label="City">
              <b-input v-model="user.city"> </b-input>
          </b-field>
          <b-field label="State">
              <b-input v-model="user.state"> </b-input>
          </b-field>
          <b-field label="Zip Code">
              <b-input v-model="user.zip"></b-input>
          </b-field>
          
          <label
            class="container"
            v-for="category in availCategories"
            v-bind:key="category.categoryId"
            >{{ category.displayName }}
            <input
              type="checkbox"
              checked="checked"
              v-bind:id="category.categoryId"
              v-bind:value="category.categoryId"
              v-model.number="categoriesSelected"
            />
            <span class="checkmark"></span>
          </label>


      </form>
      </div>
      </main>
</template>

<script>

export default {
    name:"edit-profile",
    created() {
        this.user = this.$store.state.user;
    },

    data() {
        return  {
            editProfileErrors: false,
            editProfileErrorMsg: "There was an issue editing your profile,",
        }
    },
    methods: {
        editProfile() {
            this.user = [];
            this.$store.commit("UPDATE_USER", this.user);

        }
    }

}
</script>

<style>
h1 {
  text-align: center;
  font-weight: 700;
  font-size: 1.5em;
}
.main-section {
  background-color: #fdf2f2;
  display: flex;
  align-items: center;
  width: 100vw;
  height: 100vh;
  justify-content: center;

}
#edit-profile-container {
    width: 30%;
  align-self: center;
  background-color: white;
  padding-top: 30px;
  padding-bottom: 30px;
  display: flex;
  justify-content: center;
}

.form-edit-profile{
    width: 75%;
}

</style>