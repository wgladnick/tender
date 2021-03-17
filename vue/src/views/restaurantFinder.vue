<template>
<main class="body">
    <restaurant-search />
    </main>

</template>

<script>
import RestaurantSearch from "../components/RestaurantSearch";

export default {
  name: "restaurantFinder",
  components: {
    RestaurantSearch,
  }

};
</script>

<style scoped>
.body{
height:100vh;


}
</style>