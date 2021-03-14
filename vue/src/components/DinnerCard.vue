<template>
  <form>
      <h1>Create Dinner</h1>
    <div class="input-fields">
      <b-field label="Dinner Name">
        <b-input v-model="dinnerInvite.dinnerName" type="text"/>
      </b-field>
      <b-field label="First Name">
        <b-input v-model="dinnerInvite.firstName" type="text"/>
      </b-field>
        <b-field label="Select Date and Time">
            <b-datetimepicker v-model="dinnerInvite.time"
                rounded
                placeholder="Click to select..."
                icon="calendar-today"
                :locale="locale"
                :datepicker="{ showWeekNumber }"
                :timepicker="{ enableSeconds, hourFormat }"
                horizontal-time-picker>
            </b-datetimepicker>
        </b-field>
        <div id="container">
        <b-field label="Who's invited?">
        <b-input
          v-model="dinnerInvite.email" id="email"
          type="email"
          placeholder="Enter friend's email"
          />
          <b-button v-on:click="addField()" id="add-invitee"> Add Invitee</b-button>
        </b-field>
    </div>
    </div>
    <b-button type="submit"> Click to find out Where Dinner will be</b-button>
  </form>
</template>

<script>
export default {
  name: "create-dinner",
  

  data() {
      return {
        showWeekNumber: false,
            enableSeconds: false,
            hourFormat: undefined, // Browser locale
            locale: undefined, // Browser locale
          invitees: [],
          dinnerInvite: {
            dinnerName: "",
            firstName: "",
            email: "",
            time: "",
          }

      };
  },
  
  
  methods: {
    addField () {
      let input = document.createElement("b-input");
      input.type = "email";
      container.appendChild(input);
      container.appendChild(document.createElement("br"));
      }
      
    },
  addInviteesToDinner(){
    this.invitees = [];
  }

};
</script>

<style>

</style>