<template>
  <form>
      <h1>Create Dinner</h1>
    <div class="input-fields">
      <b-field label="Dinner Name">
        <b-input type="text"/>
      </b-field>
        <b-field label="Select Date and Time">
            <b-datetimepicker
                rounded
                placeholder="Click to select..."
                icon="calendar-today"
                :locale="locale"
                :datepicker="{ showWeekNumber }"
                :timepicker="{ enableSeconds, hourFormat }"
                horizontal-time-picker>
            </b-datetimepicker>
        </b-field>
        <b-input
          id="email"
          type="text"
          placeholder="Enter friend's email"
          />
    </div>
    
  </form>
</template>

<script>
export default {
  name: "invitation-card",

  data() {
      return {
          invites: []
      };
  }



};
</script>

<style>
</style>