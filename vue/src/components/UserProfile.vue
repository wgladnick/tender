<template>
  <div>
     <h1>Welcome to your profile, {{this.user.username}} </h1>
     <p>Name: {{this.user.firstName}} {{this.user.lastName}}</p>
     <p> Email Address: {{this.user.email}}</p>
     <p>Address: {{this.user.userDetails.address}} {{this.user.userDetails.city}},{{this.user.userDetails.state}} {{this.user.userDetails.zip}} </p>
     <p>Favorite Categories: {{this.user.userDetails.searchCategories}} </p>
     <router-link :to="{name: 'editProfile' }">
     <button type = "submit">Edit My Profile</button>
     </router-link>
      </div>
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

<style>

</style>