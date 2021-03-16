<template>
  <div class="main">
    <div class="welcome-message">
      <h2>Hey {{ invitee.firstName }}</h2>

      <div v-show="invitee.isAttending !== 'true'">
        <b-button type="is-primary" rounded size="is-small" class="m-2">
          <i class="far fa-thumbs-up"></i>
          Yes I'll be there!</b-button
        >

        <b-button type="is-primary" rounded size="is-small" class="m-2">
          <i class="far fa-sad-tear"></i>
          Sorry Maybe next time!</b-button
        >
      </div>
    </div>

    <div class="restaurants">
      <restaurant-card
        v-for="restaurant in invitee.businessDetails"
        :key="restaurant.id"
        :restaurant="restaurant"
        class="card"
        @place-vote="placeVote"
      />
    </div>

    <div v-if="errorMsg">Invite not Found!!!!!!!!!</div>
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

      isVotedSubmitted: false,

      inviteeVotes: {
        yelpId: "",
        uniqueId: "",
        inviteId: "",
        thumbsUp: false,
        thumbsDown: false,
      },
    };
  },
  created() {
    InviteService.getInvitee(this.$route.params.uniqueId).then((response) => {
      this.invitee = response.data;

      this.isLoading = false;
      if (this.invitee.uniqueId === null) {
        this.errorMsg = true;
      }
    });
  },

  methods: {
    placeVote(vote) {
      this.inviteeVotes.yelpId = vote.yelpId;
      this.inviteeVotes.thumbsUp = vote.thumbsUp;
      this.inviteeVotes.thumbsDown = vote.thumbsDown;
      this.inviteeVotes.uniqueId = this.invitee.uniqueId;
      this.inviteeVotes.inviteId = this.invitee.inviteId;

      if (!vote.isVoteSubmitted) {
        if (this.inviteeVotes.thumbsUp) {
          InviteService.voteThumbsUp(this.inviteeVotes);
        } else {
          InviteService.voteThumbsDown(this.inviteeVotes);
        }
      } else {
        console.log(this.inviteeVotes);
        InviteService.undoVote(this.inviteeVotes);
      }
    },
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