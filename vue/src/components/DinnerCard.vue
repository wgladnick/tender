 <template>
  <b-form>
    <div class="input-fields">
      <h1 class="headingText">Create Dinner</h1>
      <b-field label="Dinner Name">
        <b-input v-model="dinnerInvite.inviteName" type="text" />
      </b-field>

       <div>
    <input type="date" v-model="reservationDate" />
    <input type="time" v-model="reservationTime" />

    <input type="date" v-model="deadlineDate" />
    <input type="time" v-model="deadlineTime" />
    <button v-on:click="sendInvitation">send</button>
  </div>
     
    
      <div id="email-container">
        <div>
          <b-field
            v-for="(invitee, i) in dinnerInvite.invitees"
            :key="i"
            class="invitee-fields"
            id="invitee"
            v-model="dinnerInvite.invitees"
            label="Who's invited?"
          >
            <b-input
              id="name"
              type="text"
              :label="invitee.label2"
              v-model="invitee.name"
              placeholder="Enter friend's name:"
            />
            <b-input
              id="email"
              type="email"
              :label="invitee.label1"
              v-model="invitee.email"
              placeholder="Enter friend's email:"
            />
              <button @click="remove(i)" class="delete" id="delete-invitee" />
          </b-field>
          <div>
            <b-button @click="add()" id="add-invitee"> Add Invitee</b-button>
          </div>
          <b-button v-on:click="createInvite">
            Create Dinner and Invite Friends</b-button
          >

        </div>
      </div>
    </div>
  </b-form>
</template>

<script>
import InviteService from "../services/InviteService";
export default {
  name: "create-dinner",

  data() {
    return {
      reservationDate:"",
      reservationTime: "",
      deadlineDate:"",
      deadlineTime:"",
      showWeekNumber: false,
      enableSeconds: false,
      hourFormat: undefined, // Browser locale
      locale: undefined, // Browser locale
      dinnerInvite: {
        inviteName: "",
        deadline:"",
        reservationDate: "",
        invitees: [],

   
    
      },
    };
  },

  computed: {
    reservationDateTime() {
      this.reservationDate = this.reservationDate + " " + this.reservationTime;
    },
    deadlineDateTime() {
      this.deadline = this.deadlineDate + " " + this.deadlineTime;
    },
  },

  methods: {
    createInvite() {
          this.$store.commit("CREATE_INVITATION", this.dinnerInvite);
          console.log(this.$store.state.invitation);
      
    
    },
    add() {
      this.dinnerInvite.invitees.push({

        value1: "",
  
        value2: "",
      });
    },

    remove(index) {
      this.dinnerInvite.invitees.splice(index, 1);
    },
  },
};
</script>

<style scoped>

.input-fields{
  padding:1.5em;
}
/*.input-fields {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  width: 100vw;
}
.headingText {
  text-align: center;
  font-weight: 700;
  font-size: 1.5em;
}*/
</style>