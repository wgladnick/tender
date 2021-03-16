<template>
  <section>

    <b-collapse class="card" animation="slide" aria-id="contentIdForA11y3">
        <template #trigger="props">
          <div
            class="card-header"
            role="button"
            aria-controls="contentIdForA11y3">
            <p class="card-header-title">
                {{ dinner.inviteName }}
            </p>
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
            <p>Where: {{ dinner.restaurantChoices.yelpId }}</p>
            <p>Friends: {{ dinner.invitees.name }}</p>
          </div>
        </div>
    </b-collapse>

  </section>
</template>

<script>
export default {
   /* props:  [dinner] */

}
</script>

<style scoped>


</style>