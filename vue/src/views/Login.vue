<template>
  <section id="main">
    <div id="left-panel">
      <div id="login-container">
      
        
      <div class="left-header">
        <h1>Dinner with friends just got yummy</h1>
        <p>mmm.. food.</p>
      </div>

      <login-form />
    </div>
    </div>

    
  </section>
</template>

<script>
import LoginForm from "../components/LoginForm.vue";

export default {
  name: "login",
  components: { LoginForm },
  data() {
    
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
  background-image: url("../assets/tinderbackground.png");
  height:90vh;
  justify-content:center;
}


#left-panel
{
  width: 50%;
 
}



.img {
  width: 50px;
}

#left-panel {
  display: flex;
  flex-direction: column;
  justify-self: center;
  background-color:white;
  height:65vh;
  align-self:center;
  padding-top:75px;
  
}


.left-header {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#right-panel {
  
  background-size: cover;
  height:100vh;
}
</style>


