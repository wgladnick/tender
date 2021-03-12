<template>
  <main class="zoom">
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
    <div class=location-phone>
    <span
      v-for="ln in restaurant.location.display_address"
      v-bind:key="ln.display_address"
    >
      {{ ln }}
    </span>
    

  
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
  width: 350px;
  height: 350px;
  object-fit: cover;
}


.left-panel {
  padding: 20px;
  margin-right:35px;
  
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
    font-size:1em;
    width:15em;
}
.transactions > button.is-small {
  border: none;
  font-size:em;
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

.location-phone{
  padding-top:1em;
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

.zoom {
  padding: 50px;

  transition: transform .5s;


  margin: 0 auto;
}

.zoom:hover {
  -ms-transform: scale(1.1); /* IE 9 */
  -webkit-transform: scale(1.1); /* Safari 3-8 */
  transform: scale(1.1); 
}

.title:hover{
  color:#dc6b67;
}




</style>