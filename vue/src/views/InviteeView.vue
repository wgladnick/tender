<template>
<div>
  
  <invitee />
  <br>
  <br>
  <p> --------------------------------- </p>
  <br>
  <copy-dinner-card />
  <br>
  <br>
  <br>
  <p> ---------------------------------------- </p>
  <br>
  <dinner-card/>

  </div>
</template>

<script>
import Invitee from '../components/Invitee.vue'
import CopyDinnerCard from '../components/CopyDinnerCard.vue'
import DinnerCard from '../components/DinnerCard.vue'
export default {
  name: "invitee-view",
  components: { Invitee, CopyDinnerCard, DinnerCard },

}
</script>

<style>

</style>