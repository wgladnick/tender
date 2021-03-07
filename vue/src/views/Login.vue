<template>
<section id="main">


<div id ="left-panel">

  <div class="left-header">
  <h1>Mmmmm.. food</h1>
  <p>Because food is delicious</p>
  </div>

<login-form/>

</div>

<div id ="right-panel">
  <h1> Discover New Food </h1>
  <img/>
</div>

</section>

</template>

<script>

import LoginForm from '../components/LoginForm.vue';

export default {
  name: "login",
  components: {LoginForm},
  data() {
    
  },
  methods: {
   
    }
  }

</script>
<style scoped>
#main{
  display:flex;
}
#left-panel, #right-panel{
  width:50%;
}

#right-panel{
  background-color:#EB6D6A;
  height:100vh;
}

#left-panel{
  display:flex;
  flex-direction:column;
  justify-content:center;
}

.left-header{
display:flex;
 flex-direction:column;
 align-items:center;
}



</style>


