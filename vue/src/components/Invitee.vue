<template>
  
</template>

<script>
import InviteService from "../services/InviteService"

export default {
    
     created() {
    InviteService.getInvitee(this.$route.params.id).then(
      (response) => {
        this.restaurant = response.data;
        this.isLoading = false;

        for (let i = 0; i < this.restaurant.transactions.length; i++) {
          this.transactionTypes += this.restaurant.transactions[i].transactions + " ";
        }
      }
    );
  },


}
</script>

<style>

</style>