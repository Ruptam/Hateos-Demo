package com.bapan.hateosdemo.model;

import org.springframework.hateoas.RepresentationModel;

/**
 * @author RSadhukhan
 */
public class Student extends RepresentationModel<Student> {
    /**
     * 
     */
    private int id;
    /**
     * 
     */
    private String name;
    /**
     * 
     */
    private String section;
    /**
     * 
     */
    private int rollNo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", rollNo=" + rollNo + ", section=" + section + "]";
    }
}
