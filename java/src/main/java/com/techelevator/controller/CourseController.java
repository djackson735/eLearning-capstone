package com.techelevator.controller;
import com.techelevator.dao.courses.CourseDao;
import com.techelevator.dao.users.UserDao;
import com.techelevator.model.Enrollment;
import com.techelevator.model.courses.Course;
import com.techelevator.model.courses.Curricula;
import com.techelevator.model.courses.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RestController
public class CourseController {
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private UserDao userDao;

    public CourseController(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @RequestMapping(path = "/courses", method = RequestMethod.GET)
    public List<Course> getAllCourses() {
        return courseDao.findAll();
    }

    @RequestMapping(path = "/courses/{id}", method = RequestMethod.GET)
    public Course getCourseByCourseId(@PathVariable("id") Long courseId) {
        Course result = courseDao.getCourseById(courseId);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No board with that id.");
        } else {
            return result;
        }
    }

    @RequestMapping(path = "/curricula/{curriculaId}", method = RequestMethod.GET)
    public Curricula getCurriculaById(@PathVariable Long curriculaId) {
        Curricula result = courseDao.getCurriculaById(curriculaId);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No curriculum with that id.");
        } else {
            return result;
        }
    }

    @RequestMapping(path = "/courses/{id}/curriculas", method = RequestMethod.GET)
    public List<Curricula> listAllCurriculas(@PathVariable long id, Curricula curricula) {
        curricula.setCourseId(id);
        return courseDao.listAllCurriculas(curricula.getCourseId());

    }
    //TODO Review the path here
    @RequestMapping(path = "/courses/student", method = RequestMethod.GET)
    public List<Course> getEnrolledCourses(Principal principal) {
        long studentId = userDao.findIdByUsername(principal.getName());
        return courseDao.findAllEnrolled(studentId);
    }

    @RequestMapping(path = "/courses/teacher", method = RequestMethod.GET)
    public List<Course> getAllMyCourses(Principal principal) {
        long teacherId = userDao.findIdByUsername(principal.getName());
        return courseDao.findAllMyCourses(teacherId);
    }

    @RequestMapping(path = "/courses", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(@RequestBody Course course, Principal principal) {
        course.setTeacher_id((long)userDao.findIdByUsername(principal.getName()));
        return courseDao.createCourse(course);
    }

    @RequestMapping(path = "/courses/{id}/curricula", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Curricula createCurricula(@PathVariable long id, @RequestBody Curricula curricula) {
        curricula.setCourseId(id);
        return courseDao.createCurricula(curricula);
    }

    @RequestMapping(path = "/courses/{id}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Enrollment createEnrollment(@PathVariable long id, @RequestBody Enrollment enrollment, Principal principal) {
        long studentId = userDao.findIdByUsername(principal.getName());
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(id);
        return courseDao.createEnrollment(enrollment);
    }

    @RequestMapping(path = "/enrollment/courses/{id}", method = RequestMethod.GET)
    public boolean isEnrolled(@PathVariable long id, Principal principal) {
        long studentId = userDao.findIdByUsername(principal.getName());
        return courseDao.isEnrolled(id, studentId);
    }

    @RequestMapping(path = "/courses/{id}", method = RequestMethod.PUT)
    public Course updateCourse(@PathVariable long id, @RequestBody Course updatedCourse) {
        updatedCourse.setCourse_id(id);
        if (courseDao.updateCourse(updatedCourse)) {
            return updatedCourse;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The course to update was not found.");
        }
    }

    @RequestMapping(path = "/courses/{courseId}/curricula/{curriculaId}/edit", method = RequestMethod.PUT)
    public Curricula updateCurricula(@PathVariable Long courseId, @PathVariable Long curriculaId,
                                     @RequestBody Curricula updatedCurricula) {
        updatedCurricula.setId(curriculaId);
        updatedCurricula.setCourseId(courseId);
        if (courseDao.updateCurricula(updatedCurricula)) {
            return updatedCurricula;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The curriculum to update was not found.");
        }
    }

    @DeleteMapping("/course/{id}")
    public void deleteCourse(@PathVariable long id) {
        if (!courseDao.deleteCourse(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No course with that id was found to delete.");
        }
    }

    @DeleteMapping("/courses/{id}/curricula/{id}")
    public void deleteCurricula(@PathVariable long id) {
        if (!courseDao.deleteCurricula(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No curriculum with that id was found to delete.");
        }
    }

    @RequestMapping(path = "/courses/{id}/curricula/{id}/homework", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Homework createHomework(Homework homework) {
        return courseDao.createHomework(homework);
    }
}