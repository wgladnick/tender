<template>
  <div> 
    <user-profile />
    <dinner-list />
  </div>
</template>

<script>
import DinnerList from '../components/DinnerList.vue';
import UserProfile from '../components/UserProfile.vue';
export default {
    name: "profile",
  components: { 
    UserProfile,
    DinnerList
   },

created() {
  
}
}; 
</script>


<style scoped>

</style>