<template>
  <form v-on:submit.prevent="submitForm" class="curriculaForm">
    <div class="status-message error" v-show="errorMsg !== ''">{{errorMsg}}</div>
    <div class="form-group">
      <label for="title">Curricula Name:</label>
      <input id="title" type="text" class="form-control" v-model="curricula.name" autocomplete="off" />
    </div>
    <div class="form-group">
      <label for="summary">Lesson Summary:</label>
      <input id="summary" type="text" class="form-control" v-model="curricula.summary" autocomplete="off" />
    </div>
    <div class="form-group">
      <label for="description">Full Lesson Content:</label>
      <!--Quill Editor-->
      <div class="description-editor">
        <quill-editor
            v-model="content"
            ref="myQuillEditor"
            :options="editorOption" />
      </div>

    </div>
    <button class="btn btn-submit" type="submit">Submit</button>
    <button class="btn btn-cancel" v-on:click.prevent="cancelForm" type="cancel">Cancel</button>
  </form>
</template>

<script>
import coursesService from "@/services/CourseService";
import 'quill/dist/quill.snow.css'
import { quillEditor } from 'vue-quill-editor'
export default {
  name: "curricula-form",
  components: {
    quillEditor
  },
  props: {
    curriculaID: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      content: '',
      editorOption: {
        theme: 'snow'
      },
      delta: undefined,
      courseId: Number(this.$route.params.courseID),
      curricula: {
        name: "",
        summary: "",
        description: ""
      },
      errorMsg: ""
    };
  },
  methods: {
    submitForm() {
      const updatedCurricula = {
        name: this.curricula.name,
        description: this.content,
        summary: this.curricula.summary
      };
      if (this.curriculaID === 0) {
        coursesService.addCurricula(this.courseId, updatedCurricula).then(response => {
          if (response.status === 201) {
            this.$router.push(`/courses/${this.courseId}/curricula/`);
          }
        })
            .catch(error => {
              this.handleErrorResponse(error, "adding");
            });
      } else {
        updatedCurricula.id = this.curriculaID;
        updatedCurricula.avatar = this.curricula.avatar;
        coursesService.updateCurricula(this.courseId, this.curriculaID, updatedCurricula).then(response => {
          if (response.status === 200) {
            this.$router.push(`/courses/${updatedCurricula.courseId}`);
          }
        })
            .catch(error => {
              this.handleErrorResponse(error, "updating");
            });
      }
    },
    cancelForm() {
      this.$router.push(`/curricula/${this.curriculaID}`);
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
            "Error " + verb + " curricula. Response received was '" +
            error.response.statusText +
            "'.";
      } else if (error.request) {
        this.errorMsg =
            "Error " + verb + " curricula. Server could not be reached.";
      } else {
        this.errorMsg =
            "Error " + verb + " curricula. Request could not be created.";
      }
    }
  },
  watch: {
    content () {
      this.delta = this.$refs.myQuillEditor.quill.getContents();
    }
  },
  created() {
    // if (this.curriculaID !== 0) {
    console.log('Here')
      coursesService.getCurricula(this.$route.params.courseID, this.$route.params.curriculaID).then(response => {
        this.curricula = response.data;
      })
          .catch(error => {
            if (error.response && error.response.status === 404) {
              alert("Curricula not available. This curricula may have been deleted or you have entered an invalid curricula ID.");
              this.$router.push("/");
            }
          });
    }
  // }
};
</script>

<style>
.curriculaForm {
  padding: 10px;
  margin-bottom: 10px;
}
.form-group {
  margin-bottom: 10px;
  margin-top: 10px;
}
label {
  display: inline-block;
  margin-bottom: 0.5rem;
}
.form-control {
  display: block;
  width: 80%;
  height: 30px;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  border: 1px solid #ced4da;
}
textarea.form-control {
  height: 75px;
  font-family: Arial, Helvetica, sans-serif;
}
select.form-control {
  width: 20%;
  display: inline-block;
  margin: 10px 20px 10px 10px;
}
.btn-submit {
  color: #e6fffa;
  background-color: #0062cc;
  border-color: #005cbf;
}
.btn-cancel {
  color: #e6fffa;
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
