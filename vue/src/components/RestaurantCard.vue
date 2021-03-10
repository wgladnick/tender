<template>
  <div>

     <h1>{{restaurant.name}}</h1>
     <p>{{restaurant.location.display_address[0]}}</p>
     <img :src="restaurant.image_url" class="yelp-image" >
     
  </div>
</template>

<script>
export default {
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