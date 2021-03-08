import axios from 'axios';


const INVITE_PATH = "/invite"

export default {
    getInvites() {
        return http.get(INVITE_PATH)
    },
    getInvitee(userID) {
        return http.get(`INVITE_PATH/${userID}`);
    }
}