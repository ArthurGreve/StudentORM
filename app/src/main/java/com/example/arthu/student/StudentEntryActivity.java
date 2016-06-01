package com.example.arthu.student;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentEntryActivity extends AppCompatActivity {

    private int Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_entry);

        if (this.getIntent().hasExtra("Id")) {
            this.Id = this.getIntent().getIntExtra("Id", -1);
            this.showFields(this.Id);
        }

        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtId = (EditText) findViewById(R.id.IdField);
                EditText txtCode = (EditText) findViewById(R.id.CodeField);
                EditText txtName = (EditText) findViewById(R.id.NameField);
                EditText txtEmail = (EditText) findViewById(R.id.EmailField);

                if (!isFieldsEmpty(txtCode, txtName, txtEmail)) {
                    if (TextUtils.isEmpty(txtId.getText()))
                        Insert(txtCode, txtName, txtEmail);
                    else
                        Update(txtId, txtCode, txtName, txtEmail);

                    finish();
                }
            }
        });

        Button btnDelete = (Button)findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText txtId = (EditText) findViewById(R.id.IdField);

                if(!isFieldsEmpty(txtId)){
                    Delete(txtId.getText().toString());
                    finish();
                }
            }
        });
    }

    private void showFields(int id) {
        Student student = StudentActivity.getStudentDB().GetStudentById(id);

        EditText txtId = (EditText)findViewById(R.id.IdField);
        EditText txtCode = (EditText)findViewById(R.id.CodeField);
        EditText txtName = (EditText)findViewById(R.id.NameField);
        EditText txtEmail = (EditText)findViewById(R.id.EmailField);

        txtId.setText(Integer.toString(id));
        txtCode.setText(student.Code);
        txtName.setText(student.Name);
        txtEmail.setText(student.Email);
    }

    private void Insert(EditText txtCode, EditText txtName, EditText txtEmail) {
        StudentActivity.getStudentDB().InsertStudent(txtCode.getText().toString(), txtName.getText().toString(), txtEmail.getText().toString());
    }

    private void Update(EditText txtId, EditText txtCode, EditText txtName, EditText txtEmail) {
        StudentActivity.getStudentDB().UpdateStudent(txtId.getText().toString(), txtCode.getText().toString(), txtName.getText().toString(), txtEmail.getText().toString());
    }

    private void Delete(String id) {
        StudentActivity.getStudentDB().DeleteStudent(id);
    }

    private boolean isFieldsEmpty(EditText... fields) {
        boolean empty = false;

        for (EditText field: fields)
        {
            if(TextUtils.isEmpty(field.getText().toString())) {
                empty = true;
                field.setError("Campo deve ser preenchido.");
            }
        }

        return empty;
    }
}
