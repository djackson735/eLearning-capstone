package com.techelevator.dao.courses;

import com.techelevator.model.Enrollment;
import com.techelevator.model.courses.Course;
import com.techelevator.model.courses.Curricula;
import com.techelevator.model.courses.Homework;

import java.util.List;

public interface CourseDao {
    public List<Course> findAll();

    public List<Course> findAllEnrolled(Long studentId);

    public List<Course> findAllMyCourses(long teacherId);

    public Enrollment createEnrollment (Enrollment enrollment);

    public Course createCourse(Course course);

    public Course getCourseById(Long courseId);

    public boolean updateCourse(Course course);

    public boolean deleteCourse(long courseId);

    public List<Curricula> listAllCurriculas(long courseId);

    public Curricula getCurriculaById(Long curriculaId);

    //TODO Determine if this method needs courseId
    public Curricula createCurricula(Curricula curricula);

    //TODO Determine if this needs courseId
    public boolean updateCurricula(Curricula curricula);

    public boolean deleteCurricula(long curriculaId);

    List<Curricula> getCurriculasByCourseId(Long courseId);

    public boolean isEnrolled (Long courseId, Long studentId);

    public Homework createHomework(Homework homework);

}
