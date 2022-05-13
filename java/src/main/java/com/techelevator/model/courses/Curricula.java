package com.techelevator.model.courses;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class Curricula {
    private Long id;
    private String name;
    private Long courseId;
    private String description;
    private String summary;
    private String tag;

    public Curricula() { }

    public Curricula(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTeacherId(long idByUsername) {
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}