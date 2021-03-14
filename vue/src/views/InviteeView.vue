<template>
<div class="main">
  

<invitee/>

  </div>
</template>

<script>

import Invitee from '../components/Invitee.vue'
export default {
  name: "invitee-view",
  components: { Invitee },

}
</script>

<style>
.main {
  height:90vh;
}

</style>