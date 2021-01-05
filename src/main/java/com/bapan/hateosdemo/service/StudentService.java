package com.bapan.hateosdemo.service;

import java.util.List;

import com.bapan.hateosdemo.model.Student;

public interface StudentService {
    /**
     * 
     * @param student
     * @return
     */
    Student saveStudent(Student student);
    /**
     * 
     * @return
     */
    List<Student> retreiveAllStudents();
    /**
     * 
     * @param id
     * @return
     */
    Student getById(int id);
    /**
     * 
     * @param name
     * @return
     */
    List<Student> getByName(String name);
}
