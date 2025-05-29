package com.example.demo.api;

import com.example.demo.entity.Student;

import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping()
    public ResponseEntity getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
    @PutMapping("/{id}")
    public ResponseEntity updateStudent(@PathVariable long id, @Valid @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student, id);
        return ResponseEntity.ok(updatedStudent);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable long id) {
            studentService.deleteStudent(id);
            return ResponseEntity.ok("Student deleted");
    }
}
