package com.example.guojia3.entity;

import android.content.Context;
import com.example.guojia3.dao.StudentDAOImpl;
import com.example.guojia3.dao.StudentDao;

import java.util.List;

public class StudentServiceImpl implements StudentService{
    private StudentDao studentDao;

    public StudentServiceImpl(Context context){
        studentDao= (StudentDao) new StudentDAOImpl(context);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.selectAllStudents();
    }

    @Override
    public void insert(Student student) {
        studentDao.insert(student);
    }

    @Override
    public void modifyRealNumber(Student student) {
        studentDao.update(student);
    }

    @Override
    public void delete(String StudentName) {
        studentDao.delete(StudentName);
    }
}
