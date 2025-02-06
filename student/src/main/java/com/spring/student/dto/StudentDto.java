package com.spring.student.dto;

public class StudentDto {
    private String id;

    private String name;

    private String genre;

    private Long schoolId;
    private SchoolDto schoolDto;

    public StudentDto(String id, String name, String genre, Long schoolId, SchoolDto school) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.schoolId = schoolId;
        this.schoolDto = school;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public SchoolDto getSchoolDto() {
        return schoolDto;
    }

    public void setSchoolDto(SchoolDto schoolDto) {
        this.schoolDto = schoolDto;
    }
}
