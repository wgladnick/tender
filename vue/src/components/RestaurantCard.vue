<template>
  <main>
    <div class="left-panel">
      <img :src="restaurant.image_url" class="yelp-image" />
    </div>
    <div class="right-panel">
    <h1 class="title">{{ restaurant.name }}</h1>
    <div>
    <span
      v-for="ln in restaurant.location.display_address"
      v-bind:key="ln.display_address"
    >
      {{ ln }}
    </span>
    <p>{{ restaurant.display_phone }}</p>
    <span>{{ restaurant.price }} </span>
    <span>
      <a :href="`tel:${restaurant.phone}`">
        <b-button rounded size="is-small">
          <i class="fas fa-phone-alt"></i>
          Call to order</b-button
        >
      </a>
    </span>
    
    </div>
    <div>
      <star-rating
        :rating="restaurant.rating"
        read-only="true"
        increment="0.01"
        :show-rating="false"
        :inline="true"
      /> 
    </div>
    <div>
    <b-button class="categories"
        size="is-small"
        rounded
        v-for="ctg in restaurant.categories"
        v-bind:key="ctg.title"
      >
        {{ ctg.title }}
      </b-button>
      
    </div>
    
    <div>
      <b-button
        size="is-small"
        rounded
        v-for="transaction in restaurant.transactions"
        v-bind:key="transaction.label"
        >{{ transaction.label }}</b-button
      >
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
.button.is-rounded {
    border-radius: 290486px;
    padding-left: calc(1em + 0.25em);
    margin-right: 10px;
    padding-right: calc(1em + 0.25em);
}
</style>