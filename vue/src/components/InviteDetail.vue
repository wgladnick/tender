<template>
  <div class="main" v-if="!isLoading">
      <div class="left-panel">
      </div>
      <div class="right-panel">
          <div class="invite">
              <h1 class="invite-name">{{this.invite.inviteName}}</h1>
          </div>
          <div class="deadline">
              <span class="deadline-head">Voting Deadline:</span><span> {{this.invite.deadline}}</span>
          </div>
          <div class="deadline-passed">
              <b-button v-show="!this.invite.deadlinePassed" class="deadline-open">Voting Open</b-button>
              <b-button v-show="this.invite.deadlinePassed" class="deadline-close">Voting Closed</b-button>
          <div class="reservation-date">
              <span class="res-head">Reservation Date and Time:</span><span> {{this.invite.reservationDate}}</span>
          </div>
          <div class="invitees">
          <span class="invitee-head">Participants:</span><span
            v-for="invitee in this.friends"
            v-bind:key="invitee.uniqueId"
            > {{ invitee }} </span>
          </div>
          <div class="finalists">
              <span class="finalist-head">Finalists:</span>
              <div>
              <restaurant-card v-for="restaurant in this.invite.businessDetails" :key="restaurant.id" :restaurant="restaurant" class="card"/>
              <!--<span class="total-votes"> {{this.invite.businessDetails.totalThumbsUp}}/{{this.invite.invitees.length}}</span>-->
              </div>
          </div>
          


          </div>         
      </div>
  </div>
</template>

<script>
import InviteService from "../services/InviteService";
import RestaurantCard from "./RestaurantCard.vue";
export default {
  components: { RestaurantCard },
  name: "invite-detail",

  data() {
    return {
      invite: {},
      isLoading: true,
      friends: [],
      /*invite.businessDetails loop to get businesses*/
    };
  },
  created() {
    InviteService.getInviteById(this.$route.params.id).then((response) => {
      this.invite = response.data;
      this.isLoading = false;
      for (let j = 0; j < this.invite.invitees.length; j++) {
          this.friends.push(this.invite.invitees[j].name);
          if (j < this.invite.invitees.length - 1) {
            this.friends[j] += ", ";
          } 
        }
      console.log(this.invite);
    });
  },
};
</script>

<style>
.deadline-open {
  background-color: green;
}
.deadline-close {
  background-color: red;
}
</style>