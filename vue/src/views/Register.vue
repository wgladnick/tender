<template>
<main id="register">
<register-form/>
</main>
</template>

<script>
import registerForm from '../components/RegisterForm'

export default {
  name: 'register',
  components: {
    registerForm
  },
  data() {
   
  },
  methods: {
   
}
};



</script>


<style>
#register{
  height:90vh;
  display:flex;
  justify-content:center;
  align-items:center;

}




</style>
