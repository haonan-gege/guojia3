package com.example.guojia3.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.guojia3.entity.Student;
import com.example.guojia3.utils.MyDBHelper;

import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDao {

    private MyDBHelper helper;
    private SQLiteDatabase dbs;

    public StudentDAOImpl(Context context) {
        //调用MyDBHelper类的构造方法时
        //若发现demo.db不存在会调用onCreate创建
        //若发现demo.db存在，且version的版本与已有的不一致，则调用onUpgrade方法更新
        helper = new MyDBHelper(context);
    }

    @Override
    public List<Student> selectAllStudents() {
        List<Student> students = null;
        // 1. 获取SQLiteDatabase对象
        dbs = helper.getReadableDatabase();
        String sql = "select * from student";
        // 2. 执行SQL查询
        Cursor cursor = dbs.rawQuery(sql, null);
        // 3. 处理结果
        if (cursor != null && cursor.getCount() > 0) {
            students = new ArrayList<>();
            while (cursor.moveToNext()) {
                Student student1 = new Student();
                student1.setId(cursor.getInt(cursor.getColumnIndex("id")));
                student1.setName(cursor.getString(cursor.getColumnIndex("name")));
                student1.setClass_name(cursor.getString(cursor.getColumnIndex("class_name")));
                student1.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                students.add(student1);
            }
            // 4. 关闭cursor
            cursor.close();
        }
        // 5. 返回结果
        return students;
    }

    @Override
    public void insert(Student student) {
        dbs = helper.getWritableDatabase();
        String sql = "insert into student values(null,?,?,?)";
        dbs.execSQL(sql, new Object[]{
                student.getName(),
                student.getAge(),
                student.getClass_name()});

    }

    @Override
    public void update(Student student) {
        // 1. 获取dbs对象
        dbs = helper.getWritableDatabase();
        // 2. 执行sql
        String sql = "update student set name=? where class_name=?";
        dbs.execSQL(sql, new Object[]{
                student.getName(),
                student.getClass_name()
        });
    }

    @Override
    public void delete(String studentName) {
        // 1. 获取db对象
        dbs = helper.getWritableDatabase();
        // 2. 执行sql
        String sql = "delete from student where name=?";
        dbs.execSQL(sql, new Object[]{studentName});

    }
}
