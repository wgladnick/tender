import axios from 'axios';

export default {
    getInvites() {
        return axios.get("/invitation")
    },
    getInvitee(inviteId, uniqueId) {
        return axios.get(`/invitation/invitee/${inviteId}/${uniqueId}`);
    }

}