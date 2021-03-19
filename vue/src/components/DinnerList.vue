<template>
  <article class="tile is-child notification has-background-white">
    <p class="title has-text-centered">Invite History</p>
    <div>
      <!-- Content -->

      <div>
        <div class="loading-gif" v-if="this.isLoading">
          <p class="is-size-3 has-text-weight-semibold">Loading...</p>
          <img src="../assets/loading.gif" />
        </div>

        <div v-if="!this.isLoading">
          <scheduled-dinner
            v-for="dinner in dinners"
            v-bind:key="dinner.id"
            v-bind:dinner="dinner"
            class="my-4"
          />
        </div>
      </div>
    </div>
  </article>
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
    InviteService.getInviteByUserId(this.$store.state.user.id).then(
      (response) => {
        this.dinners = response.data;
        this.isLoading = false;
      }
    );
  },
};
</script>

<style scoped>

.loading-gif {
  display: flex;
  flex-direction: column;
  height: 80vh;
  width: fit-content;
}

.loading-gif img {
  width: 400px;
  height: 400px;
  object-fit: contain;
  text-align: center;
}
</style>