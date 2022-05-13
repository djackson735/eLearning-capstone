package com.techelevator.model.courses;

public class Homework {

    private Long id;
    private Long curriculaId;
    private String description;


    public Homework() { }

    public Homework(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCurriculaId() {
        return curriculaId;
    }

    public void setCurriculaId(Long curriculaId) {
        this.curriculaId = curriculaId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
