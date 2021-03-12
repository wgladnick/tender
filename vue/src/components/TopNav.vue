<template>
<div class="header" >
      <div class="logo">
         <router-link  class="nav" v-bind:to="{ name: 'restaurantFinder' }"><img class="flame" style="padding-right: 10px;" src="../assets/TenderFlame.png"/>
         <img class="tender-text" src="../assets/TenderTextNoStroke.png" /></router-link>
      </div>

      <div id="nav-container">
        <router-link v-if="$route.name!=='restaurantFinder'" class="nav" v-bind:to="{ name: 'restaurantFinder' }">Home</router-link
        >&nbsp;&nbsp;
        <router-link
          class="nav"
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link
        >
      </div>
    </div>
  
</template>

<script>
export default {
  

}
</script>

<style scoped>
.logo{
  display: flex;
}
.flame{
  height: 70px;
}
.tender-text{
  height: 100px;
  align-items: flex-end;
}

#nav-container{
  margin-top:2em;
  padding-right:2em;
}
.nav{
  font-size:1.2em;
  text-transform: uppercase;
  font-weight:bold;
  color:#dc6b67;

}

.nav:hover{
  color:#3c0f49;
}


</style>