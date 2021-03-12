<template>
  <main>
    <div class="main-div" v-if="!isLoading">
      <div class="left-panel">
        <img
          class="yelp-image"
          v-bind:src="restaurant.image_url"
        />
      </div>
      <div class="right-panel">
        <h1 class="title">{{ restaurant.name }}</h1>
        <span
          v-for="category in restaurant.categories"
          v-bind:key="category.title"
          >{{ category.title }} </span
        >
        <div>
          <star-rating class="stars"
            :rating="restaurant.rating"
            :read-only="true"
            :increment="0.01"
            :show-rating="false"
            :star-size=25
          />
          <a :href="restaurant.url" target="_blank"> See Yelp Reviews Here </a>
        </div>
        
        <div>
          <a
            :href="`https://www.google.com/maps/dir/${this.$store.state.searchDirections}/${restaurant.location.display_address
              .toString()
              .split(' ')
              .join('+')}/`"
            target="_blank"
          >
            <b-button rounded size="is-small">
              <i class="fas fa-route"></i>
              Get Directions</b-button
            >
          </a>
          </div>
          <div>
        <span
            v-for="ln in restaurant.location.display_address"
            v-bind:key="ln.display_address"
          >
            {{ ln }}
          </span>
        </div>
          <br />
          <div>
          <span>
            <a :href="`tel:${restaurant.phone}`">
              <b-button type="is-primary" rounded size="is-small">
                <i class="fas fa-phone-alt"></i>
                Call to order</b-button
              >
            </a>
          </span>
          </div>
          <div>
          <span>{{ restaurant.display_phone }} </span>
          </div>
        <p class="price">Price: {{ restaurant.price }}</p>

        <p v-for="hours in restaurant.hours" v-bind:key="hours.hours_type">
          <b-button
            v-bind:type="{
              'is-danger': !hours.is_open_now,
              'is-success': hours.is_open_now,
            }"
          >
            <span v-if="!hours.is_open_now">Currently Closed</span>
            <span v-else>Currently Open</span>
          </b-button>
        </p>

        
        <br />
        <div class="transactions">
          <b-button size="is-small">
            <i
              class="fas fa-check has-text-success"
              v-if="transactionTypes.includes('pickup')"
            ></i>
            <i
              class="fas fa-times has-text-danger"
              v-if="!transactionTypes.includes('pickup')"
            ></i>
            Takeout</b-button
          >
          <b-button size="is-small">
            <i
              class="fas fa-check has-text-success"
              v-if="transactionTypes.includes('delivery')"
            ></i>
            <i
              class="fas fa-times has-text-danger"
              v-if="!transactionTypes.includes('delivery')"
            ></i>
            Delivery</b-button
          >
          <b-button size="is-small">
            <i
              class="fas fa-check has-text-success"
              v-if="transactionTypes.includes('restaurant_reservation')"
            ></i>
            <i
              class="fas fa-times has-text-danger"
              v-if="!transactionTypes.includes('restaurant_reservation')"
            ></i>
            Reservations</b-button
          >
        </div>
        <br />
      </div>
      <div class="far-right-panel">
          <p>Hours:</p>
          <p v-for="hours in restaurant.hours" v-bind:key="hours.hours_type">
            <span v-for="open in hours.open" v-bind:key="open.day">
              <span v-if="open.day === 6"
                >Sunday: {{ open.start }} - {{ open.end }}<br
              /></span>
              <span v-if="open.day === 0"
                >Monday: {{ open.start }} - {{ open.end }}<br
              /></span>
              <span v-if="open.day === 1"
                >Tuesday: {{ open.start }} - {{ open.end }}<br
              /></span>
              <span v-if="open.day === 2"
                >Wednesday: {{ open.start }} - {{ open.end }}<br
              /></span>
              <span v-if="open.day === 3"
                >Thursday: {{ open.start }} - {{ open.end }}<br
              /></span>
              <span v-if="open.day === 4"
                >Friday: {{ open.start }} - {{ open.end }}<br
              /></span>
              <span v-if="open.day === 5"
                >Saturday: {{ open.start }} - {{ open.end }}<br
              /></span>
            </span>
          </p>
        </div>
    </div>
  </main>
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
      transactionTypes: "",
    };
  },
  created() {
    RestaurantService.getTheRestaurant(this.$route.params.id).then(
      (response) => {
        this.restaurant = response.data;
        this.isLoading = false;

        for (let i = 0; i < this.restaurant.transactions.length; i++) {
          this.transactionTypes +=
            this.restaurant.transactions[i].transactions + " ";
        }
      }
    );
  },
};
</script>

<style scoped>
.main-div {
  display: flex;
  flex-direction: row;
  width: 100%;
  justify-content: center;
  align-items: center;
}
.yelp-image {
  width: 300px;
  height: 300px;
  object-fit: cover;
  
}
.left-panel {
  padding: 20px;
  margin-right: 25px;
  
}
.right-panel {
  padding: 20px;
 
}
.button.is-rounded {
  border-radius: 290486px;
  padding-left: calc(1em + 0.25em);
  margin-right: 10px;
  padding-right: calc(1em + 0.25em);
  padding: 10px;
}
.price {
  font-weight: 550;
}

.transactions > button.is-small {
  border: none;
  margin-right: 10px;
  margin-left: 10px;
  padding: 10px;
}

</style>