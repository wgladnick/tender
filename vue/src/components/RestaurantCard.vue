<template>
  <div class="zoom">
    <div class="left-panel">
      <router-link :to="{ name: 'details', params: { id: restaurant.id } }"
        ><img :src="restaurant.image_url" class="yelp-image"
      /></router-link>
    </div>
    <div class="right-panel">
      <div class="categories">
        <span
          class="categories-tag"
          v-for="category in this.categories"
          v-bind:key="category"
        >
          {{ category }}
        </span>
      </div>
      <router-link :to="{ name: 'details', params: { id: restaurant.id } }"
        ><h1 class="title">{{ restaurant.name }}</h1>
      </router-link>
      <div class="star-rating">
        <star-rating
          :rating="restaurant.rating"
          :read-only="true"
          :increment="0.01"
          :show-rating="false"
          :inline="true"
          :star-size="25"
        />
      </div>
      <div class="location-phone">
        <span
          v-for="ln in restaurant.location.display_address"
          v-bind:key="ln.display_address"
        >
          {{ ln }}
        </span>

        <br />
        <span>{{ restaurant.display_phone }} </span>

        <p class="price">
          Price:
          <span class="has-text-success has-text-weight-bold">{{
            restaurant.price
          }}</span>
        </p>
      </div>

      <!-- Invite Buttons --> 

         <span class="invite-buttons" v-if="$route.name === 'inviteeView'">

          <span class="yes-button"> <b-button type="is-primary" rounded size="is-small" class="m-2">
          <i class="far fa-thumbs-up"></i>
          LIKE</b-button>
          </span>

          <span class="nah-button">
          <b-button type="is-primary" rounded size="is-small" class="m-2">
          <i class="far fa-sad-tear"></i>
          NAH</b-button>
          </span>
  </span>





      <span v-if="$route.name !== 'inviteeView'" class="call-to-order">
        <a :href="`tel:${restaurant.phone}`">
          <b-button type="is-primary" rounded size="is-small">
            <i class="fas fa-phone-alt"></i>
            Call to order</b-button
          >
        </a>
      </span>

      <div v-if="$route.name !== 'inviteeView'" class="transactions">
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
        <br />
      </div>
    </div>

    <button v-if="isAddingRestaurants">Fun</button>
  </div>
</template>

<script>
import StarRating from "vue-star-rating";

export default {
  name: "restaurant-card",
  components: {
    StarRating,
  },
  props: ["restaurant", "isAddingRestaurants"],
  data() {
    return {
      transactionTypes: "",
      categories: [],
      isSelected: false,
    };
  },

  created() {
    for (let i = 0; i < this.restaurant.transactions.length; i++) {
      this.transactionTypes +=
        this.restaurant.transactions[i].transactions + " ";
    }

    for (let j = 0; j < this.restaurant.categories.length; j++) {
      this.categories.push(this.restaurant.categories[j].title);
      if (j < this.restaurant.categories.length - 1) {
        this.categories[j] += " | ";
      }
    }
  },

  methods: {
    updateSelected() {
      this.$emit("addSelectedRestaurant", this.isSelected);
    },
  },
};
</script>

<style scoped>
.zoom {
  display: flex;
  flex-direction: row;
 
  padding: 1em;
  transition: transform 0.5s;

  margin: 0 auto;
}
.yelp-image {
  max-width: 350px;
  height: 350px;
  object-fit: cover;
}

.left-panel {
  padding: 20px;
  margin-right: 35px;
}
.right-panel {
  padding: 20px 20px 20px 0px;
  max-width: 100%;
}

.call-to-order {
  margin-top: 2em;
  display: block;
}
.button.is-primary {
  background-color: #dc6b67;
}

.nah-button .button.is-rounded {
  background-color:#dc6b67;
}
.nah-button .button.is-rounded:hover {
  background-color:#ad5451;
}

.yes-button .button.button.is-rounded{
  background-color:#a5c064;
}
.yes-button .button.button.is-rounded:hover{
  background-color:#7d973f;
}


.invite-buttons .button.is-rounded{
 
  width:7em;
}
.button.is-rounded {
  border-radius: 290486px;
  padding-left: calc(1em + 0.25em);
  margin-right: 10px;
  padding-right: calc(1em + 0.25em);
  font-size: 1em;
  width: 15em;
}
.transactions > button.is-small {
  border: none;
  font-size: em;
  font-weight: bold;
}
.price {
  font-weight: 550;
}
.star-rating {
  padding: 5px 0px;
  display: inline-block;
  width: 0.2em;
}

.location-phone {
  padding-top: 1em;
}

.categories {
  padding: 10px 0px 0px 0px;
}
.categories-tag {
  color: rgb(179, 179, 179);
}

.transactions {
  margin-top: 25px;
  margin-left: -0.5em;
}

.zoom:hover {
  -ms-transform: scale(1.1); /* IE 9 */
  -webkit-transform: scale(1.1); /* Safari 3-8 */
  transform: scale(1.1);
}

.title:hover {
  color: #dc6b67;
}
</style>