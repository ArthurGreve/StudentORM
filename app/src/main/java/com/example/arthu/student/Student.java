package com.example.arthu.student;

import com.activeandroid.Model;
import com.activeandroid.annotation.*;

@Table(name = "Students")
public class Student extends Model {

    @Column(name = "Id", unique = true, onUniqueConflict = Column.ConflictAction.REPLACE)
    public int Id;
    @Column(name = "Code")
    public String Code;
    @Column(name = "Name")
    public String Name;
    @Column(name = "Email")
    public String Email;

    public Student(){
        super();
    }

    public Student(String code, String name, String email){
        this();

        this.Code = code;
        this.Name = name;
        this.Email = email;
    }

    @Override
    public String toString() {
        return this.Name;
    }
}
