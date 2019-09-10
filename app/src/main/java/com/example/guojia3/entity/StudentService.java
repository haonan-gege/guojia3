package com.example.guojia3.entity;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    void insert(Student student);
    void modifyRealNumber(Student student);
    void delete(String StudentName);
}
