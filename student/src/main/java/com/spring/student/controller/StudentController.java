package com.spring.student.controller;

import com.spring.student.dto.StudentDto;
import com.spring.student.entity.Student;
import com.spring.student.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentWithSchool(@PathVariable String id) {
        return ResponseEntity.ok(studentService.getStudentWithSchool(id));
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
       return ResponseEntity.ok(studentService.addStudent(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSchool(@PathVariable String id) {
        studentService.deleteSchool(id);

        return ResponseEntity.ok(true);
    }
}
