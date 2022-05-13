<template>
  <div id="home">
        <div id="menu">
          <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      /><br>
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      /><br>
      <button type="submit">Sign in</button>
      <p></p>
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
    </form>
  </div>
        </div>
        <div id="content">
        </div>
        </div>
</template>

<script>
import authService from "../../services/AuthService";
export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService.login(this.user).then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;
          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
}
}
</script>
<style scoped>
#login {
  display: flex;
  justify-content: center;
  margin-top: 30px;
  padding-bottom: 10px;
  text-align: center;
  background-color: #42c2f5;
  font-family: Arial, Helvetica, sans-serif;
  color: white;
  width: 100%;
  display: pointer;
}
/*#home {*/
/*  display: flex;*/
/*  flex-basis: 100%;*/
/*  background-color: #42c2f5;*/
/*}*/
.form-control {
  width: 88%;
  display: pointer;
  font-family: Arial, Helvetica, sans-serif;
}
/*#menu {*/
/*  display: block;*/
/*  justify-content: space-evenly;*/
/*  flex-basis: 300px;*/
/*  border-top: transparent;*/
/*  text-align: center;*/
/*}*/
#content {
  float: right;
  flex-basis: 100%;
  background-color: lightgray;
}
</style>