<template>

    <section class="login-search">
      <div class="initial-search" >
        <div class="loading-gif">
          <img src="../assets/loading.gif" />
        </div>
        <div >
          <h1 class="loading-text">What would you like to do today?</h1>
        </div>

        

        <form class="search-main" v-on:submit.prevent>
       
       <button class="find-food" v-on:click="searchForRestaurants()" focused>Search For Nearby Restaurants</button>
       
  
             <button class="find-food" v-on:click="invite()" focused>
          Invite Friends Out For Food
          </button>
             <button class="find-food" v-on:click="searchByLocation()" focused>
        View My Profile
          </button>
        </form>
      </div>
    </section>

<!-- <dinner-list /> -->

</template>

<script>


export default {
  name: "home",
  methods: {
    searchForRestaurants(){
      this.$router.push({ name: 'restaurant-finder'});
    },
      invite(){
      this.$router.push({ name: 'inviteeView'});
    }
  }
};
</script>
<style scoped>


.login-search{
  display:flex;
  justify-content: center;
  margin-top:5em;
  

}
.search-main {

  align-self: center;
  margin-top: 3em;
}

.loading-gif {
  display: flex;
  justify-content: center;
  height: 20vh;
}

.loading-gif img {
  width: 400px;
  max-height: 400px;
  object-fit: contain;
  align-self: center;
  margin-top: -em;
}
.loading-text {
  font-weight: bold;
  font-size: 1.5em;
  padding-top: 0;
  text-align: center;
  margin-bottom: -100em;
}

.initial-search {
  display: flex;
  flex-direction: column;
  width: 30vw;
  height: 80vh;
  margin-top: 0em;
}
.find-food {
  background-color: #dc6b67;
  border: none;
  color: white;
  padding: 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 5px;
  width: 100%;
  margin-top: 30px;
  margin-top: 2em;
  font-weight: bold;
}

.find-food:hover {
  background-color: #f7a09d;
}
</style>
