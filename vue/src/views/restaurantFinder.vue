<template>
<main class="main">
  <restaurant-search/>
  <restaurant-list/>
  </main>

</template>

<script>
import RestaurantSearch from '../components/RestaurantSearch';
import RestaurantList from '../components/RestaurantList'

export default {
    name:"restaurant-finder",
    components: {
        RestaurantSearch,
        RestaurantList
        
    }
};
</script>

<style scoped>
.searchbox{
    display: flex
}
</style>