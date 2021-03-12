<template>
  <main>
    <div class="left-panel">
      <router-link :to="{ name: 'details', params: { id: restaurant.id }}" ><img :src="restaurant.image_url" class="yelp-image" /></router-link>
    </div>
    <div class="right-panel">
    <router-link :to="{ name: 'details', params: { id: restaurant.id }}" ><h1 class="title">{{ restaurant.name }}</h1> </router-link>
    <div>
    <span
      v-for="ln in restaurant.location.display_address"
      v-bind:key="ln.display_address"
    >
      {{ ln }}
    </span>
    <br>
    <span>{{ restaurant.display_phone }} </span>
    <span>
      <a :href="`tel:${restaurant.phone}`">
        <b-button type="is-primary" rounded size="is-small">
          <i class="fas fa-phone-alt"></i>
          Call to order</b-button>
      </a>
    </span>
    <p class="price"> Price: {{ restaurant.price }} </p>
    
    </div>
    <div class="star-rating">
      <star-rating
        :rating="restaurant.rating"
        :read-only="true"
        :increment="0.01"
        :show-rating="false"
        :inline="true"
        :star-size=25
      /> 
    </div>
    <div class="categories">
    <b-button 
        size="is-small"
        rounded
        v-for="ctg in restaurant.categories"
        v-bind:key="ctg.title"
      >
        {{ ctg.title }}
      </b-button>
      
    </div>
    
    <div class="transactions">
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
    </div>
    
    
    
    </div>
    
  </main>
</template>

<script>
import StarRating from "vue-star-rating";
export default {
  name: "restaurant-card",
  components: {
    StarRating,
  },
  props: ["restaurant"],
  data() {
    return {
      transactionTypes: ''
    };
  },
  created() {
            for (let i = 0; i < this.restaurant.transactions.length; i++) {
          this.transactionTypes += this.restaurant.transactions[i].transactions + " ";
            }
      }

};
</script>

<style scoped>
main {
  display: flex;
  flex-direction: row;
  width: 75%;
  
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
.button.is-primary {

  background-color: #dc6b67;
}
.button.is-rounded {
    border-radius: 290486px;
    padding-left: calc(1em + 0.25em);
    margin-right: 10px;
    padding-right: calc(1em + 0.25em);
}
.transactions > button.is-small {
  border: none;
}
.price {
  font-weight: 550;
}
.star-rating {
  padding: 5px 0px;
  
}
.categories {
  padding: 10px 0px 0px 0px;
}

.transactions {
  padding: 5px 0px 0px 0px;
}
</style>