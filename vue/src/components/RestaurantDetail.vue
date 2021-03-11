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
      >
      <i class="fas fa-check has-text-success"
      v-if="transactionTypes.includes('pickup')"></i>
      <i class="fas fa-times has-text-danger"
      v-if="!transactionTypes.includes('pickup')"></i>
      Takeout</b-button
    >
     <b-button size="is-small"
      >
       <i class="fas fa-check has-text-success"
      v-if="transactionTypes.includes('delivery')"></i>
      <i class="fas fa-times has-text-danger"
      v-if="!transactionTypes.includes('delivery')"></i>
      Delivery</b-button
    >
     <b-button size="is-small"
      >
       <i class="fas fa-check has-text-success"
      v-if="transactionTypes.includes('restaurant_reservation')"></i>
      <i class="fas fa-times has-text-danger"
      v-if="!transactionTypes.includes('restaurant_reservation')"></i>
      Reservations</b-button
    >
    <br>
<div>
    <star-rating
      :rating="restaurant.rating"
      :read-only="true"
      :increment="0.5"
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
      <a :href="`https://www.google.com/maps/dir/19801/${ restaurant.location.display_address.toString().split(' ').join('+')}/`" target="_blank">
      <b-button rounded size="is-small">
          <i class="fas fa-route"></i>
Get Directions</b-button>
      </a>
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
  name: "restaurant-detail",
  components: {
    StarRating,
  },
  data() {
    return {
      restaurant: {},
      isLoading: true,
      directionsAddress: [],
      transactionTypes: ''
    };
  },
  created() {
    RestaurantService.getTheRestaurant(this.$route.params.id).then(
      (response) => {
        this.restaurant = response.data;
        this.isLoading = false;

        for (let i = 0; i < this.restaurant.transactions.length; i++) {
          this.transactionTypes += this.restaurant.transactions[i].transactions + " ";
        }
       
      }
    );
  },

};
</script>

<style>

</style>