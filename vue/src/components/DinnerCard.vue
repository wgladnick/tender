 <template>
  <b-form>
    <div class="input-fields">
      <h1 class="headingText">Create Dinner</h1>
      <p class="dinner">Dinner Name:</p>
      <b-field>
        <b-input v-model="dinnerInvite.inviteName" type="text" />
      </b-field>
      <p class="date">Dinner Reservation:</p>
      <input type="date" v-model="reservationDate" />
      <input type="time" v-model="reservationTime" />
      <p class="deadline">Deadline to Vote:</p>
      <input type="date" v-model="deadlineDate" />
      <input type="time" v-model="deadlineTime" />

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
              v-model="invitee.name"
              placeholder="Enter friend's name:"
            />
            <b-input
              id="email"
              type="email"
              v-model="invitee.email"
              placeholder="Enter friend's email:"
            />
            <button @click="remove(i)" class="delete" id="delete-invitee" />
          </b-field>
          <div>
            <p class="add-invitees">Add Participants</p>
            <b-button @click="add()" id="add-invitee"
              ><i class="fas fa-plus"></i
            ></b-button>
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
    };
  },

  methods: {
    createInvite() {
      this.dinnerInvite.reservationDate =
        this.reservationDate + " " + this.reservationTime;
      this.dinnerInvite.deadline = this.deadlineDate + " " + this.deadlineTime;
      this.dinnerInvite.creatorId = this.$store.state.user.id;
      this.$store.commit("CREATE_INVITATION", this.dinnerInvite);
    },
    addInviteesToDinner() {
      InviteService.sendInvite(this.dinnerInvite).then((response) => {
        this.dinnerInvite = response.data;
      });
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