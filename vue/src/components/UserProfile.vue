<template>
  <main>  
   <div class="card">
    <div class="card-image">
      <figure class="image is-4by3">
        <img src="https://bulma.io/images/placeholders/1280x960.png" alt="Placeholder image">
      </figure>
    </div>
    <div class="card-content">
      <div class="media">
        <div class="media-content pb-6">
          <p class="title is-size-3">{{ this.user.firstName }} {{ this.user.lastName }}</p>
          <p class="subtitle is-6"> @{{ this.user.username }}</p>
        </div>
      </div>

      <div>
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
          <b-button class="btn" type="is-info" size="is-small">Edit My Profile</b-button>
        </router-link>
            
        <br>
      </div>
    </div>
   </div>


        <!-- <h1 class="is-size-3 has-text-weight-semibold">
          Welcome,
          <span class="username">{{ this.user.username }}</span>
        </h1>
        <br />

        <div class="details">
          <span class="details-header is-size-4"> Name:</span>
          <br />
          <span class="pb-1">
            {{ this.user.firstName }} {{ this.user.lastName }}</span
          >
        </div>

        <div class="details">
          <span class="details-header is-size-4">Email Address:<br /></span
          ><span class="pb-1"> {{ this.user.email }}</span>
        </div>

        <div class="details">
          <span class="details-header is-size-4"> Address: <br /></span
          ><span class="pb-1">
            {{ this.user.userDetails.address }}
            {{ this.user.userDetails.city }},{{ this.user.userDetails.state }}
            {{ this.user.userDetails.zip }}
          </span>
        </div>

        <div class="details">
          <span class="details-header">Default Search Categories:<br /></span
          ><span class="pb-1">
            {{ this.user.userDetails.searchCategories }}
          </span>
        </div>

        <router-link :to="{ name: 'editProfile' }">
          <button class="btn" type="submit">Edit My Profile</button>
        </router-link>
      </div> -->
    
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
  border-radius: 5px;
  width: 30%;
  display: inline-block;
}

.btn:hover {
  background-color: #f7a09d;
}
</style>