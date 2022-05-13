


<template>
  <form v-on:submit.prevent="submitForm" class="courseForm">
    <div class="status-message error" v-show="errorMsg !== ''">{{errorMsg}}</div>
    <div class="form-group">
      <label for="title">Title:</label>
      <input id="title" type="text" class="form-control" v-model="course.title" autocomplete="off" />
    </div>
    <div class="form-group">
        Course Level:
        <select id="tag" class="level" v-model="course.level">
        <option value="Beginner">Beginner</option>
        <option value="Advanced">Advanced</option>
        <option value="Hero">Hero</option>id+
        <option value="Lore Master">Lore Master</option>
      </select>
      </div>
    <button class="btn btn-submit" v-on:click.prevent="submitForm" type="submit">Submit</button>
    <button class="btn btn-cancel" v-on:click.prevent="cancelForm" type="cancel">Cancel</button>
  </form>
</template>

<script>
import courseService from "@/services/CourseService";
export default {
  name: "course-form",
  props: {
    courseID: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      course: {
        title: "",
        teacher: 0,
        course_level: "",
      },
      errorMsg: ""
    };
  },
  methods: {
    submitForm() {
      const updCourse = {
        id: this.courseId,
        title: this.course.title,
        teacher: this.course.teacher,
        level: this.course.level,
      };
        courseService.updCourse(updCourse).then(response => {
            if (response.status === 200) {
              this.$router.push(`/course/${updCourse.courseID}`);
            }
          })
          .catch(error => {
            this.handleErrorResponse(error, "adding");
          });
      },
    cancelForm() {
      this.$router.push(`/courses/${this.$route.params.courseID}`);
    },
  },
  created() {
    if (this.courseId != 0) {
      courseService.getCourseCurriculas( this.$route.params.courseID).then(response => {
          this.course = response.data;
        })
        .catch(error => {
          if (error.response && error.response.status === 404) {
            alert("Course not available.");
            this.$router.push("/");
          }
        });
    }
  }
};
</script>

<style scoped>
.courseForm {
  padding: 20px;
  margin-bottom: 10px;
  font-family: Arial, Helvetica, sans-serif;
}
.form-group {
  margin-bottom: 10px;
  font-size: 18px;
  margin-top: 10px;
  width: 250px;
}
label {
  display: inline-block;
  margin-bottom: 0.5rem;
}
.form-control {
  display: block;
  font-size: 18px;
  width: 100%;
  height: 30px;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  border: 1px solid #ced4da;
}
textarea.form-control {
  height: 75px;
  font-size: 18px;
  font-family: Arial, Helvetica, sans-serif;
}
select.form-control {
  width: 20%;
  font-size: 18px;
  display: inline-block;
  margin: 10px 20px 10px 10px;
}
.btn-submit {
  color: #e6fffa;
  font-size: 18px;
  background-color: #0062cc;
  border-color: #005cbf;
}
.btn-cancel {
  color: #e6fffa;
  font-size: 18px;
  background-color: #dc3545;
  border-color: #dc3545;
}
.status-message {
  display: block;
  border-radius: 5px;
  font-weight: bold;
  font-size: 1rem;
  text-align: center;
  padding: 10px;
  margin-bottom: 10px;
}
.status-message.success {
  background-color: #90ee90;
}
.status-message.error {
  background-color: #f08080;
}
</style>