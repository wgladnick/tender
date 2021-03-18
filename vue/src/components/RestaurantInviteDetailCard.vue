<template>
  <span>
    <!-- Restaurant Card Image -->
    <div class="tile is-child is-vertical box">
      <div class="content">
        <div class="media-content pb-6">
      <router-link :to="{ name: 'details', params: { id: restaurant.id } }"
        ><figure class="image is-128x128"><img class="is-rounded" :src="restaurant.image_url"
      /></figure></router-link>
        </div>
    </div>
    <!-- Restaurant Card Image Ends -->

    <!-- Restaurant Card Information -->
    <div class="tile is-child">
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
      <p class="price">
        Price:
        <span class="has-text-success has-text-weight-bold">{{
          restaurant.price
        }}</span>
      </p>
      <p>Voting Progress</p>
      <progress
        class="progress is-success"
        :value="restaurant.totalThumbsUp"
        :max="restaurant.totalThumbsUp + restaurant.totalThumbsDown"
      >
      </progress>
      <progress
        class="progress is-danger"
        :value="restaurant.totalThumbsDown"
        :max="restaurant.totalThumbsUp + restaurant.totalThumbsDown"
      >
      </progress>
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
/*.zoom {
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
*/
.left-panel {
  margin-right: 1vw;
  width: 50%;
}

.action-buttons {
  display: flex;
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