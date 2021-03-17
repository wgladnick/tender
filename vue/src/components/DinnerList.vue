<template>
  <div>
    <section class="card">
      <h1 class="is-size-3 has-text-weight-semibold">Invitation History</h1>
    </section>
    <section class="has-text-centered" v-if="this.isLoading">
      <div>
      <p class="is-size-3 has-text-weight-semibold">Loading...</p>
      </div>
      <div class="loading-gif">
      <img src="../assets/loading.gif" />
      </div>      
    </section>
    <div v-if="!this.isLoading">
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
import InviteService from "../services/InviteService.js";
import ScheduledDinner from "./ScheduledDinner.vue";
export default {
  name: "dinner-list",
  components: {
    ScheduledDinner,
  },

  data() {
    return {
      dinners: [],
      isLoading: true,
    };
  },
  created() {
    InviteService.getInviteById(this.$store.state.user.id).then((response) => {
      this.dinners = response.data;
      this.isLoading = false;
    });
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
  width: 80%;
  margin-top: 25px;
  margin-bottom: 25px;
  padding: 30px;
}
</style>