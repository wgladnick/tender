import axios from 'axios';

export default {
    getInvites() {
        return axios.get("/invitation")
    },
    getInvitee(uniqueId) {
        return axios.get(`/invitation/invitee/${uniqueId}`);
    },
    sendInvite(invitation){
        return axios.post(`/invitation`, invitation)
    },
    getInviteById() {
        return axios.get(`/invitation/user/${id}`);
    }
    

}