import axios from 'axios';

export default {
  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  },

  findUserRole(user) {
    let roleStr = '';

    for (let i = 0; i < user.authorities.length; i++) {
      roleStr += Object.values(user.authorities[i]);
      roleStr = roleStr.replace("ROLE_", "");
    }
    return roleStr;
  }
}