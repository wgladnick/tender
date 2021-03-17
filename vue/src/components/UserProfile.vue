<template>
  <main class="body">

    <section class="card">
      <div id="profile-container">
        <h1>Welcome to your profile, {{ this.user.username }}</h1>
        <p class="details">Name: {{ this.user.firstName }} {{ this.user.lastName }}</p>
        <p class="details">Email Address: {{ this.user.email }}</p>
        <p class="details">
          Address: {{ this.user.userDetails.address }}
          {{ this.user.userDetails.city }},{{ this.user.userDetails.state }}
          {{ this.user.userDetails.zip }}
        </p>
        <p class="details">Favorite Categories: {{ this.user.userDetails.searchCategories }}</p>
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
  height: 75vh;
}


h1 {
  font-weight: 700;
  font-size: 1.5em;
}
p {
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