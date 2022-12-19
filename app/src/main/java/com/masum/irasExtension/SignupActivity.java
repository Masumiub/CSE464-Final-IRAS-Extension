package com.masum.irasExtension;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText name, username, password, role;
    private Button signupBtn;

    UserDetails userDetails;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = (EditText) findViewById(R.id.nameID);
        username = (EditText)findViewById(R.id.usernameID);
        password = (EditText)findViewById(R.id.passwordID);
        role = (EditText)findViewById(R.id.roleID);

        signupBtn = (Button)findViewById(R.id.signupBtnID);
        signupBtn.setOnClickListener(this);
        userDetails = new UserDetails();


        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    @Override
    public void onClick(View view) {

        String Name = name.getText().toString();
        String Username = username.getText().toString();
        String Password = password.getText().toString();
        String Role = role.getText().toString();

        userDetails.setName(Name);
        userDetails.setUsername(Username);
        userDetails.setPassword(Password);
        userDetails.setRole(Role);

        long rowId = databaseHelper.insertData(userDetails);

        if(rowId>0){
            Toast.makeText(getApplicationContext(), "Record "+rowId+" has been inserted!",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "OOPs! Record was not inserted!",Toast.LENGTH_LONG).show();
        }
    }
}