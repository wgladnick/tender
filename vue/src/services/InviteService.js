import axios from 'axios';


const INVITE_PATH = "/invite"

export default {
    getInvites() {
        return axios.get(INVITE_PATH)
    },
    getInvitee(userID) {
        return axios.get(`INVITE_PATH/${userID}`);
    }
}