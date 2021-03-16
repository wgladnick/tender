<template>
<main >
  <div id="profile-container">
     <h1>Welcome to your profile, {{this.user.username}} </h1>
     <p>Name: {{this.user.firstName}} {{this.user.lastName}}</p>
     <p> Email Address: {{this.user.email}}</p>
     <p>Address: {{this.user.userDetails.address}} {{this.user.userDetails.city}},{{this.user.userDetails.state}} {{this.user.userDetails.zip}} </p>
     <p>Favorite Categories: {{this.user.userDetails.searchCategories}} </p>
     <router-link :to="{name: 'editProfile' }">
     <button type = "submit">Edit My Profile</button>
     </router-link>
      </div>
      </main>
</template>

<script>

export default {
    name: "user-profile",
    created() {
        this.user = this.$store.state.user;
    },
    data() {
        return {
            user: {
                
            },
        }
    }


}
</script>

<style scoped>
h1 {
  text-align: center;
  font-weight: 700;
  font-size: 1.5em;
}
p {
  text-align: center;
  font-size: 1.5em;

}
button {
  background-color: #dc6b67;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin-left: auto;
  margin-right: auto;
  cursor: pointer;
  border-radius: 5px;
  width: 100%;
  margin-top: 30px;
}

</style>