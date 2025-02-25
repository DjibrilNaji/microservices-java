package com.spring.school.service;

import com.spring.school.entity.School;
import com.spring.school.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<School> findAll() {
        return schoolRepository.findAll();
    }

    public School findById(Long id) {
        return schoolRepository.findById(id).orElse(null);
    }

    public School postSchool(School school) {
      return schoolRepository.save(school);
    }

    public School deleteSchool(Long id) {
        final var school = schoolRepository.findById(id);

        if (school.isEmpty()) {
            throw new IllegalArgumentException("School not found");
        }

        schoolRepository.delete(school.get());

        return school.get();
    }
}