<template>
  <div id="home">
        <div id="menu">
          <div id="login" class="text-center">
            <br>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
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
      />
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      /><br>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
      <p></p>
      <router-link :to="{ name: 'login' }">Login Instead</router-link>
    </form>
  </div>
    </div>
        </div>
        <div id="content">
        </div>
        </div>
</template>

<script>
import authService from '../../services/AuthService';
export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'student',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
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
/*    background-color: #42c2f5;*/
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
/*#content {*/
/*  float: right;*/
/*  flex-basis: 100%;*/
/*  background-color: lightgray;*/
/*}*/
</style>