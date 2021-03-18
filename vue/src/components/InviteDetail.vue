<template>
  <section>
    <div class="loading-gif" v-if="isLoading">
          <img src="../assets/loading.gif" />
        </div>
    <main class="tile is-ancestor" v-if="!isLoading">
      <div class="tile is-parent is-2 is-vertical">
        
        <div class="tile is-child">
          <div class="tile is-child">
            <br/>
            <h1 class="title box has-text-centered">{{ this.invite.inviteName }}</h1>
            <div class="tile is-child box">
              <h2 class="title is-size-5">Reservation Date and Time:</h2>
              <span> {{ this.invite.reservationDate }}</span>
            </div>
            <br/>
            <div class="tile is-child box">
              <h2 class="title is-size-5 has-text-centered">Participants:</h2>
              <span
                v-for="invitee in this.friends"
                v-bind:key="invitee.uniqueId"
              >
                {{ invitee }}
              </span>
            </div>
            <br/>
            <div class="tile is-child box">
              <h2 class="title is-size-5">Voting Deadline:</h2>
              <span> {{ this.invite.deadline }}</span>
<div class="tile is-child">
              <b-button
                v-show="!this.invite.deadlinePassed"
                class="button is-success is-rounded tile is-child has-text-justified has-text-centered"
              >Voting Open</b-button>
              <b-button
                label="Voting Closed"
                placeholder="Voting Closed"
                v-show="this.invite.deadlinePassed"
                class="button is-danger is-rounded tile is-child"
              />
</div>
            </div>
            <br/>
            <div class="tile is-child box">
              <h2 class="title is-size-5">Winning Restaurant</h2>
            </div>
          </div>
        </div>
      </div>
      <div class="tile is-parent">
      <b-collapse
        class="tile is-child is-10"
        animation="slide"
        :open.sync="isOpen"
        aria-id="contentIdForA11y3"
      >
        <template #trigger="props">
          <br/>
          <div class="tile is-child box is-12">
            <p class="title is-child has-text-centered">Finalists</p>
            
          </div>
        </template>

        <br/>
          <div>
            <restaurant-invite-detail-card
              class="tile is-child"
              v-for="restaurant in this.businessDetails"
              v-bind:restaurant="restaurant"
              v-bind:key="restaurant.id"
            />
        </div>
      </b-collapse>
      </div>
    </main>
  </section>
</template>

<script>
import InviteService from "../services/InviteService";
import RestaurantInviteDetailCard from "../components/RestaurantInviteDetailCard";

export default {
  components: { RestaurantInviteDetailCard },
  name: "invite-detail",

  data() {
    return {
      invite: {},
      isLoading: true,
      friends: [],
      businessDetails: [],
      isOpen: false,

      /*invite.businessDetails loop to get businesses*/
    };
  },
  created() {
    InviteService.getInviteById(this.$route.params.id).then((response) => {
      this.invite = response.data;
      this.isLoading = true;
      for (let j = 0; j < this.invite.invitees.length; j++) {
        this.friends.push(this.invite.invitees[j].name);
        if (j < this.invite.invitees.length - 1) {
          this.friends[j] += ", ";
        }
      }
      this.businessDetails = this.invite.businessDetails;
      this.businessDetails.sort(function (a, b) {
        return b.totalThumbsUp - a.totalThumbsUp;
      });
      this.isLoading = false;
    });
  },
};
</script>

<style scoped>
/*main {
    display: flex;
}
.finalists {
  display: flex;
  flex-wrap: wrap;
}*/
.final-head {
  text-align: center;
  text-decoration: underline;
}
.head {
  font-size: 18pt;
  font-weight: bold;
}
/*.invite-card {
  width: 33%;
}*/
</style>