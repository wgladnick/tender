<template>
  <main class="body">

    <section class="card is-fullheight">
      <div id="profile-container">
        <h1 class="is-size-3 has-text-weight-semibold">Welcome, <span class="username">{{ this.user.username }}</span></h1><br />
        <div class="details"><span class="details-header is-size-4">Name:<br /></span><span class="pb-1"> {{ this.user.firstName }} {{ this.user.lastName }}</span></div>
        <div class="details"><span class="details-header is-size-4">Email Address:<br /></span><span class="pb-1"> {{ this.user.email }}</span></div>
        <div class="details"><span class="details-header is-size-4">
          Address: <br /></span><span class="pb-1"> {{ this.user.userDetails.address }}
          {{ this.user.userDetails.city }},{{ this.user.userDetails.state }}
          {{ this.user.userDetails.zip }}
        </span></div>
        <div class="details"><span class="details-header">Default Search Categories:<br /></span><span class="pb-1"> {{ this.user.userDetails.searchCategories }} </span></div>
        <router-link :to="{ name: 'editProfile' }">
          <button class="btn" type="submit">Edit My Profile</button>
        </router-link>
      </div>
    </section>


  </main>
</template>

<script>
export default {
  name: "user-profile",
  created() {
    this.user = this.$store.state.user;
    console.log("profile page: " + this.user.userDetails.searchCategories);
  },
  data() {
    return {
      user: {},
    };
  },
};
</script>

<style scoped>
.body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
}

.card {
  width: 70%;
  margin-top: 25px;
  margin-bottom: 25px;
  padding: 50px;
  justify-content: left;

}
.details-header{
  font-weight:bold;
  color:#dc6b67;
}
.details {
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

.btn:hover {
  background-color: #f7a09d;
}

</style>