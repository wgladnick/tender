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
        <b-field label="Select datetime">
        <b-datetimepicker v-model="dinnerInvite.datetime"
            placeholder="Click to select...">

            <template #left>
                <b-button
                    label="Now"
                    type="is-primary"
                    icon-left="clock"
                    @click="dinnerInvite.datetime = new Date()" />
            </template>

            <template #right>
                <b-button
                    label="Clear"
                    type="is-danger"
                    icon-left="close"
                    outlined
                    @click="dinnerInvite.datetime = null" />
            </template>
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
            datetime: new Date(),
          }

      };
  },
  
  
  methods: {
    addField () {
      let input = document.createElement("b-input");
      input.type = "email";
      container.addField(input);
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