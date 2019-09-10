package com.example.guojia3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.guojia3.R;
import com.example.guojia3.adapter.StudentAdapter;
import com.example.guojia3.entity.Student;
import com.example.guojia3.entity.StudentService;

import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private Button btn;
    private Button btn1;
    private Button btn2;
    private ListView list;
    private static final int ADD_REQUEST = 100;
    private static final int MODIFY_REQUEST = 101;
    private Student selectedStudent;
    private int selectedPos=0;
    private StudentService studentService;
    private List<Student> students;
    private StudentAdapter studentAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.btn);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                intent.putExtra("flag", "添加");
                startActivityForResult(intent, ADD_REQUEST);

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);

            }
        });



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                selectedPos = position;
                selectedStudent = (Student) parent.getItemAtPosition(position);

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                        intent.putExtra("flag", "修改");

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("student", selectedStudent);
                        intent.putExtras(bundle);

                        startActivityForResult(intent, MODIFY_REQUEST);
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        studentService.delete(selectedStudent.getName());
                        students.remove(position);
                        studentAdapter.notifyDataSetChanged();
                    }
                });
            }
        });


    }


}
