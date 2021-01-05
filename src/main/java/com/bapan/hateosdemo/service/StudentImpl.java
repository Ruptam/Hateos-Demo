package com.bapan.hateosdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bapan.hateosdemo.model.Student;

import org.springframework.stereotype.Service;

@Service
public class StudentImpl implements StudentService {

    List<Student> allStudents = new ArrayList<Student>();

    
    public Student saveStudent(Student student) {
        allStudents.add(student);
        return student;
    }

    
    public List<Student> retreiveAllStudents() {
        return allStudents;
    }

    
    public Student getById(int id) {
        Student requiredStudent = null;
        for (Student student : allStudents) {
            if (student.getId() == id) {
                requiredStudent = student;
            }
        }
        return requiredStudent;
    }

    
    public List<Student> getByName(String name) {
        List<Student> requiredStudents = null;
        requiredStudents = allStudents.stream().filter(student -> 
            student.getName().equals(name)).collect(Collectors.toList());
        return requiredStudents;
    }
    
}
