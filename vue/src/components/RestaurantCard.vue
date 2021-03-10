<template>
  <div>

     <h1>{{restaurant.name}}</h1>
     <star-rating></star-rating>
     <p>{{restaurant.category}}</p>
     <p>{{restaurant.location.display_address[0]}}</p>
     <img :src="restaurant.image_url" class="yelp-image" />
     
  </div>
</template>

<script>

export default {
<<<<<<< HEAD
props: ["restaurant"]
=======
  name: 'restaurant-card',
  props: ["restaurant"]

>>>>>>> 2db012ccd682e33a2b727c68809001e958d4d796
}
</script>

<style>

.yelp-image {
  width:300px;
  height:300px;
  object-fit:cover;
}

</style>