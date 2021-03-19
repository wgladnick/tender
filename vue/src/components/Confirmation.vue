<template>
  <div>
    <div class="container mb-4">
      <div class="is-primary">
        <h1 class="title has-text-weight-semibold">Yay! Your invitation is all set. Here is your confirmation:</h1>
        <h2 class="is-size-2 has-text-weight-semibold">{{ invitation.inviteName }}</h2>
      </div>
    </div>
    <div class="container mb-4">
      <div class="">
        <p class="is-size-5 has-text-weight-semibold">Reservation Date: 
        <span class="is-size-5 has-text-weight-medium">
          {{
            invitation.reservationDate | moment("dddd, MMMM Do, YYYY h:mm a")
          }}
        </span></p>
        <p class="is-size-5 has-text-weight-semibold">Voting Deadline: 
        <span class="is-size-5 has-text-weight-medium">{{ invitation.deadline | moment("dddd, MMMM Do, YYYY h:mm a") }}</span></p>
      </div>
    </div>
    <div class="container mb-4">
      <div class="">
        <h3 class="is-size-4 has-text-weight-semibold">Friends List</h3>
        <p>
          Send each friend on your list their unique voting link to submit their
          restaurant vote
        </p>
        <span
          v-for="invitee in invitation.invitees"
          v-bind:key="invitee.uniqueId"
        >
          <div class="">
            <p class="is-size-5 has-text-weight-semibold">Invite Link for {{ invitee.name }}</p>
          </div>
          <div class="field is-grouped">
            <p class="control is-expanded">
              <input
                :id="invitee.uniqueId"
                class="input"
                type="text"
                :value="
                  'https://www.tenderinvite.com/invite/' + invitee.uniqueId
                "
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
        </span>
      </div>
    </div>
    <div class="container">
      <div class="">
        <p>Restaurant Choices</p>
        <div
          class="columns"
          v-for="restaurant in invitation.businessDetails"
          v-bind:key="restaurant.yelpId"
        >
          <div class="column">
            <figure class="image is-square">
              <img :src="restaurant.image_url" />
            </figure>
          </div>
          <div class="column">
            {{ restaurant.name }}
          </div>
        </div>
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
    // DELETE the api call below.
    InviteService.getInviteById(1).then((response) => {
      this.invitation = response.data;
    });
    // DELETE ABOVE
  },
  methods: {
    copyUrl(uniqueId) {
      const idToCopy = "#" + uniqueId;
      var copyText = document.querySelector(idToCopy);
      copyText.select();
      document.execCommand("copy");
    },
  },
};
</script>

<style scoped>
</style>