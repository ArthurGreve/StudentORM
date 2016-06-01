package com.example.arthu.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.activeandroid.ActiveAndroid;

import java.util.List;

public class StudentActivity extends AppCompatActivity {

    private static StudentHelperDB studentDb;
    private static final int REQUEST_CODE = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        this.studentDb = new StudentHelperDB(this);
        this.studentDb.open();

        showStudents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, 0, 0, "Incluir");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case 0:
                Intent i = new Intent(this, StudentEntryActivity.class);
                startActivityForResult(i, REQUEST_CODE);
                return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.showStudents();
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showStudents() {
        final ListView studentList = (ListView)findViewById(R.id.studentView);

        List<Student> values = this.studentDb.GetListStudents();

        ArrayAdapter<Student> studentAdapter =  new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, values);

        studentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = (Student)studentList.getItemAtPosition(position);
                long studentId = student.Id;

                Intent i = new Intent(view.getContext(), StudentEntryActivity.class);
                i.putExtra("Id", studentId);
                startActivityForResult(i, REQUEST_CODE);
            }
        });

        studentList.setAdapter(studentAdapter);
    }

    public static StudentHelperDB getStudentDB(){
        return studentDb;
    }
}
