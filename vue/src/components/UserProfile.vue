<template>
  <div>
      </div>
</template>

<script>

export default {
    name: "user-profile",
    created() {
        
    },
    data() {
        return {
            user: {
                firstName: "",
                lastName: "",
                email: "",
                address: "",
                city: "",
                state: "",
                zip: 0,
                defaultRadius: 16100,
                searchedCategories: "", 
            },
        }
    }


}
</script>

<style>

</style>