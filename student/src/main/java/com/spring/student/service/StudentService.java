package com.spring.student.service;

import com.spring.student.dto.SchoolDto;
import com.spring.student.dto.StudentDto;
import com.spring.student.entity.Student;
import com.spring.student.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final RestTemplate restTemplate;

    public StudentService(StudentRepository studentRepository, RestTemplate restTemplate) {
        this.studentRepository = studentRepository;
        this.restTemplate = restTemplate;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public StudentDto getStudentWithSchool(String studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        String url = "http://school/school/" + student.getSchoolId();
        SchoolDto school = restTemplate.getForObject(url, SchoolDto.class);

        return new StudentDto(student.getId(), student.getName(), student.getGenre(), student.getSchoolId(), school);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student deleteStudent(String id) {
        final var student = studentRepository.findById(id);

        if (student.isEmpty()) {
            throw new IllegalArgumentException("Student not found");
        }

        studentRepository.delete(student.get());

        return student.get();
    }
}