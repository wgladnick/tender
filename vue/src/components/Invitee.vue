<template>
  <div class="main">
    <div class="welcome-message" v-if="!invitee.deadlinePassed && !isLoading">
      <h2>Hey, {{ invitee.name }}</h2>
      <p> <span>Voting Deadline: {{ invitee.deadline | moment("dddd, MMMM Do YYYY h:mm a") }}</span></p>
      <p> <span>Reservation Date: {{ invitee.reservationDate | moment("dddd, MMMM Do YYYY h:mm a") }}</span></p>
      <div>
      <span v-show="invitee.isAttending === 'Pending'">
        <b-button type="is-primary" rounded size="is-small" class="m-2"
        v-on:click="isAttending('Attending')">
          <i class="far fa-thumbs-up"></i>
          Yes I'll be there!</b-button
        >

        <b-button type="is-primary" rounded size="is-small" class="m-2"
        v-on:click="isAttending('Declined')">
          <i class="far fa-sad-tear"></i>
          Sorry Maybe next time!</b-button
        >
        </span>

        <span v-show="invitee.isAttending !== 'Pending'">
          <p>RSVP Status: {{ invitee.isAttending }} </p>
          <b-button type="is-primary" rounded size="is-small" class="m-2"
        v-on:click="isAttending('Pending')">
          <i class="fas fa-exchange-alt"></i>
          Change my Status</b-button
        >
        </span>
      </div>




    <div>
      <span>
         <b-button type="is-primary" rounded size="is-small" class="m-2"
        v-on:click="hasVoted(true)"
        v-show="!this.invitee.hasVoted">
          <i class="fas fa-check"></i>
          I'm done voting!</b-button
        >
      </span>

      <span>
 <b-button type="is-primary" rounded size="is-small" class="m-2"
        v-on:click="hasVoted(false)"
        v-show="this.invitee.hasVoted">
          <i class="fas fa-times"></i>
          Hey, let me revote!</b-button
        >
      </span>

    </div>
    <div class="restaurants">
      <restaurant-card
        v-for="restaurant in this.businessDetails"
        :key="restaurant.id"
        :restaurant="restaurant"
        class="card"
      />
    </div>
    </div>

    

    <div v-if="errorMsg">Invite not Found!!!!!!!!!</div>
    <div v-if="invitee.deadlinePassed">
     <h1> Sorry, the voting period for this invite has passed.</h1>
    </div>
  </div>

  
</template>

<script>
import InviteService from "../services/InviteService";
import RestaurantCard from "./RestaurantCard.vue";

export default {
  components: { RestaurantCard },
  data() {
    return {
      invitee: {},
      isLoading: true,
      errorMsg: false,
      businessDetails: [],
      currentTime: '',
      deadlinePassed: '',
      minutesGranularity: 15,
            hoursGranularity: 1,
            time: '',
    };
  },
  created() {
    InviteService.getInvitee(this.$route.params.uniqueId).then((response) => {
      this.invitee = response.data;

      this.isLoading = false;
      if (this.invitee.uniqueId === null) {
        this.errorMsg = true;
      }
      this.businessDetails = this.invitee.businessDetails;
      this.invitee.businessDetails = null;
      this.$store.commit("SET_CURRENT_INVITEE", this.invitee);
      this.isLoading = false;

    });
  },

  methods: {
    isAttending(status) {
      this.invitee.isAttending = status;
      InviteService.updateInvitee(this.invitee);
      this.$store.commit("SET_CURRENT_INVITEE", this.invitee);
    },
    hasVoted(status) {
      this.invitee.hasVoted = status;
      InviteService.updateInvitee(this.invitee);
      this.$store.commit("SET_CURRENT_INVITEE", this.invitee);
    },
  },
};
</script>

<style scoped>
h1{
    font-size: 2.7vw;
    text-align:center;
}

h2{
    font-size: 2.3vw;
    font-weight:600;
    text-align:center;
}

h3{
    font-size: 1.7vw;;
    font-weight:bold;
    text-align:center;
}

p{
    font-size:1.5vw;
    text-align:center;
}
.main {
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.welcome-message {
  display: flex;
  flex-direction: column;
  justify-content: center;
  text-align: center;
  margin-bottom: 2em;
}

.restaurants {
  display: flex;
}
.button.is-primary {
  background-color: #dc6b67;
}
.button.is-primary:hover {
  background-color: #d6544f;
}
</style>