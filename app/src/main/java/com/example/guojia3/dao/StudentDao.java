package com.example.guojia3.dao;

import com.example.guojia3.entity.Student;
import com.example.sqlitedemo.entity.Student;

import java.util.List;

public interface StudentDao {
    //查询所有的学生
    List<Student> selectAllStudents();
    //条件查询
    //增删改一个学生
    void insert(Student student);
    void update(Student student);
    void delete(String studentName);
}
