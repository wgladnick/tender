<template>
  <div class="main"> 
    <div class="left">
      <user-profile />
    </div>
    <div class="right">
      <dinner-list />
    </div>
  </div>
</template>

<script>
import DinnerList from "../components/DinnerList.vue";
import UserProfile from "../components/UserProfile.vue";
export default {
  name: "profile",
  components: {
    UserProfile,
    DinnerList,
  },

  created() {},
};
</script>


<style scoped>

.main {
  display: flex;
  
  width: 100vw;
}

.right {
  width: 40vw

}

.left {
   width: 60vw 
}
</style>