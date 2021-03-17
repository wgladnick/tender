<template>
  <div class="header">
    <div class="logo">
      <router-link class="nav" v-bind:to="{ name: 'home' }"
        >
       <!-- <img
          class="flame"
          style="padding-right: 10px"
          src="../assets/TenderFlame2.png" /> -->

        <img class="tender-text" src="../assets/TenderTextNew.png"
      /></router-link>
    
</div>
    <div id="nav-container">
      <router-link
        v-if="$route.name !== 'home'"
        class="nav"
        v-bind:to="{ name: 'home' }"
        >Home</router-link
      >&nbsp;&nbsp;
      <router-link
        class="nav"
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
    </div>
  </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>

.header{
  z-index: 2;
  position:fixed;
  top:0;
  width:100vw;

}
.logo {
  display: flex;
  flex-direction: row;

}
.flame {
width:40px;
margin-top:-3em;
}
.tender-text {
  width:200px;
 
  
  
}

#nav-container {
  margin-top: 1.5em;
  padding-right: 2em;
}
.nav {
  font-size: 1.2em;
  text-transform: uppercase;
  font-weight: bold;
  color: #dc6b67;
}

.nav:hover {
  color: #3c0f49;
}
</style>