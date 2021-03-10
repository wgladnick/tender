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
    background-color:#fdf2f2;
    height:100%;
  }
</style>

