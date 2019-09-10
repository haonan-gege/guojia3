package com.example.guojia3.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.guojia3.R;
import com.example.guojia3.R;
import com.example.sqlitedemo.entity.Student;
import com.example.guojia3.entity.StudentService;
import com.example.guojia3.entity.StudentServiceImpl;

import java.util.Arrays;
import java.util.List;

public class Main3Activity extends Activity implements View.OnClickListener {
    private Button btn_true,btn_cancel;

    private Spinner classmate;
    private EditText etName ;
    private EditText etAge;

    private List<String> class_name;
    private Student student;
    private StudentService studentService;
    private String flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        studentService=new StudentServiceImpl(this);

        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        flag = intent.getStringExtra("flag");
        Bundle bundle = intent.getExtras();
        if(bundle != null) {
            student = (Student) bundle.getSerializable("student");
            if(student != null) {
                etName.setText(String.valueOf(student.getName()));
                etName.setEnabled(false);
                classmate.setSelection(class_name.indexOf(student.getClass_name()), true);
                etAge.setText(String.valueOf(student.getAge()));
            }
        }
    }

    private void initView() {
        etName = findViewById(R.id.username);
        etAge = findViewById(R.id.age);
        classmate = findViewById(R.id.classname);
        btn_cancel = findViewById(R.id.cancel);
        btn_true =findViewById(R.id.yes);

        btn_cancel.setOnClickListener(this);
        btn_true.setOnClickListener(this);
        class_name =  Arrays.asList(getResources().getStringArray(R.array.classmate));
        classmate.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                class_name));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yes:
                updateStudent();
                break;
            case R.id.cancel:
                Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void updateStudent() {
        if (student == null) {
            student = new Student();
        }
        //获取将界面上修改的数据写入对象
        student.setName(etName.getText().toString());
        student.setClass_name((String) classmate.getSelectedItem());
        student.setAge(Integer.valueOf(etAge.getText().toString()));
        //根据flag进行修改或添加到数据库表
        if("修改".equals(flag)) {
            studentService.modifyRealNumber(student);
        } else if("添加".equals(flag)) {
            studentService.insert(student);
        }

        // 将修改的数据返回Activity
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("student", student);
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
