<template>
  <div>
    <div class="header">
      <div class="hbannerleft"><div class="hbannerleftcontent">
      <article>Course Name: <div class="articlecontent"><b>#{{ courseId }} {{ courseName }}</b></div></article>
      <article class="auto">Difficulty: <div class="articlecontent"><b>{{ courseLevel }}</b></div></article>
      <article :hidden="!pageload" class="auto">Teacher ID: {{this.courseTeacher}}<div class="articlecontent"><b> {{this.getTeacherNameById()}}{{this.teacherName}}</b></div></article></div></div>
      <div class="hbannerright">
        <button class="btn enrollbtn"
                v-on:click="enroll()" v-if="enrolled === false&pageload === true">Enroll
        </button>
        <button class="btn curriculas" v-on:click="showCurriculum=true"
                v-if="enrolled === true&showCurriculum === false&pageload === true">
          View Course Curricula
        </button>
        <button class="btn curriculas" v-on:click="showCurriculum=false"
                v-if="pageload === true&showCurriculum === true">
          Hide Course Curricula
        </button>
        <router-link tag="button" class="btn addNewCurricula" v-if="isTeacher === true&pageload === true"
                     :to="{ name: 'AddCurricula', params: {courseID: this.courseId} }">Add New Lesson
        </router-link>
        <router-link tag="button" class="btn btn-edit" v-if="isTeacher === true&pageload === true" 
      :to="{ name: 'CourseEditor', params: {courseID: this.courseId} }">Edit Course</router-link>
        <button class="btn btn-cancel" v-on:click="deleteCourse" v-if="isTeacher === true&pageload === true">
          Delete Course
        </button>
      </div>
    </div>
    <div>
      <div class="status-message error" v-show="errorMsg !== ''">{{ errorMsg }}</div>
      <div class="courses">
        <course-column title="Lessons:" :curriculas="getAll" :courseID="this.courseId" v-if="showCurriculum === true"/>
      </div>
    </div>
    <div class="course-actions">
      <div class="hidden"></div>
      <router-link class="return" to="/content">Back to Course Directory</router-link>
    </div>
  </div>
</template>

<script>
import coursesService from "@/services/CourseService";
import userService from "@/services/UserService";
import CourseColumn from "@/components/course/CourseColumn";

export default {
  name: "curriculas-list",
  components: {
    CourseColumn

  },
  data() {
    return {
      pageload: false,
      enrolled: false,
      isTeacher: false,
      showCurriculum: false,
      user: [],
      courseName: "",
      courseLevel: "",
      courseTeacher: 0,
      teacherName: "",
      teacher: { 
      },
      courseId: 0,
      errorMsg: "",
      enrollment: {
        course_id: this.courseId,
      },
    };
  },
  methods: {
    checkEnrolled() {
      coursesService.isEnrolled(this.courseId).then(response => {
        this.enrolled = response.data;
      })
    },
    checkRole() {
      userService.checkTeacher(this.$store.state.user.id).then(response => {
        this.isTeacher = response.data;
      })
    },
    enroll() {
      coursesService.addEnrollment(this.courseId, this.enrollment).then(response => {
        if (response.status === 201) {
          alert("Enrollment Successful.");
          this.enrolled = true;
        }
      })
          .catch(error => {
            if (error.response && error.response.status === 404) {
              alert("Enrollment Unsuccessful.");
            }
          });
    },
    retrieveCurriculas() {
      coursesService.getCourseCurriculas(this.courseId).then(response => {
        this.courseName = response.data.course_name;
        this.courseTeacher = response.data.teacher_id;
        this.courselevel = response.data.level;
        this.$store.commit("SET_COURSE_CURRICULAS", response.data.curriculas);
      })
          .catch(error => {
            if (error.response && error.response.status === 404) {
              alert("Course content not available. This course may have been deleted or is temporarily offline.");
              this.$router.push("/");
            }
          });
    },
    deleteCourse() {
      if (
          confirm("Are you sure you want to delete this course? This action cannot be undone.")
      ) {
        coursesService.deleteCourse(this.courseId)
            .then(response => {
              if (response.status === 200) {
                coursesService.getCourses().then(response => {
                  this.$store.commit("SET_COURSES", response.data);
                  this.$router.push("/content");
                })
              }
            })
            .catch(error => {
              this.handleErrorResponse(error);
            })
      }
    },
    handleErrorResponse(error) {
      if (error.response) {
        this.errorMsg =
            "Error deleting course. Response received was '" +
            error.response.statusText +
            "'.";
      } else if (error.request) {
        this.errorMsg =
            "Error deleting course. Server could not be reached.";
      } else {
        this.errorMsg =
            "Error deleting course. Request could not be created.";
      }
    },
      getTeacherNameById() {
      if(this.courseTeacher != 0) {
      userService.getUserById(this.courseTeacher).then(response => {
      this.teacherName = response.data.username;
      return this.teacherName;
      });}
    },
  },
  created() {
    this.courseId = this.$route.params.id;
    this.retrieveCurriculas();
    this.checkEnrolled();
    this.checkRole();
    this.pageload = true;
    this.getTeacherNameById();
  },
  computed: {
    getAll() {
      return this.$store.state.courseCurriculas.filter(
          curriculas => curriculas
      );
    },
  },
};
</script>

<style scoped>
.hidden{  
  height: 50px;
}
.courses {
  display: grid;
  gap: 2px;
}

.hbannerleft {
  border-radius: 3px;
  color: white;
  background-color: #42c2f5;
  margin-left: 20px;
  margin-top: 20px;
  font-size: 20pt;
  text-align: left;
}

.hbannerleftcontent {
  display: block;
  padding: 20px;
  font-family: Arial, Helvetica, sans-serif;
}

.articlecontent {
  display: block;
  float: right;
  padding-left: 10px;
  text-align: left;
}

.hbannerright {
  display: flex;
  flex-grow: 1;
  height: 100%;
  align-items: flex-end;
  justify-content: flex-end;
  color: white;
  margin-top: 10px;
  margin-right: 20px;
  flex-wrap: wrap;
}

.return {
  display: float;
  cursor: pointer;
  color: white;
  border-radius: 3px;
  background-color: #42c2f5;
  padding: 10px;
  padding-left: 40px;
  padding-right: 40px;
  text-align: center;
  cursor: pointer;
  border: 1px solid transparent;
  width: 180px;
}
.course-actions {
  font-size: 16pt;
  font-family: Arial, Helvetica, sans-serif;
  text-align: center;
}

.course-actions a:link,
.course-actions a:visited {
  color: white;
  text-decoration: none;
}

.course-actions a:hover {
  color: purple;
}

.btn.addNewCurricula {
  display: flex;
  color: #e6fffa;
  height: 100%;
  background-color: #508ca8;
  border-color: #508ca8;
  font-size: 20pt;
}

.btn-cancel {
  display: flex;
  color: #e6fffa;
  height: 100%;
  background-color: #dc3545;
  border-color: #dc3545;
  font-size: 20pt;
}
.btn-edit {
  display: flex;
  color: #e6fffa;
  height: 100%;
  background-color: #35dc6d;
  border-color: #35dc6d;
  font-size: 20pt;
}
.btn.curriculas {
  display: flex;
  color: #e6fffa;
  height: 100%;
  background-color: #92087b;
  border-color: #92087b;
  font-size: 20pt;
}

.btn.enrollbtn {
  display: flex;
  color: #fff;
  height: 100%;
  background-color: #0e8f52;
  border-color: #0e8f52;
  font-size: 20pt;
}

.header {
  display: flex;
  align-items: center;
}

.header h1 {
  flex-grow: 1;
}
</style>
