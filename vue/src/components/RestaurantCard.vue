<template>
  <span class="zoom">

    <div class="left-panel">
      <router-link :to="{ name: 'details', params: { id: restaurant.id } }"
        ><img :src="restaurant.image_url" class="yelp-image"
      /></router-link>
    </div>
    <div class="right-panel">
      <div class="categories">
        <span
          class="categories-tag"
          v-for="category in this.categories"
          v-bind:key="category"
        >
          {{ category }}
        </span>
      </div>
      <router-link :to="{ name: 'details', params: { id: restaurant.id } }"
        ><h1 class="title">{{ restaurant.name }}</h1>
      </router-link>
      <div class="star-rating">
        <star-rating
          :rating="restaurant.rating"
          :read-only="true"
          :increment="0.01"
          :show-rating="false"
          :inline="true"
          :star-size="25"
        />
      </div>
      <div class="location-phone">
        <span
          v-for="ln in restaurant.location.display_address"
          v-bind:key="ln.display_address"
        >
          {{ ln }}
        </span>

        <br />
        <span>{{ restaurant.display_phone }} </span>

        <p class="price">
          Price:
          <span class="has-text-success has-text-weight-bold">{{
            restaurant.price
          }}</span>
        </p>
      </div>

      <!-- Vote Buttons -->
      <!-- Like -->
      <span class="invite-buttons" v-if="$route.name === 'inviteeView'" v-show="!this.$store.state.currentInvitee.hasVoted">
        <span class="yes-button" v-show="!this.$store.state.currentInvitee.hasVoted">
          <b-button v-on:click="thumbsUp()" type="is-primary" rounded size="is-small" class="m-2" v-show="!hasVoted">
            <i class="far fa-thumbs-up"></i>
            LIKE</b-button
          >
        </span>
         <!-- NAH -->
        
          <b-button v-on:click="thumbsDown()" type="is-primary" rounded size="is-small" class="m-2" v-show="!hasVoted">
            <i class="far fa-sad-tear"></i>
            NAH</b-button
          >
           <!-- Undo Vote -->
        
          <b-button v-on:click="undoVote()" type="is-primary" rounded size="is-small" class="m-2" v-show="hasVoted">
            <i class="fas fa-undo-alt"></i>
            Whoops</b-button
          >
       
       
      </span>


       <!-- Call To Order -->
      <span v-if="$route.name !== 'inviteeView'" class="action-buttons">
        <span class="call-to-order">
        <a :href="`tel:${restaurant.phone}`">
          <b-button type="is-primary" rounded size="is-small">
            <i class="fas fa-phone-alt"></i>
            Call to order</b-button
          >
        </a>
        </span>

        <!-- Add to List -->
        <span class ="add-to-list">
         <b-button type="is-primary" rounded size="is-small"
           v-show="!this.$store.state.sideMenuToggle" v-on:click="addToList(restaurant)">
           <i class="fas fa-plus"></i>
           Add To Invite List</b-button>
           </span>
           
       
      </span>

      <!-- Delivery/ TakeOut Tags -->
      <div v-if="$route.name !== 'inviteeView'" class="transactions">
        <b-button size="is-small">
          <i
            class="fas fa-check has-text-success"
            v-if="transactionTypes.includes('pickup')"
          ></i>
          <i
            class="fas fa-times has-text-danger"
            v-if="!transactionTypes.includes('pickup')"
          ></i>
          Takeout</b-button
        >
        <b-button size="is-small">
          <i
            class="fas fa-check has-text-success"
            v-if="transactionTypes.includes('delivery')"
          ></i>
          <i
            class="fas fa-times has-text-danger"
            v-if="!transactionTypes.includes('delivery')"
          ></i>
          Delivery</b-button
        >
        <b-button size="is-small">
          <i
            class="fas fa-check has-text-success"
            v-if="transactionTypes.includes('restaurant_reservation')"
          ></i>
          <i
            class="fas fa-times has-text-danger"
            v-if="!transactionTypes.includes('restaurant_reservation')"
          ></i>
          Reservations</b-button
        >
        <br />
      </div>
    </div>

  
  </span>
