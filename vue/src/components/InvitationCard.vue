<template>
  <form>
      <h1>Invite your Friends to Dinner</h1>
    <div>
        <label for="friends">Invite a friend</label>
        <input
          id="friends"
          type="text"
          placeholder="Enter email"
          />
    </div>
    
  </form>
</template>

<script>
export default {
  name: "invitation-card",

  data() {
      return {
          invites: []
      };
  }



};
</script>

<style>
</style>