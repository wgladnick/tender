<template>
  <div>
      </div>
</template>

<script>

export default {
    name: "user-profile",
    created() {
        this.getUserId(this.$route.params.username)
    }


}
</script>

<style>

</style>