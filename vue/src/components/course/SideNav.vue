<template>
  <div>
    <b-sidebar id="sidebar" title="Sidebar" bg-variant="light" text-variant="dark" shadow>
      <div class="px-3 py-2">
    <b-button block variant="primary" :to="{ name: 'Directory'}" tag="div"
              class="list-group-item border-end-0 d-inline-block text-truncate board"
              data-bs-parent="#sidebar">
      <i class="bi bi-bootstrap"></i> <span>Course Directory</span></b-button>
    <b-button block variant="secondary" :to="{ name: 'Enrollments'}" tag="div"
              class="list-group-item border-end-0 d-inline-block text-truncate board"
              data-bs-parent="#sidebar">
      <i class="bi bi-film"></i> <span>My Courses</span></b-button>
    <b-button class="board" data-bs-parent="#sidebar"
              v-if="isUserTeacher" v-on:click="showAddCourse = !showAddCourse"><i class="bi bi-heart"></i>
      <span>Add New Course</span></b-button>
    <form v-if="showAddCourse" v-on:submit="saveNewCourse">
      <div class="sh">
        Course Title:
        <input type="text" class="form-control" v-model="newCourse.course_name"/>
        Course Level:
        <select id="tag" class="level" v-model="newCourse.level">
          <option value="Beginner">Beginner</option>
          <option value="Advanced">Advanced</option>
          <option value="Hero">Hero</option>
          id+
          <option value="Lore Master">Lore Master</option>
        </select>
        <button class="btn btn-submit" type="submit">Save</button>
        <button class="btn btn-cancel" type="cancel" v-on:click="showAddCourse = !showAddCourse">Cancel
        </button>
      </div>
    </form>
    <b-button v-if="isUserAdmin" :to="{ name: 'admin'}" tag="div"
              class="list-group-item border-end-0 d-inline-block text-truncate" data-bs-parent="#sidebar"><i
        class="bi bi-heart"></i> <span>User List</span></b-button>
      </div>
    </b-sidebar>
  </div>
</template>

<script>
import coursesService from '@/services/CourseService';
import userService from "@/services/UserService";
import authService from "@/services/AuthService";

export default {
  name: 'SideNav',
  props: ['user'],
  computed: {
    isUserTeacher() {
      return !!(authService.findUserRole(this.$store.state.user).includes('TEACHER'));
    },
    isUserAdmin() {
      return !!(authService.findUserRole(this.$store.state.user).includes('ADMIN'));
    }
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
  created() {
    this.retrieveCourses();
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
      coursesService.addCourse(this.newCourse).then(() => {
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
<style>
#sidebar {
  width: 16em;
  padding-top: 100px;
}

.btn {
  /*display: inline-block;*/
  /*color: #42c2f5;*/
  /*text-align: left;*/
  /*vertical-align: middle;*/
  /*border: 1px solid transparent;*/
  width: 100%;
}

.form-control {
  padding: 8px;
  width: 93%;
  display: pointer;
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

.board:hover {
  color: purple;
}
</style>