<template>
 <span class="zoom">
<!-- Restaurant Card Image -->
    <div class="left-panel">
      <router-link :to="{ name: 'details', params: { id: restaurant.id } }"
        ><img :src="restaurant.image_url" class="yelp-image"
      /></router-link>
    </div>
<!-- Restaurant Card Image Ends -->

<!-- Restaurant Card Information -->
    <div class="right-panel">
      
      <!-- Categories -->
        <span
          class="categories-tag"
          v-for="category in this.categories"
          v-bind:key="category"
        >
          {{ category }}
        </span>


<!-- Restaurant Name -->
      <router-link :to="{ name: 'details', params: { id: restaurant.id } }"
        ><h1 class="title">{{ restaurant.name }}</h1>
      </router-link>


<!-- Stars -->
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

      <!-- Location/Phone Number -->
      <div class="location-phone">
        <p v-for="ln in restaurant.location.display_address"
          v-bind:key="ln.display_address">
          {{ ln }}
        </p>
        
        <p>{{ restaurant.display_phone }} </p>

        <p class="price">
          Price:
          <span class="has-text-success has-text-weight-bold">{{
            restaurant.price}}</span>
        </p>
      </div>
     
    </div>

  
  </span>
</template>

<script>

import StarRating from "vue-star-rating";

export default {
  name: "restaurant-invite-detail-card",

  components: {
    StarRating,
  },

  props: ["restaurant"],

  data() {
    return {
      transactionTypes: "",
      categories: [],
      
     
    }
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
}
</script>
<style scoped>


.zoom {
  display: flex;
  flex-direction: row;
  padding: 1em;
  transition: transform 0.5s;
  

  
}
.yelp-image {
  width: 200px;
  height: 300px;
  object-fit: cover;
  
}

.left-panel {
  margin-right:1vw;
  width: 50%;
}



.action-buttons {
 display:flex;
 


}



.nah-button .button.is-rounded {
  background-color: #dc6b67;
}
.nah-button .button.is-rounded:hover {
  background-color: #ad5451;
}

.yes-button .button.button.is-rounded {
  background-color: #a5c064;
}
.yes-button .button.button.is-rounded:hover {
  background-color: #9cbd4f;
}

.add-to-list .button.button.is-rounded:hover {
  background-color: #9dbd52;
}
.add-to-list .button.button.is-rounded {
  background-color: #81974e;
}
.invite-buttons .button.is-rounded {
  width: 7em;
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