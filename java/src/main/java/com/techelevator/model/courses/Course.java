package com.techelevator.model.courses;

import java.util.List;

public class Course {
    private Long course_id;
    private String course_name;
    private Long teacher_id;
    private String level;
    private List<Curricula> curriculas;

    public Course() { }

    public Course(Long id, String course_name) {
        this.course_id = id;
        this.course_name = course_name;
    }

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<Curricula> getCurriculas() {
        return curriculas;
    }

    public void setCurriculas(List<Curricula> curriculas) {
        this.curriculas = curriculas;
    }
}