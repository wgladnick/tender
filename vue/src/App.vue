<template>
  <div class="app">
    <div class="header">
      <div class="logo">
        <img src="../src/assets/tender.png" />
      </div>

      <div id="nav">
        <router-link v-bind:to="{ name: 'restaurantFinder' }">Home</router-link
        >&nbsp;|&nbsp;
        <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link
        >
      </div>
    </div>
    <router-view />
  </div>
</template>

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

