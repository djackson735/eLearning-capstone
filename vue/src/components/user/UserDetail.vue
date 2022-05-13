<template>
  <div>
    <router-link v-if="isAdmin" :to="{ name: 'admin'}" class="board" tag="div">
      View Users List </router-link>
  </div>
</template>

<script>
import userService from "@/services/UserService";
export default {
  data() {
    return {
      name: 'user-list',
      user: ['user'],
    };
  },
//TODO: Ensure this still works after logging out and logging back in.
  methods: {
    getUserRole() {
      let roleStr = '';

      for (let i = 0; i < this.user.authorities.length; i++) {
        roleStr += Object.values(this.user.authorities[i]);
        roleStr = roleStr.replace("ROLE_", "");
      }
      return roleStr;
    },
    isAdmin() {
      return this.getUserRole().includes('ADMIN');
    },

  created() {
    userService.getUserById(this.$store.state.user.id).then(
        (response) => {
          this.user = response.data;
        }
      )
   }
  }
}
</script>

<style scoped>
.board {
  color: #e6fffa;
  border-radius: 3px;
  background-color: #42c2f5;
  padding: 10px;
  margin: 10px;
  text-align: center;
  border: 1px solid transparent;
  width: 90%;
}
.board:hover {
  border: solid white 1px;
}
</style>