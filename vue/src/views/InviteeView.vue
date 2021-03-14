<template>
<div>
  <invitee />
  <br>
  <br>
  <br>
  <copy-dinner-card />

  </div>
</template>

<script>
import Invitee from '../components/Invitee.vue'
import CopyDinnerCard from '../components/CopyDinnerCard.vue'
export default {
  name: "invitee-view",
  components: { Invitee, CopyDinnerCard },

}
</script>

<style>

</style>