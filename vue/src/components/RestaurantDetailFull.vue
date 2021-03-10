<template>
  <div>
    <h1>{{ restaurant.name }}</h1>
    <b-button
      v-bind:type="{
        'is-danger': !restaurant.hours.is_open_now,
        'is-success': !restaurant.hours.is_open_now,
      }"
    >
      <span v-if="!restaurant.hours.is_open_now">Currently Closed</span>
      <span v-else>Currently Open</span>
    </b-button>
    <br />
    <b-button
      rounded
      v-for="category in restaurant.categories"
      v-bind:key="category.title"
      >{{ category.title }}</b-button
    >
    <br>
    <b-button
      rounded
      v-for="transaction in restaurant.transactions"
      v-bind:key="transaction.title"
      >{{ transaction.label }}</b-button
    >


    <star-rating
      :rating="restaurant.rating"
      read-only="true"
      increment="0.01"
      show-rating="false"
    ></star-rating>
    <div>
      <span
        v-for="line in restaurant.location.display_address"
        v-bind:key="line"
      >
        {{ line }}
        <br />
      </span>
      
      <br />
      <a :href="`tel:${restaurant.phone}`">
      <b-button rounded>Call to order</b-button>
      </a>
    </div>
    <img v-bind:src="restaurant.image_url" width="150px" />
  </div>
</template>

<script>
import RestaurantService from "../services/RestaurantService";
import StarRating from "vue-star-rating";

export default {
  components: {
    StarRating,
  },
  data() {
    return {
      restaurant: {},
      isLoading: true,
    };
  },
  created() {
    RestaurantService.getTheRestaurant(this.$route.params.id).then(
      (response) => {
        this.restaurant = response.data;
        this.isLoading = false;
      }
    );
  },
};
</script>

<style>
</style>