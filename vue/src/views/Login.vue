<template>
  <section id="main">
   
      <div id="login-container">

      <div class="headingText">
        <h1>Food with friends just got yummy</h1>
        <p>mmm.. food.</p>
      </div>

      <login-form />
    </div>
  

    
  </section>
</template>

<script>
import LoginForm from "../components/LoginForm.vue";

export default {
  name: "login",
  components: { LoginForm },
  data() {
   return{} 
  },
  methods: {},
};
</script>
<style scoped>

h1{
  font-size:25px;
  font-family: sans-serif;
  font-weight: bold;
}
#main {
  display: flex;
  background-image: url("../assets/tinderbackground.jpg");
  background-size:cover;
  height:90vh;
  justify-content:center;
}


.img {
  width: 50px;
}

#login-container {
  display: flex;
  flex-direction: column;
  justify-self: center;
  height:70vh;
  align-self:center;
  background-color:white;
  padding: 75px;
  
  
}


.headingText {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-bottom:50px;
}


</style>


