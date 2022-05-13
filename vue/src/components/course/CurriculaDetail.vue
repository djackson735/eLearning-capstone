<template>
  <div>
    <div class="header">
      <div class="hbannerleft"><div class="hbannerleftcontent"><article>
      Curricula Name: <div class="articlecontent"><b>#{{ curricula.id }} {{ curricula.name }}</b></div></article>
      <article class="auto">Summary: <div class="articlecontent"><b>{{ curricula.summary }}</b></div></article></div></div>
        <div class="hbannerright">
        <router-link tag="button" v-if="isTeacher === true&pageload === true" :to="{ name: 'EditCurricula', params: {curriculaID: $route.params.curriculaID} }"
        class="btn-edit">
        Edit Curriculum</router-link>
        <button class="btn-cancel" v-on:click="deleteCurricula" v-if="isTeacher === true&pageload === true">Delete Curriculum</button>
        </div>
      </div>
      <div id="curriculaContainer">
      <div id="curriculaDescription">
      <p v-html="curricula.description"></p>
        </div></div>
    <div class="course-actions">
      <router-link class="return" :to="{ name: 'Course', params: { id: this.curricula.courseId } }">Back to Curricula List</router-link>
    </div>
     <p></p><p></p>
  </div>
</template>

<script>
import coursesService from "@/services/CourseService";
import userService from "@/services/UserService";


export default {
  name: "curricula-detail",

  data() {
    return {
      errorMsg: "",
      isTeacher: "",
      pageload: false,
    };
  },
  methods: {
    checkRole() {
      userService.checkTeacher(this.$store.state.user.id).then(response => {
        this.isTeacher = response.data;
      })
    },
    retrieveCurricula() {
      coursesService.getCurricula(this.$route.params.curriculaID).then(response => {
          this.$store.commit("SET_CURRENT_CURRICULA", response.data);
        })
        .catch(error => {
          if (error.response && error.response.status === 404) {
            alert("Curricula not available. This curricula may have been deleted or you have entered an invalid curricula ID.");
            this.$router.push("/");
          }
        });
    },
    deleteCurricula() {
      if (confirm("Are you sure you want to delete this curricula? This action cannot be undone.")
      ) {coursesService.deleteCurricula(this.curricula.id).then(response => {
            if (response.status === 200) {
              alert("Curricula successfully deleted");
              this.$router.push(`/course/${this.curricula.courseId}`);
            }
          })
          .catch(error => {
            if (error.response) {
              this.errorMsg =
                "Error deleting curricula. Response received was '" +
                error.response.statusText +
                "'.";
            } else if (error.request) {
              this.errorMsg =
                "Error deleting curricula. Server could not be reached.";
            } else {
              this.errorMsg =
                "Error deleting curricula. Request could not be created.";
            }
          });
      }
    },
  },
  created() {
    this.retrieveCurricula();
    this.checkRole();
    this.pageload = true;
  },
  computed: {
    curricula() {
      return this.$store.state.curricula;
    }
  }
};
</script>

<style scoped>
.btn.editCurricula {
  color: #fff;
  background-color: #508ca8;
  border-color: #508ca8;
  margin-bottom: 10px;
}
.btn.deleteCurricula {
  color: #fff;
  background-color: #ef031a;
  border-color: #ef031a;
  margin-bottom: 10px;
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
.header {
  display: flex;
  align-items: center;
}
#curriculaContainer {
  margin: 20px;
  padding: 20px;
  background-color: white;
}
#curriculaDescription {
  display: flex;
}

</style>
