<template>
  <main>
    <div class="loading-gif" v-if="isLoading">
      <img src="../assets/loading.gif" />
    </div>

    <div v-if="!isLoading" class="result-list">
      <ul>
      <li 
        v-for="dinner in dinners"
        v-bind:key="dinner.id"
        v-bind:dinner="dinner"
        class="card">

    <div>
      <p>{{dinner.inviteName}}</p>
    </div>
    <div>
      <p>{{dinner.reservationDate}}</p>
    </div>
    <div>
      <p>{{dinner.invitees.name}}</p>
    </div>
    <div>
      <p>{{dinner.restaurantChoices.yelpId}}</p>
    </div>
    </li>
    </ul>
  </main>
</template>

<script>

import DinnerCard from "./DinnerCard.vue";
export default {
  name: "dinner-list",
  components: { DinnerCard },

  props: ["dinners", "isLoading"],

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