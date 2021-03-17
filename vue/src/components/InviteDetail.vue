<template>
  <div class="main" v-if="!isLoading">
      <div class="left-panel">
      </div>
      <div class="right-panel">
          <div class="invite">
              <h1 class="invite-name">{{invite.inviteName}}</h1>
          </div>
          <div class="deadline">
              <span class="deadline-head">Voting Deadline:</span><span> {{invite.deadline}}</span>
          </div>
          <div class="deadline-passed">
              <b-button v-show="!this.invite.deadlinePassed" class="deadline-open">Voting Open</b-button>
              <b-button v-show="this.invite.deadlinePassed" class="deadline-close">Voting Closed</b-button>
          <div class="reservation-date">
              <span class="head">Reservation Date and Time:</span><span> {{invite.reservationDate}}</span>
          </div>
          <div class="invitees">
          <span class="head">Participants:</span><span
            v-for="invitee in this.friends"
            v-bind:key="invitee.uniqueId"
            > {{ invitee }} </span>
          </div>
          <div class="finalists">
              <span class="head">Finalists:</span>
              
              <restaurant-card v-for="restaurant in this.invite.businessDetails" :key="restaurant.id" :restaurant="restaurant" class="card"/>
                  <span class="head">Total Votes:</span><span class="total-votes"> {{this.invite.businessDetails.totalThumbsUp}}/{{this.friends.length}}</span>
              
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
      businessDetails: [],
      
      /*invite.businessDetails loop to get businesses*/
    };
  },
  created() {
    InviteService.getInviteById(this.$route.params.id).then((response) => {
      this.invite = response.data;
      console.log(this.invite);
       console.log("array" + this.invite.invitees);
      
        for (let j = 0; j < this.invite.invitees.length; j++) {
          this.friends.push(this.invite.invitees[j].name);
          if (j < this.invite.invitees.length - 1) {
            this.friends[j] += ", ";
          }   
        }
        this.businessDetails = this.invite.businessDetails;
       this.isLoading = false;
       console.log(this.invite.invitees);
       console.log(this.invite.invitees);
      
    });
  },
};
</script>

<style scoped>
.deadline-open {
  background-color: green;
}
.deadline-close {
  background-color: red;
}
.main {
    display:flex;
    flex-direction: column;
    justify-content:center;
    padding:100px;
}


h1{
    font-size: 2.7vw;
    text-align:center;


}

.card{
    padding-top:20px;
    margin-left:50px;
    margin-right:50px;
    
}

</style>