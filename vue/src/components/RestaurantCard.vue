<template>
  <div>
    <h1>{{ restaurant.name }}</h1>
    <div>
      <star-rating
        :rating="restaurant.rating"
        read-only="true"
        increment="0.01"
        :show-rating="false"
        :inline="true"
      />
      {{ restaurant.review_count }} reviews
    </div>
    <div>
      <span>{{ restaurant.price }} </span>
      <b-button
        size="is-small"
        rounded
        v-for="ctg in restaurant.categories"
        v-bind:key="ctg.title"
      >
        {{ ctg.title }}
      </b-button>
    </div>
    <span
      v-for="ln in restaurant.location.display_address"
      v-bind:key="ln.display_address"
    >
      {{ ln }}
    </span>
    <p>{{ restaurant.display_phone }}</p>
    <span>
      <a :href="`tel:${restaurant.phone}`">
        <b-button rounded size="is-small">
          <i class="fas fa-phone-alt"></i>
          Call to order</b-button
        >
      </a>
    </span>
    <div>
      <b-button
        size="is-small"
        rounded
        v-for="transaction in restaurant.transactions"
        v-bind:key="transaction.label"
        >{{ transaction.label }}</b-button
      >
    </div>
    <img :src="restaurant.image_url" class="yelp-image" />
  </div>
</template>

<script>
import StarRating from "vue-star-rating";
export default {
  name: "restaurant-card",
  components: {
    StarRating,
  },
  props: ["restaurant"],
};
</script>

<style>
.yelp-image {
  width: 300px;
  height: 300px;
  object-fit: cover;
}
</style>