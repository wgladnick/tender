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
  height:100%;
  width: 100vw;

  padding:5em;
  justify-content: center;
  margin-left:7em;
 
}

.right {
  width: 60vw

}

.left {
   width: 40vw 
}
</style>