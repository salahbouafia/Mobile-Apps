package com.example.studentdatabase;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    public static class Student{
        public int studentID;
        public String Name;
        public String LName;

        public Student(int id,String n,String ln){
            this.studentID = id;
            this.Name = n;
            this.LName = ln;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.mix);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main4), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<Student> Students = new ArrayList<>();
        Button add = findViewById(R.id.add);
        Button delete = findViewById(R.id.delete);
        Button search = findViewById(R.id.search);
        Button edit = findViewById(R.id.edit);
        Button display = findViewById(R.id.display);
        EditText name= findViewById(R.id.Name);
        EditText lname= findViewById(R.id.LastName);
        EditText ID= findViewById(R.id.ID);
        TextView info = findViewById(R.id.info);

        add.setOnClickListener(v -> {
            if (Students.size() >= 10) {
                Toast.makeText(this,"Cannot Add More Than 10 Students",Toast.LENGTH_SHORT).show();
                return;
            }
            if(!ID.getText().toString().isEmpty()){
                int id = Integer.parseInt(ID.getText().toString());
                String n = name.getText().toString();
                String ln = lname.getText().toString();

                if (n.isEmpty() || ln.isEmpty()) {
                    Toast.makeText(this,"Pleas Fill Up The Information",Toast.LENGTH_SHORT).show();
                    return;
                }

                Students.add(new Student(id,n,ln));
                Toast.makeText(this,"Student Added Successfully",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Please Give The Student ID",Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(v -> {
            if(!ID.getText().toString().isEmpty()){
                int id = Integer.parseInt(ID.getText().toString());
                if (Students.removeIf(student -> student.studentID == id)) {
                    Toast.makeText(this,"Student Removed Successfully",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"Student Not Found",Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this,"Please Give The Student ID",Toast.LENGTH_SHORT).show();
            }

        });

        search.setOnClickListener(v -> {
            if(!ID.getText().toString().isEmpty()){
                int id = Integer.parseInt(ID.getText().toString());
                for (Student s : Students) {
                    if (s.studentID == id) {
                        String inf = s.Name + " " + s.LName + "\n";
                        info.setText(inf);
                        Toast.makeText(this,"Student Found",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
            String inf = "Student Not Found";
            Toast.makeText(this,"Student Not Found",Toast.LENGTH_SHORT).show();
            info.setText(inf);
        });

        edit.setOnClickListener(v -> {
            if(!ID.getText().toString().isEmpty()){
                int id = Integer.parseInt(ID.getText().toString());
                String n = name.getText().toString();
                String ln = lname.getText().toString();
                if(n.isEmpty() || ln.isEmpty()){
                    Toast.makeText(this,"Please Enter Student Information",Toast.LENGTH_SHORT).show();
                    return;
                }
                for (Student student : Students) {
                    if (student.studentID == id) {
                        student.Name = n;
                        student.LName = ln;
                        Toast.makeText(this,"Student Information Changed Successfully",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
            Toast.makeText(this,"Please give The Student ID",Toast.LENGTH_SHORT).show();
        });

        display.setOnClickListener(v -> {
            if (Students.isEmpty()) {
                info.setText("No Students To Display");
                Toast.makeText(this,"No Students To Display",Toast.LENGTH_SHORT).show();
                return;
            }

            Iterator<Student>it = Students.iterator();
            String allstudents = "";
            while(it.hasNext()){
                Student s = it.next();
                allstudents += s.Name + " " + s.LName + "\n";
            }
            info.setText(allstudents);
        });

    }
}