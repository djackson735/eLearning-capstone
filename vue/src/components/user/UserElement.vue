<template>
  <div id="elmnt">
      <div id="elmnt1">ID:</div><div id="elmnt1">{{  user.id }}</div>
      <div id="elmnt2">User Name:</div><div id="elmntname">
      <router-link v-bind:to="{name: 'AdminDetail', params :{id: user.id}}"> {{ user.username }}</router-link></div>
      <div id="elmnt3">Access Level:</div><div id="elmnt4">{{ getUserRole }}</div>
      <div id="elmnt5"><button class="promoteButton button-close" v-on:click="promoteToTeacher()"
       :disabled=isDisabled :hidden=!isHidden>Make Teacher</button></div>
      <div id="elmnt5"><button class="deleteButton button-close" :hidden=!isHidden v-on:click="deleteUser()">Delete User</button></div>
  </div>
</template>

<script>
import userService from '@/services/UserService';
import authService from "@/services/AuthService";

export default {
  name: 'UserElement',
  props: ['user'],
  data() {
    return {
      userrole: '',
    }
  },
  computed: {
    getUserRole() {
      return authService.findUserRole(this.user)
    },
    isHidden() {
      return this.user.id > 1;
    },
    isDisabled() {
        return authService.findUserRole(this.user) == 'TEACHER';
      }
  },
  methods: {
    promoteToTeacher() {
      userService.promoteToTeacher(this.user.id);
      this.$router.go(0);
    },
    deleteUser() {
      if (
          confirm("Are you sure you want to delete this user? " +
              "This action cannot be undone.")
      )
      userService.deleteUser(this.user.id);
      this.$router.go(0);
    }
  }
}
</script>
<style scoped>
#elmnt {
  font-family: Arial, Helvetica, sans-serif;
  display: flex;
  flex-basis: 100%;
  width: 100%;
  background-color: white;
  padding-top: 24px;
  padding-bottom: 24px;
}
#elmnt1 {
  padding-left: 10px;
  width: 40;
}
#elmnt2 {
  padding-left: 40px;
  width: 90px;
}
#elmnt3 {
  padding-left: 40px;
  width: 120px;
  word-break: break-word;
}
#elmnt4 {
  width: 120px;
  word-break: break-word;
}
#elmnt5 {
  padding-left: 20px;
  width: 120px;
  word-break: break-word;
}
#elmntname {
  padding-left: 20px;
  font-weight: bold;
  width: 120px;
  word-break: break-word;
}
</style>