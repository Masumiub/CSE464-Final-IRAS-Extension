package com.masum.irasExtension;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button facultybtn;
    Button studentbtn;

    Button LoginBtn;
    Button SignUpBtn;

    EditText username, password, role;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        username = (EditText)findViewById(R.id.usernameID);
        password = (EditText)findViewById(R.id.passwordID);
        role = (EditText)findViewById(R.id.roleID);

//        facultybtn = (Button) findViewById(R.id.facultybtn);
//        facultybtn.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                openFacultyActivity();
//            }
//        });
//
//        studentbtn = (Button) findViewById(R.id.studentbtn);
//        studentbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openStudentActivity();
//            }
//        });

        LoginBtn = (Button) findViewById(R.id.loginBtnID);
        LoginBtn.setOnClickListener(this);
        SignUpBtn = (Button) findViewById(R.id.signupBtnID);
        SignUpBtn.setOnClickListener(this);

    }

//    public void openFacultyActivity(){
//        Intent intent1 = new Intent(this, FacultyActivity.class);
//        startActivity(intent1);
//    }
//
//    public void openStudentActivity(){
//        Intent intent2 = new Intent(this, StudentActivity.class);
//        startActivity(intent2);
//    }

    @Override
    public void onClick(View view) {
        String user_name = username.getText().toString();
        String Pass_word = password.getText().toString();
        String Role = role.getText().toString();

        if(view.getId()==R.id.loginBtnID){
            int result = databaseHelper.findPassword(user_name, Pass_word, Role);

            if(result == 1){
                Intent intent = new Intent(LoginActivity.this, FacultyActivity.class);
                startActivity(intent);
            }
            else if(result == 2){
                Intent intent2 = new Intent(LoginActivity.this, StudentActivity.class);
                startActivity(intent2);
            }
            else{
                Toast.makeText(getApplicationContext(), "Username & password didn't Match", Toast.LENGTH_LONG).show();
            }
        }
        else if(view.getId()==R.id.signupBtnID){
            Intent intent3 = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent3);
        }
    }
}