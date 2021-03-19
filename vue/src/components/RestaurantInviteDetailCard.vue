<template>
  <div>
    <!-- Restaurant Card Image -->
    <router-link :to="{ name: 'details', params: { id: restaurant.id } }">
      <div class="box">
        
          <div class="columns">
            <div class="column is-one-quarter">
              
              <figure class="image is-1by1">
                <img :src="restaurant.image_url" />
              </figure>
              <br/>
              
            
          
        </div>
        <!-- Restaurant Card Image Ends -->

        <!-- Restaurant Card Information -->
        
            <div class="column">
              <!-- Categories -->
              <span
                class="categories-tag"
                v-for="category in this.categories"
                v-bind:key="category"
              >
                {{ category }}
              </span>

              <!-- Restaurant Name -->
              <router-link
                :to="{ name: 'details', params: { id: restaurant.id } }"
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
              <p class="price">
                Price:
                <span class="has-text-success has-text-weight-bold">{{
                  restaurant.price
                }}</span>
              </p>
              <h3 class="title is-size-6">Voting Progress:</h3>
              <progress
                class="progress is-success"
                :value="restaurant.totalThumbsUp"
                :max="restaurant.totalThumbsUp + restaurant.totalThumbsDown"
              ></progress>
              <progress
                class="progress is-danger"
                :value="restaurant.totalThumbsDown"
                :max="restaurant.totalThumbsUp + restaurant.totalThumbsDown"
              ></progress>
              
              </div>
            </div>
          </div>
        
      
    </router-link>
    <br />
  </div>
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
};
</script>
<style scoped>

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

.title:hover {
  color: #dc6b67;
}
</style>