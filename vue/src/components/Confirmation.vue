<template>
  <div>
      <p>{{invitation}}</p>
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
    }

}
</script>

<style>

</style>