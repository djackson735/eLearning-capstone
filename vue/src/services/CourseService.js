import axios from 'axios';

export default {

  getCourses() {
    return axios.get('/courses');
  },

  getCourseCurriculas(courseID) {
    return axios.get(`/courses/${courseID}`)
  },

  getCurriculas(courseID) {
    return axios.get(`/courses/${courseID}/curriculas`)
  },

  getCurricula(curriculaID) {
    return axios.get(`/curricula/${curriculaID}`)
  },

  addCurricula(courseID, curricula) {
    return axios.post(`/courses/${courseID}/curricula`, curricula);
  },

  updateCurricula(courseID, curriculaId, curricula) {
    return axios.put(`/courses/${courseID}/curricula/${curriculaId}/edit`, curricula);
  },

  deleteCurricula(curriculaID) {
    return axios.delete(`/curricula/${curriculaID}`);
  },

  addCourse(course) {
    return axios.post('/courses', course);
  },

  updCourse(course) {
    return axios.put('/courses', course);
  },

  addEnrollment(courseID, enrollment) {
    return axios.post(`/courses/${courseID}`, enrollment);
  },

  getEnrollments() {
    return axios.get('/courses/student');
  },

  isEnrolled(courseID) {
    return axios.get(`/enrollment/courses/${courseID}`)
  },

  unenroll(courseID, enrollment) {
    return axios.delete(`/courses/${courseID}`, enrollment)
  },

  deleteCourse(courseID) {
    return axios.delete(`/course/${courseID}`);
  }
}