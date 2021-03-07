import axios from 'axios';

const http = axios.create({
    baseURL: "http//localhost:3000"
});
const INVITE_PATH = "/invite"

export default {
    getInvites() {
        return http.get(INVITE_PATH)
    },
    getInvitee(userID) {
        return http.get(`INVITE_PATH/${userID}`);
    }
}