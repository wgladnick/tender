<template>
  <main>
    <div class="main">
      <div class="loading-gif" v-if="isLoading">
        <img src="../assets/loading.gif" />
      </div>
      <div class="left-panel">
        <div class="invite">
          <h1 class="invite-name">{{ this.invite.inviteName }}</h1>
        </div>
        <div class="deadline">
          <span class="head">Voting Deadline:</span
          ><span> {{ this.invite.deadline }}</span>
          <div class="deadline-passed">
            <b-button
              label="Voting Open"
              placeholder="Voting Open"
              v-show="!this.invite.deadlinePassed"
              class="deadline-open"
            />
            <b-button
              label="Voting Closed"
              placeholder="Voting Closed"
              v-show="this.invite.deadlinePassed"
              class="deadline-close"
            />
          </div>
        </div>
      </div>

      <div class="reservation-date">
        <span class="head">Reservation Date and Time:</span
        ><span> {{ this.invite.reservationDate }}</span>
      </div>
      <div class="invitees">
        <span class="head">Participants:</span
        ><span v-for="invitee in this.friends" v-bind:key="invitee.uniqueId">
          {{ invitee }}
        </span>
      </div>
    </div>
    <span class="head">Finalists:</span>
    <div class="finalists">
        
<restaurant-invite-detail-card class="invite-card" v-for="restaurant in this.invite.businessDetails" v-bind:restaurant="restaurant" v-bind:key="restaurant.id"/>
        
    </div>
  </main>
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
      this.isLoading = false;
    });
  },
};
</script>

<style scoped>
.finalists {
    display: flex;
    flex-wrap: wrap;

}
.deadline-open {
  background-color: green;
  color: white;
}
.deadline-close {
  background-color: red;
}
.main {
  display: flex;
  flex-direction: row;
  width: 100%;
  justify-content: center;
  align-items: center;
}

h1 {
  font-size: 2.7vw;
  text-align: center;
}
.head {
  font-size: 18pt;
  font-weight: bold;
}
.invite-card {
    width: 33%
}
</style>