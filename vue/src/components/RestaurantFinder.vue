<template>
  <div>
    <restaurant-card
      v-for="restaurant in restaurants"
      v-bind:key="restaurant.id"
      v-bind:restaurant="restaurant"
    />
  </div>
</template>
<script>
import RestaurantService from "../services/RestaurantService";
import RestaurantCard from "../components/RestaurantCard";
export default {
  name: "restaurant-finder",
  components: { RestaurantCard },
  data() {
    return {
      restaurants: [],
      isLoading: true,
    };
  },
  methods: {
    viewRestaurant(id) {
      this.$router.push(`/${id}`);
    },
  },
  created() {
    RestaurantService.list().then((response) => {
      this.restaurants = response.data;
    });
    console.log(this.restaurants);
  },
};
</script>
<style scoped>
</style>