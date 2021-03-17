<template>
  <div>

 <p> {{invitation}} </p>
 <ul>
     <li v-for="invitee in invitation.invitees" v-bind:key="invitee.uniqueId">
         <p>{{invitee.name}}</p>
         <p>{{invitee.uniqueId}}</p>
         </li>
     </ul>



      </div>
</template>

<script>
export default {

    data(){
        return{
        invitation: {},
        

        };
    },


created(){
this.invitation = this.$store.state.createdInvite;
console.log(this.invitation);



}

}
</script>

<style>

</style>