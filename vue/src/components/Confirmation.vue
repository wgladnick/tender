<template>
  <div>


 <br>
 <br>
 <br>
 <br>
 <h1> Yay! Your invitation has been set. Here is your confirmation: </h1><br>
 <h2> {{invitation.inviteName}} </h2> <br>
     <h3> Reservation Date: </h3> <p> {{ invitation.reservationDate | moment("dddd, MMMM Do YYYY h:mm a") }} </p><br>
     
     <h3> Voting Deadline </h3> <p> {{ invitation.deadline | moment("dddd, MMMM Do YYYY h:mm a") }} </p><br><br>
     <h3> Friends List </h3> 
     <ul>
     <li v-for="invitee in invitation.invitees" v-bind:key="invitee.uniqueId">
         <p><strong>Name:</strong> {{invitee.name}}</p>
         <p><strong>Voting Link:</strong> www.tenderinvite.com/invite/ <span class="strong"> {{invitee.uniqueId}}</span></p>
         <br>
         
         </li>
        
     </ul>
    <ul>
     <li v-for="restaurant in restaurants" v-bind:key="restaurant.yelpId">
        <h2> Restaurant Choices </h2>
         <p><strong> {{restaurant.name}} </strong></p>
         <img :src="restaurant.image_url"/>
         
         </li>
        
     </ul>


    






      </div>
</template>

<script>

export default {

    data(){
        return{
        invitation: {},
        restaurants:[]
        

        };
    },


created(){
this.invitation = this.$store.state.createdInvite;
console.log(this.invitation);

this.invitation.businessDetails = this.restaurants;

},

 
    
methods: {
   
}

}
</script>

<style scoped>

h1{
    font-size: 2.7vw;
    text-align:center;
}

h2{
    font-size: 2.5vw;
    font-weight:600;
    text-align:center;
}

h3{
    font-size: 2vw;;
    font-weight:bold;
    text-align:center;
}

p{
    font-size:1.5vw;
    text-align:center;
}

ul p {
    text-align:left;
    justify-content: center;
    margin-left:20em;
}

</style>