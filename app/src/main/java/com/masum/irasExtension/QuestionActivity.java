package com.masum.irasExtension;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{

    QuestionHelperClass questionHelperClass;
    private EditText course_id, question1, question2, question3,question4, question5;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionHelperClass = new QuestionHelperClass(this);
        SQLiteDatabase sqLiteDatabase = questionHelperClass.getWritableDatabase();

        course_id = (EditText)findViewById(R.id.CourseID);
        question1 = (EditText)findViewById(R.id.Question1ID);
        question2 = (EditText)findViewById(R.id.Question2ID);
        question3 = (EditText)findViewById(R.id.Question3ID);
        question4 = (EditText)findViewById(R.id.Question4ID);
        question5 = (EditText)findViewById(R.id.Question5ID);
        saveBtn = (Button)findViewById(R.id.saveQuestionBtnID);

        saveBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        String Course_Id = course_id.getText().toString();
        String Question1 = question1.getText().toString();
        String Question2 = question2.getText().toString();
        String Question3 = question3.getText().toString();
        String Question4 = question4.getText().toString();
        String Question5 = question5.getText().toString();


        if (view.getId() == R.id.saveQuestionBtnID) {
            long rowId = questionHelperClass.insertData(Course_Id, Question1, Question2, Question3, Question4, Question5);
            if (rowId > 0) {
                Toast.makeText(getApplicationContext(), "Record " + rowId + " has been inserted!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "OOPs! Record was not inserted!", Toast.LENGTH_LONG).show();
            }
        }
    }
}