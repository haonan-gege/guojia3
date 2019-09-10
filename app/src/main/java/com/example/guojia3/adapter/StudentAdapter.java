package com.example.guojia3.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.guojia3.R;
import com.example.guojia3.R;
import com.example.guojia3.entity.Student;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private List<Student> students;

    public StudentAdapter(List<Student> students) {
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
            holder = new ViewHolder();

            holder.tvName = convertView.findViewById(R.id.s_name);
            holder.tvAge = convertView.findViewById(R.id.s_age);
            holder.tvClass = convertView.findViewById(R.id.s_class);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Student msg = students.get(position);
        holder.tvName.setText(msg.getName());
        holder.tvAge.setText(String.valueOf(msg.getAge()));
        holder.tvClass.setText(msg.getClass_name());

        return convertView;
    }

    static class ViewHolder {
        TextView tvName;
        TextView tvAge;
        TextView tvClass;
    }
}
