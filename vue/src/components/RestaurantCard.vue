<template>
  <main>
    <div class="left-panel">
      <router-link :to="{ name: 'details', params: { id: restaurant.id }}" ><img :src="restaurant.image_url" class="yelp-image" /></router-link>
    </div>
    <div class="right-panel">
         <div class="categories">
 
        <span class="categories-tag"
        v-for="ctg in restaurant.categories"
        v-bind:key="ctg.title"
      >
        {{ ctg.title }}
      </span>
      
    </div>
    <router-link :to="{ name: 'details', params: { id: restaurant.id }}" ><h1 class="title">{{ restaurant.name }}</h1> </router-link>
    <div>
    <span
      v-for="ln in restaurant.location.display_address"
      v-bind:key="ln.display_address"
    >
      {{ ln }}
    </span>
    <br>

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
    <br>
    <span>{{ restaurant.display_phone }} </span>
    
   
    <p class="price"> Price: {{ restaurant.price }} </p>
    
    </div>
    
 
     <span class="call-to-order">
      <a :href="`tel:${restaurant.phone}`">
        <b-button type="is-primary" rounded size="is-small">
          <i class="fas fa-phone-alt"></i>
          Call to order</b-button>
      </a>
    </span>
    
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

                RestaurantService.getTheRestaurant(this.$route.params.id).then(
      (response) => {
        this.restaurant = response.data;
        this.isLoading = false;

        for (let i = 0; i < this.restaurant.transactions.length; i++) {
          this.transactionTypes +=
            this.restaurant.transactions[i].transactions + " ";
        }
        for (let j = 0; j < this.restaurant.categories.length; j++) {
          this.category = this.restaurant.categories[j].title;
          if (j < this.restaurant.categories.length - 1) {
            this.categories.push(this.category + " | ");
          } else {
            this.categories.push(this.category);
          }
        }
      }
    );
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
  margin-right:10px;
  
}
.right-panel {
  padding: 20px 20px 20px 0px;
 
}

.call-to-order{
  margin-top:2em;
  display:block;
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
  font-size:.8em;
  font-weight:bold;
}
.price {
  font-weight: 550;
}
.star-rating {
  padding: 5px 0px;
  display:inline-block;
  width:.2em;
  
  
}

.categories {
  padding: 10px 0px 0px 0px;
}
.categories-tag{
  color:rgb(179, 179, 179)

}

.transactions {  
margin-top:25px;
margin-left:-.5em;

}
</style>