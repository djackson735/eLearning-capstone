<template>
  <div id="UsersListTable">
      <h1>User List</h1>
        <user-element v-for="user in users" v-bind:key="user.id" v-bind:user = "user"/>
  </div>
</template>

<script>
import userService from '@/services/UserService.js';
import UserElement from "@/components/user/UserElement";
export default {
  name: 'User',
  components: {
    UserElement
  },
  data() {
    return{
      users:[]
    }
  },
  created() {
    userService.getUsers().then(
        (response) => {
          this.users = response.data;
    })
  }
}
</script>
<style scoped>
#UsersListTable {
  font-family: Arial, Helvetica, sans-serif;
  display: flex;
  background-color: lightgray;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  gap: 2px;
  flex-basis: 100%;
}
</style>