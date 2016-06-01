package com.example.arthu.student;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;

import java.util.List;

public class StudentHelperDB  {
    private final StudentActivity context;

    public StudentHelperDB(StudentActivity context) {
        this.context = context;
    }

    public void open() {
        ActiveAndroid.initialize(this.context);
    }

    public void InsertStudent(String code, String name, String email) {
        Student newStudent = new Student(code, name, email);
        newStudent.save();
    }

    public void UpdateStudent(String id, String code, String name, String email) {
        Student student = new Student(code, name, email);
        student.Id = Integer.parseInt(id);
        student.save();
    }

    public void DeleteStudent(String id) {
        Student student = Student.load(Student.class, Integer.parseInt(id));
        student.delete();
    }

    public List<Student> GetListStudents(){
        return new Select()
                .from(Student.class)
                .orderBy("Name ASC")
                .execute();
    }

    public Student GetStudentById(int id){
        Student student = Student.load(Student.class, id);
        return student;
    }
}