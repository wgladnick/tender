<template>
  <div class="main">
    <div class="welcome-message" v-if="!invitee.deadlinePassed">
      <h2>Hey, {{ invitee.name }}</h2>
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
      Sorry, the voting period for this invite has passed.
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

    });
  },

  methods: {
    isAttending(status) {
      this.invitee.isAttending = status;
      InviteService.updateInvitee(this.invitee);
    },
    hasVoted(status) {
      this.invitee.hasVoted = status;
      InviteService.updateInvitee(this.invitee);
      console.log(this.invitee)
    } 
  },
};
</script>

<style scoped>
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