<template>
<div>
  <invitee />
  <br>
  <br>
  <br>
  <dinner-card /> 

  </div>
</template>

<script>
import Invitee from '../components/Invitee.vue'
import DinnerCard from '../components/DinnerCard.vue'
export default {
  name: "invitee-view",
  components: { Invitee, DinnerCard },

}
</script>

<style>

</style>