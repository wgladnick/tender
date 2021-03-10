<template>
  <div>

     <h1>{{restaurant.name}}</h1>
     <star-rating  />
     <p v-for="ctg in restaurant.categories" 
        v-bind:key="ctg.title">{{ctg.title}}</p>
     <p v-for="ln in restaurant.location.display_address" v-bind:key="ln.display_address">
       {{ln}}
       </p>
     <img :src="restaurant.image_url" class="yelp-image" />
     
  </div>
</template>

<script>
import StarRating from "vue-star-rating";
export default {

 name: 'restaurant-card',
  components: {
    StarRating
  },
  props: ["restaurant"]


}
</script>

<style>

.yelp-image {
  width:300px;
  height:300px;
  object-fit:cover;
}

</style>