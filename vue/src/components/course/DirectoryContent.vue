<template>
<div id="cmenutitle">  
  <h1>Course Directory</h1>
    <div class="elmnt">
      <router-link :to="{ name: 'Course', params: { id: course.course_id } }"
        id="course" v-for="course in this.$store.state.courses" v-bind:key="course.id" tag="div">
      <div id="elmnt0">ID:</div><div id="elmnt1">{{ course.course_id }}</div>
      <div id="elmnt2">Course Name:</div><div id="elmntname">{{ course.course_name }}</div>
      <div id="elmnt3">Course Difficulty:</div><div id="elmnt4">{{ course.level }}</div></router-link>
  </div>
</div>
</template>
<script>
import courseService from '@/services/CourseService';
export default {

  data() {
    return {
      showAddCourse: false,
      newCourse: {
      course_name: '',
      },
      errorMsg: '',
    };
  },
  created() {
    this.retrieveCourses();
  },
  methods: {
    retrieveCourses() {
      courseService.getCourses().then(response => {
        this.$store.commit("SET_COURSES", response.data);
      });
    },
  },
  computed: {
    }
};
</script>

<style scoped>
#cmenutitle {
  display: flex;
  font-family: Arial, Helvetica, sans-serif;
  background-color: lightgray;
  flex-direction: column;
  flex-basis: 100%;
  widows: 100%;
  text-align: center;
}
#course {
  display: flex;
  font-family: Arial, Helvetica, sans-serif;
  flex-basis: 100;
  width: 100%;
  justify-content: flex-start;
  background-color: white;
  padding-top: 24px;
  padding-bottom: 24px;
  text-align: left;
  border-bottom: 2px solid lightgray;
}
#elmnt0 {
  padding-left: 10px;
  width: 30px;
}
#elmnt1 {
  padding-left: 10px;
  width: 40px;
}
#elmnt2 {
  padding-left: 10px;
  width: 120px;
}
#elmnt3 {
  padding-left: 40px;
  width: 140px;
  word-break: break-word;
}
#elmnt4 {
  width: 140px;
  word-break: break-word;
}
#elmntname {
  padding-left: 20px;
  font-weight: bold;
  width: 230px;
  word-break: break-word;
}
</style>