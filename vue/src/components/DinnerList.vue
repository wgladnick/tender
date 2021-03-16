<template>
  <div>
  <section class="loading-gif" v-if="isLoading">
    <img src="../assets/loading.gif" />
  </section>
  <div v-if="!isLoading">
    <scheduled-dinner 
      v-for="dinner in dinners"
      v-bind:key="dinner.id"
      v-bind:dinner="dinner"
      class="card"
      />
  </div>
  </div>

</template>

<script>

import ScheduledDinner from './ScheduledDinner.vue';
export default {
  name: "dinner-list",
  components: {  
    ScheduledDinner 
  },
  props: ["isLoading"],

  data() {
    return {};
  },
};
</script>

<style scoped>
.loading-gif {
  display: flex;
  justify-content: center;
  height: 80vh;
}

.loading-gif img {
  width: 400px;
  height: 400px;
  object-fit: contain;
}

.result-list {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.card {
  width: 60%;
  margin-top: 25px;
  margin-bottom: 25px;
  padding: 50px;
}
</style>