 <template>
  <b-form>
    <div class="input-fields">
      <h1 class="headingText">Create Dinner</h1>
      <b-field label="Dinner Name">
        <b-input v-model="dinnerInvite.inviteName" type="text" />
      </b-field>
      <b-field id="reservationDate" label="Select Date and Time">
        <b-datetimepicker
          v-model="dinnerInvite.reservationDate"
          placeholder="Click to select..."
        >
          <template #left>
            <b-button
              label="Now"
              type="is-primary"
              icon-left="clock"
              @click="dinnerInvite.reservationDate = new Date()"
            />
          </template>

          <template #right>
            <b-button
              label="Clear"
              type="is-danger"
              icon-left="close"
              outlined
              @click="dinnerInvite.reservationDate = null"
            />
          </template>
        </b-datetimepicker>
      </b-field>
      <b-field id="deadline" label="Deadline for Dinner Invite">
        <b-datetimepicker
          v-model="dinnerInvite.deadline"
          placeholder="Click to select..."
        >
          <template #left>
            <b-button
              label="Now"
              type="is-primary"
              icon-left="clock"
              @click="dinnerInvite.deadline = new Date()"
            />
          </template>

          <template #right>
            <b-button
              label="Clear"
              type="is-danger"
              icon-left="close"
              outlined
              @click="dinnerInvite.deadline = null"
            />
          </template>
        </b-datetimepicker>
      </b-field>
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
              v-model="invitee.value2"
              placeholder="Enter friend's name:"
            />
            <b-input
              id="email"
              type="email"
              :label="invitee.label1"
              v-model="invitee.value1"
              placeholder="Enter friend's email:"
            />
              <button @click="remove(i)" class="delete" id="delete-invitee" />
          </b-field>
          <div>
            <b-button @click="add()" id="add-invitee"> Add Invitee</b-button>
          </div>
          <b-button type="submit">
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
      dinnerInvite: {
        inviteName: "",
        reservationDate: new Date(),
        deadline: new Date(),
        invitees: [],
        location: "",
        radius: "",
        restaurantChoices: [],
      },
    };
  },

  methods: {
    addInviteesToDinner() {
      InviteService.sendInvite(this.dinnerInvite).then((response) => {
        this.dinnerInvite = response.data;
      });
    },
    add() {
      this.dinnerInvite.invitees.push({
        label1: "email",
        value1: "",
        label2: "name",
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
.input-fields {
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
}
</style>