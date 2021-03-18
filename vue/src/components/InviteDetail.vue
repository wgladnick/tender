<template>
  <main class="tile is-ancestor">
    <div class="tile is-3  is-parent box">
      <div class="loading-gif" v-if="isLoading">
        <img src="../assets/loading.gif" />
      </div>
      <div class="tile is-child is-vertical">
          <h1 class="title">{{ this.invite.inviteName }}</h1>
          <div class="tile is-child">
          <h2 class="title is-size-5">Voting Deadline:</h2
          ><span> {{ this.invite.deadline }}</span>
          </div>
      
            <b-button
              label="Voting Open"
              placeholder="Voting Open"
              v-show="!this.invite.deadlinePassed"
              class="button is-success is-rounded"
            />
            <b-button
              label="Voting Closed"
              placeholder="Voting Closed"
              v-show="this.invite.deadlinePassed"
              class="button is-danger is-rounded"
            />
          
        <h2 class="title is-size-5">Reservation Date and Time:</h2
        ><span> {{ this.invite.reservationDate }}</span>
      
      
        <h2 class="title is-size-5">Participants:</h2
        ><span v-for="invitee in this.friends" v-bind:key="invitee.uniqueId">
          {{ invitee }}
        </span>
      
      </div>

      
    </div>
    
    <div class="tile is-vertical is-parent">
        <div class="is-child box"><span class="title">Finalists</span></div>
        <div class="is-child box">
<restaurant-invite-detail-card class="is-child" v-for="restaurant in this.invite.businessDetails" v-bind:restaurant="restaurant" v-bind:key="restaurant.id"/>
        </div>
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
.deadline-open {
  background-color: green;
  color: white;
  width: auto;
}
.deadline-close {
  background-color: red;
  width: auto;
}

h1 {
  font-size: 2.7vw;
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
.left-panel {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  width: 30%;
  background-color: white;
}
</style>