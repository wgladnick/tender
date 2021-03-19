<template>
    <article class="tile is-child notification has-background-white">
      <div class="content">
        <p class="title has-text-centered">User Profile</p>
        <figure class="image is-4by3">
        <img src="https://bulma.io/images/placeholders/1280x960.png" alt="Placeholder image">
      </figure>

        <div class="content">
          <div class="media-content pb-6">
          <p class="title is-size-3">{{ this.user.firstName }} {{ this.user.lastName }}</p>
          <p class="subtitle is-6"> @{{ this.user.username }}</p>
        </div>
        </div>
        <div class="content">
          <span class="details-header is-size-5"> Location <br /></span>
          <span>
            {{ this.user.userDetails.city }}, {{ this.user.userDetails.state }}
          </span>
        </div> 
         <div class="content">
          <span class="details-header is-size-5">Food favs <br /></span>
          <span>
            {{ this.user.userDetails.searchCategories }}
          </span>
        </div>
        <router-link :to="{ name: 'editProfile' }">
          <b-button class="btn is-rounded" type="is-info" size="is-small">Edit My Profile</b-button>
        </router-link>
      </div>
    </article>
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
  margin-bottom: 25px;
  padding: 50px;
  justify-content: left;
}
.details-header {
  font-weight: 500;
}
.btn {
  background-color: #dc6b67;
  text-align: center;
  justify-content: center;
  align-items: center;
  width: 30%;
  display: inline-block;
}
.btn:hover {
  background-color: #f7a09d;
}
</style>