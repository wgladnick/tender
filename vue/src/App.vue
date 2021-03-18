<template>
  <div class="app">
    
    <top-nav 
    v-if="$route.name!=='login' && $route.name!=='register'"
    />
    
    <router-view />
  </div>
</template>

<script>

import TopNav from './components/TopNav.vue'

export default {

components: {
  TopNav
},
}
</script>


<style>

h1 {
  font-size:3vw;
}

.header {
  display: flex;
  justify-content: space-between;
  padding: 15px;
  background-color:white;
}
#nav {
  padding-right: 200px;
}
.logo {
  padding-left: 50px;
}
  .app {
    
    height:100vh;
  }
  html, body {
  background-color:#fdf2f2 !important;
  height: 100%;
  min-height: 100vh;
  }
</style>

