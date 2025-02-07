package com.spring.school.controller;

import com.spring.school.entity.School;
import com.spring.school.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public ResponseEntity<List<School>> findAll() {
        return ResponseEntity.ok(schoolService.findAll());
    }

    @GetMapping("/{id}")
    public School findById(@PathVariable Long id) {
        return schoolService.findById(id);
    }

    @PostMapping
    public ResponseEntity<School> addSchool(@RequestBody School school) {
        School responseSchool = schoolService.postSchool(school);
        return ResponseEntity.ok(responseSchool);
    }

    @DeleteMapping("/{id}")
    public School deleteSchool(@PathVariable Long id) {
        return schoolService.deleteSchool(id);
    }
}
