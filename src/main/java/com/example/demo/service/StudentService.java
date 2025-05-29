package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.exception.DuplicationException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Student> getAllStudents(){
        return studentRepository.findStudentsByIsDeletedFalse();
    }
    public Student updateStudent(Student student, long id){
        Student oldStudent = studentRepository.findStudentById(id);
        if(oldStudent == null){
            throw new NotFoundException("Student not found!");
        }else{
            oldStudent.setName(student.getName());
            oldStudent.setStudentCode(student.getStudentCode());
            oldStudent.setScore(student.getScore());
            return studentRepository.save(oldStudent);
        }
    }
    public void deleteStudent(long id){
        Student oldStudent = studentRepository.findStudentById(id);
        if(oldStudent == null){
            throw new NotFoundException("Student not found!");
        }else{
           oldStudent.setIsDeleted(true);
           studentRepository.save(oldStudent);
        }
    }
}
