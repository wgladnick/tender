<template>
<div class="header" >
      <div class="logo">
        <img src="../assets/tender.png" />
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
  
</template>

<script>
export default {
  

}
</script>

<style>

</style>