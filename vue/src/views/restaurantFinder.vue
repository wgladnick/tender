<template>
  <main class="main">
    <restaurant-search />
  </main>
</template>

<script>
import RestaurantSearch from "../components/RestaurantSearch";

export default {
  name: "restaurant-finder",
  components: {
    RestaurantSearch,
  },
};
</script>

<style scoped>
</style>