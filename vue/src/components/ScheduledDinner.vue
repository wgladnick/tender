<template>
  <section>

    <b-collapse class="card" animation="slide" aria-id="contentIdForA11y3">
        <template #trigger="props">
          <div
            class="card-header"
            role="button"
            aria-controls="contentIdForA11y3">
            <h1 
            class="card-header-title">
           
                {{ dinner.inviteName }}
            </h1>
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
            <p
            v-for="invitee in dinner.invitees"
            v-bind:key="invitee.uniqueId"
            >Friends: {{ invitee.name }}</p>
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

    }
  },

  created() {
    this.getInviteById();
  }

}
</script>

<style scoped>


</style>