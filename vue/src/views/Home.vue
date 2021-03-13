<template>

<dinner-list />

</template>

<script>
import DinnerList from '../components/DinnerList.vue';
import Profile from '../components/Profile.vue';


export default {
  components: { DinnerList, Profile},
  name: "home"
};
</script>
