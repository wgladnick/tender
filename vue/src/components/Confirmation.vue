<template>
  <div class="card">
    <h1>Yay! Your invitation is all set. Here is your confirmation:</h1>
    <br />
    <h2>{{ invitation.inviteName }}</h2>
    <br />
    <h3>Reservation Date:</h3>
    <p>
      {{ invitation.reservationDate | moment("dddd, MMMM Do, YYYY h:mm a") }}
    </p>
    <br />

    <h3>Voting Deadline</h3>
    <p>{{ invitation.deadline | moment("dddd, MMMM Do, YYYY h:mm a") }}</p>
    <br /><br />
    <h3>Friends List</h3>
    <p>
      Send each friend on your list their unique voting link to submit their
      restaurant vote
    </p>
    <br />
    <span v-for="invitee in invitation.invitees" v-bind:key="invitee.uniqueId">
      <div class="">
        <p>Invite Link for {{ invitee.name }}</p>
      </div>
      <div class="field is-grouped">
        <p class="control is-expanded">
          <input
            :id="invitee.uniqueId"
            class="input"
            type="text"
            :value="'https://www.tenderinvite.com/invite/' + invitee.uniqueId"
            readonly
          />
        </p>
        <p class="control">
          <button
            class="button"
            @click.stop.prevent="copyUrl(invitee.uniqueId)"
          >
            <span class="icon mr-1"> <i class="far fa-copy"></i> </span>Copy
          </button>
        </p>
      </div>

      <br />
    </span>
    <p>Restaurant Choices</p>
    <div
      class="columns"
      v-for="restaurant in invitation.businessDetails"
      v-bind:key="restaurant.yelpId"
    >
      <div class="column">
        <img :src="restaurant.image_url" />
      </div>
      <div class="column">
        {{ restaurant.name }}
      </div>
    </div>
  </div>
</template>

<script>
import InviteService from "../services/InviteService.js";
export default {
  data() {
    return {
      invitation: {},
    };
  },
  created() {
    this.invitation = this.$store.state.createdInvite;
    InviteService.getInviteById(3).then((response) => {
      this.invitation = response.data;
    });
  },
  methods: {
    copyUrl(uniqueId) {
      const idToCopy = "#" + uniqueId;
      var copyText = document.querySelector("#" + uniqueId);
      copyText.select();
      document.execCommand("copy");
    },
  },
};
</script>

<style scoped>
</style>