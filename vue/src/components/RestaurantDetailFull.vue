<template>
<div>
    {{restaurant.name}}
    </div>
  
</template>

<script>
import RestaurantService from '../services/RestaurantService'
export default {
        data() {
    return {
      restaurant: {},
      isLoading: true,
    };
  },
  created() {
      RestaurantService.getTheRestaurant(this.$route.params.id).then((response) => {
      this.restaurant = response.data;
    });
    this.isLoading = false;
    console.log('Details: ' + this.restaurant);
  },

}
</script>

<style>

</style>