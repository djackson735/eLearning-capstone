import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {
      id: '',
      name: ''
    },
    courses: [],
    enrolledcourses: [],
    course: {
      course_level: '',
      teacher_id: ''
    },
    courseCurriculas: [],
    curricula: {
      name: '',
      description: '',
      summary: '',
    }
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_COURSES(state, data) {
      state.courses = data;
    },
    SET_ENROLLED_COURSES(state, data) {
      state.enrolledcourses = data;
    },
    SET_CURRENT_COURSE(state, data) {
      state.course = data;
    },
    SET_COURSE_CURRICULAS(state, data) {
      state.courseCurriculas = data;
    },
    SET_CURRENT_CURRICULA(state, data) {
      state.curricula = data;
    },
    DELETE_COURSE(state, courseIdToDelete) {
      state.courses = state.courses.filter((course) => {
        return course.id !== courseIdToDelete;
      });
    }
  },
  actions: {
  },
  modules: {
  }
})