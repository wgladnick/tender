<template>
  <div class="card">


 <h1> Yay! Your invitation is all set. Here is your confirmation: </h1><br>
 <h2> {{invitation.inviteName}} </h2> <br>
     <h3> Reservation Date: </h3> <p> {{ invitation.reservationDate | moment("dddd, MMMM Do YYYY h:mm a") }} </p><br>
     
     <h3> Voting Deadline </h3> <p> {{ invitation.deadline | moment("dddd, MMMM Do YYYY h:mm a") }} </p><br><br>
     <h3> Friends List </h3>
     <p>  Send each friend on your list their unique voting link to submit their restaurant vote</p><br>
     <ul class="card">
     <li v-for="invitee in invitation.invitees" v-bind:key="invitee.uniqueId">
         <p><strong>Name:</strong> {{invitee.name}}</p>
         <p><strong>Voting Link:</strong> www.tenderinvite.com/invite/<span class="strong">{{invitee.uniqueId}}</span></p>
         <br>
         
         </li>
        
     </ul><br><br>
       <h2> Restaurant Choices </h2>
    <ul class="rest">
      
     <li v-for="restaurant in invitation.businessDetails" v-bind:key="restaurant.yelpId">
        
         <p><strong> {{restaurant.name}} </strong></p><br>
         <img :src="restaurant.image_url"/>
         
         </li>
        
     </ul>


    






      </div>
</template>

<script>

export default {

    data(){
        return{
        invitation: {}
      
        

        };
    },


created(){
this.invitation = this.$store.state.createdInvite;
console.log(this.invitation);



},

 
    
methods: {
   
}

}
</script>

<style scoped>

div {
    display:flex;
    flex-direction: column;
    justify-content:center;
    padding:100px;
}


</style>