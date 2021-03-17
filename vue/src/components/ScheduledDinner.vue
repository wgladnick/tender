<template>
  <section>

    <b-collapse class="card" animation="slide" aria-id="contentIdForA11y3">
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
            <p>When: {{ dinner.reservationDate }}</p>

            <p
            v-for="rst in dinner.businessDetails" 
            v-bind:key="rst.id"
            >Where: {{ rst.name }}</p>

            <p>Friends: </p>
            <span
            v-for="invitee in this.friends"
            v-bind:key="invitee.uniqueId"
            > {{ invitee }} </span>
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
      friends: []

    }
  },

  created() {
    for (let j = 0; j < this.dinner.invitees.length; j++) {
          this.friends.push(this.dinner.invitees[j].name);
          if (j < this.dinner.invitees.length - 1) {
            this.friends[j] += ", ";
          } 
        }

      
      
  }



}
</script>

<style scoped>

.card-header-title:hover {
  color: #dc6b67;
}


</style>