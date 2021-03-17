 <template>
  
    <div class="input-fields">
     
     <label for="inviteName">What's the Occasion?</label>
      <b-field>
        <b-input id="inviteName" v-model="dinnerInvite.inviteName" type="text" />
      </b-field>
      <label for="reservation">Reservation Date:</label>
    
      <input type="date" v-model="reservationDate" />
      <input class="date" type="time" v-model="reservationTime" /><br>
     <label for="searchdeadline">Deadline to Vote:</label>
      <input id="deadline" type="date" v-model="deadlineDate" />
      <input class="date" step="900" type="time" v-model="deadlineTime" /><br>

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
           <button @click="remove(i)" class="delete" id="delete-invitee" />
            <b-input
              id="name"
              type="text"
              v-model="invitee.name"
              placeholder="Enter friend's name:"
            />
            <b-input
              id="email"
              type="email"
              v-model="invitee.email"
              placeholder="Enter friend's email:"
            />
           
          </b-field>
          <div>
            <span class="add-guests">
            <b-button @click="add()" id="add-invitee"
              ><i class="fas fa-plus"></i
            > Add Guests</b-button>
            </span>
          </div>
          <span class="create-invite">
          <b-button v-on:click="createInvite">
           Create Invite</b-button>
          </span>
        </div>
      </div>
    </div>
  
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
      reservationDate: "",
      reservationTime: "",
      deadlineDate: "",
      deadlineTime: "",
      location: "",
      radius: "",
      restaurantChoices: [],
      dinnerInvite: {
        creatorId: "",
        inviteName: "",
        reservationDate: "",
        deadline: "",
        invitees: [],
      },
      createdInvite:{}
    };
  },

  methods: {
    createInvite() {
      this.dinnerInvite.reservationDate =
        this.reservationDate + " " + this.reservationTime;
      this.dinnerInvite.deadline = this.deadlineDate + " " + this.deadlineTime;
      this.dinnerInvite.creatorId = this.$store.state.user.id;
      this.$store.commit("CREATE_INVITATION", this.dinnerInvite);
     
      InviteService.sendInvite(this.$store.state.invitation).then ((response) => {
        this.createdInvite = response.data;
        this.$store.commit("SET_CREATED_INVITE", this.createdInvite);
        console.log(this.$store.state.createdInvite);
        this.$router.push('/confirmation');
      })
  
    },
   
   
    add() {
      this.dinnerInvite.invitees.push({});
    },

    remove(index) {
      this.dinnerInvite.invitees.splice(index, 1);
    },
  },
};
</script>

<style scoped>



.delete{
  margin-top:.5em;
  margin-right:1em;
  
}

.create-invite button{
  background-color: #81974e;
}


button {
  background-color: #dc6b67;
  border: none;
  color: white;
  padding: 10px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 5px;
  width: 100%;
  margin-top: 30px;
  margin-top: 2em;
  font-weight: bold;
}

button:hover {
  background-color: #f7a09d;
  color:white;


}

#name.input.is-danger{
  width: 100%;
  height: 40px;
  border-radius: 3px;
  padding-left: 0.5em;
  font-size: 1em;
  font-weight: 600;
 
}
.date{
  margin-top:.3em;
}
h1 {
  font-size: 20pt;
  font-weight: bold;
}
.input-fields {
  padding: 1.5em;
  display: flex;
  flex-direction: column;
}
.add-invitees {
  font-weight: bold;
}
.date {
  font-size: 15pt;
}
.deadline {
  font-size: 15pt;
}
.dinner {
  font-size: 15pt;
  
}
label {
  font-weight: bold;
}
input {
  width: 100%;
  height: 40px;
  border-radius: 3px;
  padding-left: 0.5em;
  font-size: 1em;
  font-weight: 600;
}

b-input {
    width: 100%;
  height: 40px;
  border-radius: 3px;
  padding-left: 0.5em;
  font-size: 1em;
  font-weight: 600;

}
/*.input-fields {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  width: 100vw;
  */



.headingText p {
  margin-bottom:1em;
}
</style>