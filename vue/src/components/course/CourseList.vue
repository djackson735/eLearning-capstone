<template>
  <div id="sideNav">
    <div class="courses">
          <div class="hidden"></div>
          <router-link :to="{ name: 'Directory'}" class="board" tag="div">
         View Course Directory </router-link>
          <div class="hidden"></div>
          <router-link :to="{ name: 'Enrollments'}" class="board" tag="div">
         View Enrolled Courses </router-link>
          <div class="hidden"></div>
          <button class="board" v-if="isUserTeacher" v-on:click="showAddCourse = !showAddCourse">Add A New Course</button>
      <form v-if="showAddCourse" v-on:submit="saveNewCourse">
        <div class="sh">
        Course Title:
        <input type="text" class="form-control" v-model="newCourse.course_name" />
        Course Level:
        <select id="tag" class="level" v-model="newCourse.level" >
        <option value="Beginner">Beginner</option>
        <option value="Advanced">Advanced</option>
        <option value="Hero">Hero</option>id+
        <option value="Lore Master">Lore Master</option>
      </select>
        <button class="btn btn-submit" type="submit">Save</button>
        <button class="btn btn-cancel" type="cancel" v-on:click="showAddCourse = !showAddCourse">Cancel</button>
        </div></form>
      <router-link v-if="isUserAdmin" :to="{ name: 'admin'}" class="board" tag="div">
        View Users List
      </router-link>
    </div>
  </div>
</template>

<script>
import coursesService from '@/services/CourseService';
import userService from "@/services/UserService";
import authService from "@/services/AuthService";

export default {

  name: 'CourseList',
  props: ['user'],
  computed: {
    isUserTeacher() {
      return !!(authService.findUserRole(this.$store.state.user).includes('TEACHER'));
    },
    isUserAdmin() {
      return !!(authService.findUserRole(this.$store.state.user).includes('ADMIN'));
    },
  },
  data() {
    return {
      pageload: false,
      enrolled: true,
      showCurriculum: false,
      coursename: "",
      courselevel: "",
      courseId: 0,
      isTeacher: false,
      showAddCourse: false,
      // user: [],
      newCourse: {
      course_name: '',
      },
      errorMsg: '',
    }
  },
  methods: {
    retrieveCourses() {
      coursesService.getCourses().then(response => {
        this.$store.commit("SET_COURSES", response.data);
        if (this.$route.name === "Home" && response.status === 200 && response.data.length > 0) {
          this.$router.path(`/courses/${response.data[0].id}`);
        }
      });
    },
    checkRole() {
      userService.checkTeacher(this.$store.state.user).then(response => {
      this.isTeacher = response.data;
      })
    },
    saveNewCourse() {
      coursesService.addCourse(this.newCourse).then( () => {
        this.retrieveCourses();
        this.newCourse = {
          course_id: '',
          level: ''
        };
        this.showAddCourse = true;
      })
    },
  created() {
    this.checkRole();
    this.courseId = this.$route.params.id;
    this.retrieveCurriculas();
    this.checkEnrolled();
    this.pageload = true;
   },
}
}
</script>

<style scoped>
.boards {
  margin-bottom: 800px;
}
.sh {
  font-size: 16pt;
  color: white;
  display: float;
  width: 100%;
  background-color: #42c2f5;
  align-content: center;
  font-family: Arial, Helvetica, sans-serif;
}
.board {
  color: white;
  padding-top: 10px;
  padding-bottom: 10px;
  background-color: #42c2f5;
  font-size: 20px;
  font-family: Arial, Helvetica, sans-serif;
  cursor: pointer;
  text-align: left;
  padding-left: 8px;
  border: 2px solid transparent;
  border-bottom: 2px solid transparent;
  width: 100%;
  cursor: pointer;
}
.hidden{  
  background-color: white;
  height: 10px;
}
.form-control {
  padding: 8px;
  width: 93%;
  display: pointer;
  font-family: Arial, Helvetica, sans-serif;
}
.btn {
  display: inline-block;
  color: #e6fffa;
  text-align: center;
  vertical-align: middle;
  border: 1px solid transparent;
  width: 100%;
}
.board:hover {
  color: purple;
}
</style>