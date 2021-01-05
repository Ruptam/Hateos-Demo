package com.bapan.hateosdemo.controller;

import java.util.List;

import com.bapan.hateosdemo.model.Student;
import com.bapan.hateosdemo.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/student")
    public ResponseEntity<?> saveStudent(@RequestBody Student student) {
        Student savedStudent = studentService.saveStudent(student);

        Link allStudentlink = WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(this.getClass()).getAllStudents()).withRel("allStudents");

        Link studentByIdlink = WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(this.getClass()).getStudentById(
                savedStudent.getId())).withRel("student_By_Id");

        Link studentByNameLink = WebMvcLinkBuilder.linkTo(
            WebMvcLinkBuilder.methodOn(this.getClass()).getStudentByName(
                savedStudent.getName())).withRel("student_By_Name");

        savedStudent.add(allStudentlink);
        savedStudent.add(studentByIdlink);
        savedStudent.add(studentByNameLink);
        return new ResponseEntity<Student>(savedStudent, HttpStatus.OK);
    }


    @GetMapping(value = "/student")
    public ResponseEntity<?> getAllStudents() {
        List<Student> allStudents = studentService.retreiveAllStudents();
        allStudents.stream().forEach(student -> {
            student.removeLinks();
            // entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
            //     this.getClass()).getStudentById(student.getId())).withRel("student_by_id"));

            // entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
            //     this.getClass()).getStudentByName(student.getName())).withRel("student_by_name"));

            Link studentByIdLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).getStudentById(student.getId()))
                .withRel("student_by_id");

            Link studentByNameLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass()).getStudentByName(student.getName()))
                .withRel("student_by_name");
            
            student.add(studentByIdLink);
            student.add(studentByNameLink);
        });
        return new ResponseEntity<List<Student>>(allStudents, HttpStatus.OK);
    }

    @GetMapping(value = "/student/id/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") int id) {
        Student student = studentService.getById(id);
        if (student != null) {
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        }
        return new ResponseEntity<String>("NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/student/name/{name}")
    public ResponseEntity<?> getStudentByName(@PathVariable("name") String name) {
        List<Student> students = studentService.getByName(name);
        if (students != null) {
            return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
        }
        return new ResponseEntity<String>("NOT FOUND", HttpStatus.NOT_FOUND);
    }

}
