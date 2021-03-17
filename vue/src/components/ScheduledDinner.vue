<template>
  <section>

    <b-collapse class="card" animation="slide" :open.sync="isOpen" aria-id="contentIdForA11y3">
        <template #trigger="props">
          <div
            class="card-header"
            role="button"
            aria-controls="contentIdForA11y3">
            <h1 class="card-header-title"> {{ dinner.inviteName }} </h1>
            <a class="card-header-icon">
              <b-icon
                :icon="props.open ? 'menu-down' : 'menu-up'">
              </b-icon>
            </a>
          </div>
        </template>

        <div class="card-content">
          <div class="content">
            <p><strong>When: </strong> {{ dinner.reservationDate | moment("dddd, MMMM Do YYYY h:mm a")}}</p>

            <p><strong>Where: </strong> 
            <span 
            v-for="rst in this.restaurants" 
            v-bind:key="rst.name"
            > {{ rst }} </span></p>

            <p><strong>Friends: </strong> 
            <span
            v-for="invitee in this.friends"
            v-bind:key="invitee.uniqueId"
            > {{ invitee }} </span></p>
            <router-link :to="{ name: 'inviteDetailsPage', params: {id: dinner.inviteId} }">
              <b-button class="btn" type="is-info" size="is-small">View Details</b-button>
            </router-link>
          </div>
        </div>
    </b-collapse>

  </section>
</template>

<script>
export default {
  name: 'scheduled-dinner', 
  props:  ["dinner"],

  data() {
    return {
      restaurants: [],
      friends: [],
      isOpen: false

    }
  },

  created() {
    for (let j = 0; j < this.dinner.invitees.length; j++) {
          this.friends.push(this.dinner.invitees[j].name);
          if (j < this.dinner.invitees.length - 1) {
            this.friends[j] += ", ";
          } 
        }
    for (let j = 0; j < this.dinner.businessDetails.length; j++) {
          this.restaurants.push(this.dinner.businessDetails[j].name);
          if (j < this.dinner.businessDetails.length - 1) {
            this.restaurants[j] += ", ";
          } 
        }

      
      
  }



}
</script>

<style scoped>

.card-header-title:hover {
  color: #dc6b67;
}

.btn {
  background-color: #dc6b67;
}

.btn:hover {
  background-color: #f7a09d;
}


</style>