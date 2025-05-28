package com.example.demo.api;

import com.example.demo.entity.Student;

import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/student")
public class StudentApi {
    @Autowired
    StudentService studentService;
    @PostMapping
    public ResponseEntity createStudent(@Valid @RequestBody Student student) {
        Student newStudent = studentService.createStudent(student);
        return ResponseEntity.ok(newStudent);
    }
}
