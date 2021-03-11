import axios from 'axios';

export default {
    getInvites() {
        return axios.get("/invitation")
    },
    getInvitee(userID) {
        return axios.get(`/invitation/${userID}`);
    }
}