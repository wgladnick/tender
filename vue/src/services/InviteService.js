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
    getInviteById(id) {
        return axios.get(`/invitation/user/${id}`);
    },

    voteThumbsUp(inviteeVotes){
        return axios.put('/invitation/thumbsup/', inviteeVotes);
},

    voteThumbsDown(inviteeVotes){
        return axios.put('/invitation/thumbsdown/', inviteeVotes);


    },

    undoVote(inviteeVotes){
        return axios.delete('/invitation/removevote/', inviteeVotes);
    }
    

}