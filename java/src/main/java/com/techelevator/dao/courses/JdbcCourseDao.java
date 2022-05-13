package com.techelevator.dao.courses;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.Enrollment;
import com.techelevator.model.courses.Course;
import com.techelevator.model.courses.Curricula;
import com.techelevator.model.courses.Homework;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcCourseDao implements CourseDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcCourseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT course_id, course_name, teacher_id, course_level " +
                "FROM courses";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Course course = mapRowToCourse(results);
//            course.setCurriculas(getCurriculasByCourseId(course.getCourse_id()));
            courses.add(course);
        }
        return courses;
    }

    @Override
    public List<Course> findAllEnrolled(Long studentId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses JOIN enrollment ON courses.course_id=enrollment.course_id " +
                "WHERE student_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, studentId);
        while(results.next()) {
            Course course = mapRowToCourse(results);
            courses.add(course);
        }
        return courses;
    }

    @Override
    public List<Course> findAllMyCourses(long teacherId) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses WHERE teacher_id=? ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, teacherId);
        while(results.next()) {
            Course course = mapRowToCourse(results);
            courses.add(course);
        }
        return courses;
    }

    @Override
    public Course createCourse(Course course) {
        String sql = "INSERT INTO courses (course_id, course_name, teacher_id, course_level)" +
                " VALUES (default, ?, ?, ?) RETURNING course_id;";
        Long newId = jdbcTemplate.queryForObject(sql, Long.class, course.getCourse_name(), course.getTeacher_id(), course.getLevel());
        course.setCourse_id(newId);
        return course;
    }

    @Override
    public Course getCourseById(Long courseId) {
        String sql = "SELECT course_id, course_name, teacher_id, course_level " +
                "FROM courses WHERE course_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, courseId);
        if(results.next()) {
            Course course = mapRowToCourse(results);
            course.setCurriculas(getCurriculasByCourseId(course.getCourse_id()));
            return course;
        } else {
            throw new RuntimeException("No Course with id: " + courseId + " was found.");
        }
    }

    //TODO Setting count to 1 here seems odd, look into why
    @Override
    public boolean updateCourse(Course course) {
        String sql = "UPDATE courses SET " +
                "course_name = ?, " +
                "course_level = ? " +
                "WHERE course_id = ?;";
        int count = jdbcTemplate.update(sql, course.getCourse_name(), course.getLevel());

        return count == 1;
    }

    @Override
    public boolean deleteCourse(long courseId) {
        String sql = "DELETE from courses WHERE " +
                "course_id = ?;";
        int count = jdbcTemplate.update(sql, courseId);
        return count == 1;
    }

    private Course mapRowToCourse(SqlRowSet rs) {
        Course course = new Course();
        course.setCourse_id(rs.getLong("course_id"));
        course.setCourse_name(rs.getString("course_name"));
        course.setTeacher_id(rs.getLong("teacher_id"));
        course.setLevel(rs.getString("course_level"));
        return course;
    }

    // ALL CODE BELOW THIS LINE CORRESPONDS TO CURRICULA


    @Override
    public List<Curricula> listAllCurriculas(long courseId) {

        List<Curricula> curriculas = new ArrayList<>();
        String sql = "SELECT * FROM curriculas WHERE course_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,courseId);
        while(results.next()) {
            Curricula curricula = mapRowToCurricula(results);
            curriculas.add(curricula);
        }
        return curriculas;
    }

    @Override
    public Curricula getCurriculaById(Long curriculaId) {
        String sql = "SELECT * FROM curriculas WHERE curricula_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, curriculaId);
        if(results.next()) {
            return mapRowToCurricula(results);
        } else {
            throw new RuntimeException("curriculaId "+curriculaId+" was not found.");
        }
    }

//    private List<Curricula> getCurriculumForCourseId(long courseId) {
//        List<Curricula> result = new ArrayList<>();
//        String sql = "SELECT * FROM curriculas WHERE course_id = ?;";
//        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, courseId);
//        while (rowSet.next()) {
//            Curricula curricula = mapRowToCurricula(rowSet);
//            result.add(curricula);
//        }
//        return result;
//    }

    //TODO Investigate Null Pointer Exception Here
    @Override
    public Curricula createCurricula(Curricula curricula) {
        String sql = "INSERT INTO curriculas (curricula_id, course_id, curricula_name, summary, description) " +
                "VALUES (default,?,?,?,?) RETURNING curricula_id;";
        Long newId = jdbcTemplate.queryForObject(sql, Long.class, curricula.getCourseId(), curricula.getName(),
                curricula.getSummary(), curricula.getDescription());
        curricula.setId(newId);
        return curricula;
    }

    @Override
    public boolean updateCurricula(Curricula curricula) {
        String sql = "UPDATE curriculas SET " +
                "course_id = ?, curricula_name = ?, summary = ?, description = ? " +
                "WHERE curricula_id = ?;";
        int count = jdbcTemplate.update(sql, curricula.getCourseId(), curricula.getName(),
                curricula.getSummary(), curricula.getDescription(), curricula.getId());

        return count == 1;
    }

    @Override
    public boolean deleteCurricula(long curriculaId) {
        String sql = "DELETE from curriculas WHERE " +
                "curricula_id = ?;";
        int count = jdbcTemplate.update(sql, curriculaId);
        return count == 1;
    }

    @Override
    public List<Curricula> getCurriculasByCourseId(Long courseId) {
        List<Curricula> result = new ArrayList<>();
        String sql = "SELECT * FROM curriculas WHERE course_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, courseId);
        while (rowSet.next()) {
            Curricula curricula = mapRowToCurricula(rowSet);
            result.add(curricula);
        }
        return result;
    }

    @Override
    public Enrollment createEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO enrollment(enrollment_id,student_id, course_id) VALUES (default,?,?) RETURNING enrollment_id;";
        Long newId = jdbcTemplate.queryForObject(sql, Long.class, enrollment.getStudentId(), enrollment.getCourseId());
        enrollment.setEnrollmentId(newId);
        return enrollment;
    }

    @Override
    public boolean isEnrolled(Long courseId, Long studentId) {
        String sql = "SELECT COUNT(*) FROM enrollment " +
                "JOIN users ON user_id = student_id " +
                "JOIN courses ON courses.course_id = enrollment.course_id " +
                "WHERE student_id = ? AND enrollment.course_id = ?; ";
        Integer results = jdbcTemplate.queryForObject(sql, Integer.class, studentId, courseId);
        if(results==1) {
            return true;
        }
        return false;
    }



    private Curricula mapRowToCurricula(SqlRowSet rs) {
        Curricula curricula = new Curricula();
        curricula.setId(rs.getLong("curricula_id"));
        curricula.setCourseId(rs.getLong("course_id"));
        curricula.setName(rs.getString("curricula_name"));
        curricula.setSummary(rs.getString("summary"));
        curricula.setDescription(rs.getString("description"));
        return curricula;
    }
    private Enrollment mapRowToEnrollment(SqlRowSet rs) {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(rs.getLong("enrollment_id"));
        enrollment.setStudentId(rs.getLong("student_id"));
        enrollment.setCourseId(rs.getLong("course_id"));
        enrollment.setFinalGrade(rs.getInt("final_grade"));
        return enrollment;
    }


    @Override
    public Homework createHomework(Homework homework) {
        String sql = "INSERT INTO Homework VALUES (default,?,?) RETURNING homework_id;";
        Long newId = jdbcTemplate.queryForObject(sql, Long.class, homework.getCurriculaId(), homework.getDescription(),
                homework.getDescription());
        homework.setId(newId);
        return homework;
    }
}