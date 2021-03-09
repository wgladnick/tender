<template>

<div class ="app">
  <div class ="header">

      <div class = "logo">
        <img src="../src/assets/tender.png"/>
        </div>

          <div id="nav">
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
    </div>
      </div>
    <router-view :key="$route.fullPath"/>
       
        </div>

</template>

<style scoped>

.header{
  display:flex;
  justify-content: space-between;
  padding:15px; 
 
}
#nav{
  padding-right:200px;
}
.logo{
  padding-left:50px;
}
</style>

