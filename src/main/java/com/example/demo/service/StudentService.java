package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.exception.DuplicationException;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public Student createStudent(Student student){
        try {
            Student newStudent = studentRepository.save(student);
            return newStudent;
        }catch (Exception e){
            throw new DuplicationException("Duplicate student code!");
        }
    }
}
