<template>
  <b-form>
    <h1>Create Dinner</h1>
    <div class="input-fields">
      <b-field label="Dinner Name">
        <b-input v-model="dinnerInvite.dinnerName" type="text" />
      </b-field>
      <b-field label="First Name">
        <b-input v-model="dinnerInvite.firstName" type="text" />
      </b-field>
      <b-field label="Select datetime">
        <b-datetimepicker
          v-model="dinnerInvite.datetime"
          placeholder="Click to select..."
        >
          <template #left>
            <b-button
              label="Now"
              type="is-primary"
              icon-left="clock"
              @click="dinnerInvite.datetime = new Date()"
            />
          </template>

          <template #right>
            <b-button
              label="Clear"
              type="is-danger"
              icon-left="close"
              outlined
              @click="dinnerInvite.datetime = null"
            />
          </template>
        </b-datetimepicker>
        <input type="datetime-local" v-model="datetime2" />
      </b-field>
      <div id="email-container"
       >
       <div>
        <b-field v-for="(email, i) in emailFields"
            :key="i"
            class="email-fields-row" id="emails" v-model="emailFields" label="Who's invited?">
          <b-input id="email" type="email" :label="email.label1" v-model="email.value1" placeholder="Enter friend's email" />
          
          
        </b-field>
        <b-button @click="add()" id="add-invitee"> Add Invitee</b-button>
        <b-button @click="remove(i)" class="delete" id="delete-invite" v-if="emailFields.legnth > 1">Delete</b-button>
       </div>
      </div>
      
      
    </div>
    <b-button type="submit"> Click to find out Where Dinner will be</b-button>
  </b-form>
</template>

<script>
import InviteService from "../services/InviteService";
export default {
  name: "create-dinner",

  data() {
    return {
      showWeekNumber: false,
      enableSeconds: false,
      hourFormat: undefined, // Browser locale
      locale: undefined, // Browser locale
      dinnerInvite: {
        dinnerName: "",
        datetime: new Date(),
        datetime2: "",
      },
      emailFields: [],
      
    };
  },

  methods: {
    /*addField() {
      let input = document.createElement("input");
      input.type = "text";
      container.appendChild(input);
      container.appendChild(document.createElement("br"));
    },*/
    addInviteesToDinner() {
      InviteService.sendInvite().then((response) => {
        this.emailFields = response.data;
      });
    },
    add () {
        this.emailFields.push({ 
          label1: "Invite-Email", 
          value1: "",
        })
     },
    
     remove (index) {
         this.emailFields.splice(index, 1)
     },
     
  
}
}

</script>

<style>
</style>