</template>

<script>
import InviteService from '../services/InviteService.js'
import StarRating from "vue-star-rating";

export default {
  name: "restaurant-card",

  components: {
    StarRating,
  },

  props: ["restaurant"],

  data() {
    return {
      transactionTypes: "",

      categories: [],
      hasVoted: false,
      vote: {
        yelpId: this.restaurant.id,
        thumbs_up: false,
        thumbs_down: false,
        inviteId: this.$store.state.currentInvitee.inviteId,
        uniqueId:this.$store.state.currentInvitee.uniqueId,
      }
     
    }
  },
  created() {
    for (let i = 0; i < this.restaurant.transactions.length; i++) {
      this.transactionTypes +=
        this.restaurant.transactions[i].transactions + " ";
    }

    for (let j = 0; j < this.restaurant.categories.length; j++) {
      this.categories.push(this.restaurant.categories[j].title);
      if (j < this.restaurant.categories.length - 1) {
        this.categories[j] += " | ";
      }
    }
  },

  methods: { 
    thumbsUp(){
      this.vote.thumbs_up = true;
      this.vote.thumbs_down= false;
      InviteService.voteThumbsUp(this.vote)
      this.hasVoted = true;
    },

    thumbsDown(){
      this.vote.thumbsDown = true;
      this.vote.thumbsUp = false;
      InviteService.voteThumbsDown(this.vote);
      this.hasVoted = true;

    },

     undoVote(){
      this.vote.thumbs_down = false;
      this.vote.thumbs_up = false;
      InviteService.undoVote(this.vote);
      this.hasVoted = false;

    },

    addToList(restaurant) {
      if(!this.$store.state.invitation.restaurantChoices.includes(restaurant)){
      this.$store.commit("ADD_CHOICE_TO_LIST", restaurant);
      this.$emit('update-list', restaurant);
      console.log(this.$store.state.invitation.restaurantChoices);
      }
    },
  },
};
</script>

<style scoped>



.zoom {
  display: flex;
  flex-direction: row;
  
  padding: 1em;
  transition: transform 0.5s;

  margin: 0 auto;
}
.yelp-image {
  max-width: 350px;
  height: 350px;
  object-fit: cover;
}

.left-panel {
  padding: 20px;
  margin-right: 35px;
}
.right-panel {
  padding: 20px 20px 20px 0px;
}


.action-buttons {
 display:flex;
 margin-top:2em;


}
.button.is-primary {
  background-color: #dc6b67;
}


.nah-button .button.is-rounded {
  background-color: #dc6b67;
}
.nah-button .button.is-rounded:hover {
  background-color: #ad5451;
}

.yes-button .button.button.is-rounded {
  background-color: #a5c064;
}
.yes-button .button.button.is-rounded:hover {
  background-color: #9cbd4f;
}

.call-to-order .button.is-rounded:hover {
  background-color: #f1837f;
}
.add-to-list .button.button.is-rounded:hover {
  background-color: #9dbd52;
}
.add-to-list .button.button.is-rounded {
  background-color: #81974e;
}
.invite-buttons .button.is-rounded {
  width: 7em;
}
.button.is-rounded {
  border-radius: 290486px;
  padding-left: calc(1em + 0.25em);
  margin-right: 10px;
  padding-right: calc(1em + 0.25em);
  font-size: 1em;
  width: 12em;
}
.transactions > button.is-small {
  border: none;
  font-size: em;
  font-weight: bold;
}
.price {
  font-weight: 550;
}
.star-rating {
  padding: 5px 0px;
  display: inline-block;
  width: 0.2em;
}

.location-phone {
  padding-top: 1em;
}

.categories {
  padding: 10px 0px 0px 0px;
}
.categories-tag {
  color: rgb(179, 179, 179);
}

.transactions {
  margin-top: 25px;
  margin-left: -0.5em;
}

.zoom:hover {
  -ms-transform: scale(1.1); /* IE 9 */
  -webkit-transform: scale(1.1); /* Safari 3-8 */
  transform: scale(1.1);
}

.title:hover {
  color: #dc6b67;
}
</style>