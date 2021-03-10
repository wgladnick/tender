<template>
  <div>
    <h1>{{ restaurant.name }}</h1>
    <p>Price: {{restaurant.price}}</p>

    <p v-for="hours in restaurant.hours"
      v-bind:key="hours.hours_type">
    <b-button 
      v-bind:type="{
        'is-danger': !hours.is_open_now,
        'is-success': hours.is_open_now
      }"
    >
      <span v-if="!hours.is_open_now">Currently Closed</span>
      <span v-else>Currently Open</span>
    </b-button>
    </p>
    <br />
    <b-button
    size="is-small"
      rounded
      v-for="category in restaurant.categories"
      v-bind:key="category.title"
      >{{ category.title }}</b-button
    >
    <br>
    <b-button size="is-small"
      rounded
      v-for="transaction in restaurant.transactions"
      v-bind:key="transaction.title"
      >{{ transaction.label }}</b-button
    >

<div>
    <star-rating
      :rating="restaurant.rating"
      read-only="true"
      increment="0.01"
      :show-rating="false"
    />
    {{restaurant.review_count}} reviews
</div>

    <div>
      <span
        v-for="line in restaurant.location.display_address"
        v-bind:key="line"
      >
        {{ line }}
        <br />
      </span>
      <p>{{ restaurant.display_phone }}</p>
      <a :href="`tel:${restaurant.phone}`">
      <b-button rounded size="is-small">
          <i class="fas fa-phone-alt"></i>
Call to order</b-button>
      </a>
      <p>Hours:</p>
      <p v-for="hours in restaurant.hours"
      v-bind:key="hours.hours_type">
      <span v-for="open in hours.open"
      v-bind:key="open.day">
      <span v-if="open.day === 6">Sunday: {{open.start}} - {{open.end}}<br></span> 
      <span v-if="open.day === 0">Monday: {{open.start}} - {{open.end}}<br></span> 
      <span v-if="open.day === 1">Tuesday: {{open.start}} - {{open.end}}<br></span> 
      <span v-if="open.day === 2">Wednesday: {{open.start}} - {{open.end}}<br></span> 
      <span v-if="open.day === 3">Thursday: {{open.start}} - {{open.end}}<br></span> 
      <span v-if="open.day === 4">Friday: {{open.start}} - {{open.end}}<br></span> 
      <span v-if="open.day === 5">Saturday: {{open.start}} - {{open.end}}<br></span> 
      </span>
      </p>
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