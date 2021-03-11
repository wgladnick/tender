<template>
  <div>
      {{invitee.firstName}} {{ invitee.lastName}}

  <div v-show="invitee.isAttending !== 'true'">
      <b-button type="is-primary" rounded size="is-small" class="m-2">
          <i class="far fa-thumbs-up"></i>
          Yes I'll be there!</b-button>

          <b-button type="is-primary" rounded size="is-small" class="m-2">
          <i class="far fa-sad-tear"></i>
          Sorry Maybe next time!</b-button>
  </div>
  <restaurant-card
  v-for="restaurant in invitee.businessDetails"
  :key="restaurant.id"
  :restaurant="restaurant" 
  />
  </div>
</template>

<script>
import InviteService from "../services/InviteService"
import RestaurantCard from './RestaurantCard.vue';

export default {
  components: { RestaurantCard },
      data() {
    return {

      invitee: {},

      isLoading: true,

    };
  },
     created() {
    InviteService.getInvitee(this.$route.params.uniqueId).then(
      (response) => {
        this.invitee = response.data;
        this.isLoading = false;
      }
    );
  },


}
</script>

<style>
.button.is-primary {

  background-color: #dc6b67;
}
.button.is-primary:hover {
    background-color: #d6544f;
}
</style>