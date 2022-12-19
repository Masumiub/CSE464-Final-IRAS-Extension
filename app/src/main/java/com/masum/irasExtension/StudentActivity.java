package com.masum.irasExtension;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener{

    CourseOutlineHelperClass courseOutlineHelper;
    QuestionHelperClass questionHelperClass;
    AnswerHelperClass answerHelperClass;
    GradeHelperClass gradeHelperClass;

    private Button DisplayCourseOutlineBtn;
    private Button DisplayQuestionsBtn;
    private Button SubmitAnsBtn;
    private Button ViewGradeBtn;

    private EditText courseID, StudentID, Answer1, Answer2, Answer3, Answer4, Answer5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        courseOutlineHelper = new CourseOutlineHelperClass(this);
        SQLiteDatabase sqLiteDatabase =  courseOutlineHelper.getWritableDatabase();

        questionHelperClass = new QuestionHelperClass(this);
        SQLiteDatabase sqLiteDatabase2 = questionHelperClass.getWritableDatabase();

        answerHelperClass = new AnswerHelperClass(this);
        SQLiteDatabase sqLiteDatabase3 = answerHelperClass.getWritableDatabase();

        gradeHelperClass = new GradeHelperClass(this);
        SQLiteDatabase sqLiteDatabase4 = gradeHelperClass.getWritableDatabase();

        DisplayCourseOutlineBtn = (Button)findViewById(R.id.viewCourseOutlineBtn);
        DisplayCourseOutlineBtn.setOnClickListener(this);

        DisplayQuestionsBtn = (Button) findViewById(R.id.viewQuestionBtn);
        DisplayQuestionsBtn.setOnClickListener(this);

        SubmitAnsBtn = (Button)findViewById(R.id.submitAnsBtnID);
        SubmitAnsBtn.setOnClickListener(this);

        ViewGradeBtn = (Button)findViewById(R.id.viewGradeBtnID);
        ViewGradeBtn.setOnClickListener(this);

        courseID = (EditText) findViewById(R.id.coruseID);
        StudentID = (EditText) findViewById(R.id.studentID);
        Answer1 = (EditText) findViewById(R.id.answer1ID);
        Answer2 = (EditText) findViewById(R.id.answer2ID);
        Answer3 = (EditText) findViewById(R.id.answer3ID);
        Answer4 = (EditText) findViewById(R.id.answer4ID);
        Answer5 = (EditText) findViewById(R.id.answer5ID);

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.viewQuestionBtn){ //viewQuestionBtn

            Cursor questionset = questionHelperClass.displayAllData();

            if(questionset.getCount()==0){
                showQuestion("Error","No Data Found!");
                return;
            }
            else{
                StringBuffer stringBuffer2 = new StringBuffer();
                while(questionset.moveToNext()){
                    stringBuffer2.append("Sno :"+questionset.getString(0) +"\n");
                    stringBuffer2.append("Course ID :"+questionset.getString(1) +"\n");
                    stringBuffer2.append("Question 1 :"+questionset.getString(2) +"\n");
                    stringBuffer2.append("Question 2 :"+questionset.getString(3) +"\n");
                    stringBuffer2.append("Question 3 :"+questionset.getString(4) +"\n");
                    stringBuffer2.append("Question 4 :"+questionset.getString(5) +"\n");
                    stringBuffer2.append("Question 5 :"+questionset.getString(6) +"\n\n");
                }
                showQuestion("All Questions", stringBuffer2.toString());
            }
        }


        if(view.getId()==R.id.viewCourseOutlineBtn){ // courseoutlineButton

            Cursor resultset = courseOutlineHelper.displayAllData();

            if(resultset.getCount()==0){
                showData("Error","No Data Found!");
                return;
            }
            else{
                StringBuffer stringBuffer = new StringBuffer();
                while(resultset.moveToNext()){
                    stringBuffer.append("Sno :"+resultset.getString(0) +"\n");
                    stringBuffer.append("Course ID :"+resultset.getString(1) +"\n");
                    stringBuffer.append("Course Name :"+resultset.getString(2) +"\n");
                    stringBuffer.append("Credit :"+resultset.getString(3) +"\n");
                    stringBuffer.append("Course Outline :"+resultset.getString(4) +"\n");
                    stringBuffer.append("Grading :"+resultset.getString(5) +"\n");
                    stringBuffer.append("Policy :"+resultset.getString(6) +"\n");
                }
                showData("All Course Outline", stringBuffer.toString());
            }
        }

        String Course_Id = courseID.getText().toString();
        String Student_Id = StudentID.getText().toString();
        String Answer_1 = Answer1.getText().toString();
        String Answer_2 = Answer2.getText().toString();
        String Answer_3 = Answer3.getText().toString();
        String Answer_4 = Answer4.getText().toString();
        String Answer_5 = Answer5.getText().toString();

        if(view.getId()==R.id.submitAnsBtnID){
            long rowId =   answerHelperClass.insertData(Course_Id, Student_Id, Answer_1, Answer_2, Answer_3, Answer_4, Answer_5);
            if(rowId>0){
                Toast.makeText(getApplicationContext(), "Record "+rowId+" has been inserted!",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "OOPs! Record was not inserted!",Toast.LENGTH_LONG).show();
            }
        }


        if(view.getId()==R.id.viewGradeBtnID){ //viewgrades

            Cursor gradeset = gradeHelperClass.displayAllData();

            if(gradeset.getCount()==0){
                showGrade("Error","No Data Found!");
                return;
            }
            else{
                StringBuffer stringBuffer3 = new StringBuffer();
                while(gradeset.moveToNext()){
                    stringBuffer3.append("Sno :"+gradeset.getString(0) +"\n");
                    stringBuffer3.append("Course ID :"+gradeset.getString(1) +"\n");
                    stringBuffer3.append("Student ID :"+gradeset.getString(2) +"\n");
                    stringBuffer3.append("Grade :"+gradeset.getString(3) +"\n");
                }
                showGrade("All Grades", stringBuffer3.toString());
            }
        }
    }
    public void showQuestion(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.show();
    }

    public void showData(String title, String message){ // courseoutlineButton
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.show();
    }


    public void showGrade(String title, String message){ // viewgradeButton
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.show();
    }
}