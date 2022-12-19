package com.masum.irasExtension;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FacultyActivity extends AppCompatActivity implements View.OnClickListener {

    CourseOutlineHelperClass courseOutlineHelper;
    GradeHelperClass gradeHelperClass;

    private EditText course_id, course_name, credit, course_outcome,grading, policy, deleteID;
    private Button saveBtn;

    private Button DisplayCourseOutlineBtn;
    private Button DeleteCourseOutlineBtn;
    private Button CreateQuestionBtn;
    private Button CheckAnsBtn;

    private EditText course_grade_id, student_grade_id, grade;
    private Button SubmitGradeBtn;

    AnswerHelperClass answerHelperClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);

        courseOutlineHelper = new CourseOutlineHelperClass(this);
        SQLiteDatabase sqLiteDatabase =  courseOutlineHelper.getWritableDatabase();

//        answerHelperClass = new AnswerHelperClass(this);
//        SQLiteDatabase sqLiteDatabase3 = answerHelperClass.getWritableDatabase();
//
//        gradeHelperClass = new GradeHelperClass(this);
//        SQLiteDatabase sqLiteDatabase4 = gradeHelperClass.getWritableDatabase();

        course_id = (EditText)findViewById(R.id.course_id);
        course_name = (EditText)findViewById(R.id.course_name);
        credit = (EditText)findViewById(R.id.credit);
        course_outcome = (EditText)findViewById(R.id.course_outcome);
        grading = (EditText)findViewById(R.id.grading);
        policy = (EditText)findViewById(R.id.policy);
        deleteID = (EditText)findViewById(R.id.deleteSno);

        saveBtn = (Button)findViewById(R.id.saveBtn);
        DisplayCourseOutlineBtn = (Button)findViewById(R.id.viewCourseOutlineBtn);
        DeleteCourseOutlineBtn = (Button)findViewById(R.id.deleteCourseOutlineBtn);

        CreateQuestionBtn = (Button)findViewById(R.id.CreateQuestionBtnID);
        CreateQuestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuestionActivity();
            }
        });

        saveBtn.setOnClickListener(this);
        DisplayCourseOutlineBtn.setOnClickListener(this);
        DeleteCourseOutlineBtn.setOnClickListener(this);

        CheckAnsBtn = (Button) findViewById(R.id.CheckAnswerBtnID);
        CheckAnsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openCheckScriptActivity();
            }
        });


//        course_grade_id = (EditText)findViewById(R.id.CourseGradeID) ;
//        student_grade_id = (EditText)findViewById(R.id.StudentGradeID) ;
//        grade = (EditText)findViewById(R.id.GradeID);
//
//        SubmitGradeBtn = (Button)findViewById(R.id.submitGradeBtnID);
//        SubmitGradeBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        String Course_Id = course_id.getText().toString();
        String Course_Name = course_name.getText().toString();
        String Credit = credit.getText().toString();
        String Course_outcome = course_outcome.getText().toString();
        String Grading = grading.getText().toString();
        String Policy = policy.getText().toString();

        String DeleteID = deleteID.getText().toString();

        if(view.getId()==R.id.saveBtn){
           long rowId =   courseOutlineHelper.insertData(Course_Id, Course_Name, Credit, Course_outcome, Grading, Policy);
            if(rowId>0){
                Toast.makeText(getApplicationContext(), "Record "+rowId+" has been inserted!",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "OOPs! Record was not inserted!",Toast.LENGTH_LONG).show();
            }
        }

//        String Course_Grade_Id = course_grade_id.getText().toString();
//        String Student_Grade_Id = student_grade_id.getText().toString();
//        String Grade = grade.getText().toString();
//
//        if(view.getId()==R.id.submitGradeBtnID){
//            long rowId =   gradeHelperClass.insertData(Course_Grade_Id, Student_Grade_Id, Grade);
//            if(rowId>0){
//                Toast.makeText(getApplicationContext(), "Record "+rowId+" has been inserted!",Toast.LENGTH_LONG).show();
//            }
//            else{
//                Toast.makeText(getApplicationContext(), "OOPs! Record was not inserted!",Toast.LENGTH_LONG).show();
//            }
//        }

//        if(view.getId()==R.id.CheckAnswerBtnID){ //CheckAnswerBtnID
//            Cursor answerset = answerHelperClass.displayAllData();
//
//            if(answerset.getCount()==0){
//                showData("Error","No Data Found!");
//                return;
//            }
//            else{
//                StringBuffer stringBufferAns = new StringBuffer();
//                while(answerset.moveToNext()){
//                    stringBufferAns.append("Sno :"+answerset.getString(0) +"\n");
//                    stringBufferAns.append("Course ID :"+answerset.getString(1) +"\n");
//                    stringBufferAns.append("Student ID :"+answerset.getString(2) +"\n");
//                    stringBufferAns.append("Answer 1 :"+answerset.getString(3) +"\n");
//                    stringBufferAns.append("Answer 2 :"+answerset.getString(4) +"\n");
//                    stringBufferAns.append("Answer 3 :"+answerset.getString(5) +"\n");
//                    stringBufferAns.append("Answer 4 :"+answerset.getString(6) +"\n");
//                    stringBufferAns.append("Answer 5 :"+answerset.getString(7) +"\n \n");
//                }
//                showAnswer("All Exam Answers", stringBufferAns.toString());
//            }
//        }

        if(view.getId()==R.id.deleteCourseOutlineBtn){
            int value = courseOutlineHelper.deleteData(DeleteID);
            if(value>0){
                Toast.makeText(getApplicationContext(), "Data was Deleted!",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Data was not Deleted!",Toast.LENGTH_LONG).show();
            }
        }

        if(view.getId()==R.id.viewCourseOutlineBtn){ //View Course Outline
            Cursor resultset = courseOutlineHelper.displayAllData();

            if(resultset.getCount()==0){
                showData("Error","No Data Found!");
                return;
            }
            else{
                StringBuffer stringBuffer = new StringBuffer();
                while(resultset.moveToNext()){
                    stringBuffer.append("Sno "+resultset.getString(0) +"\n");
                    stringBuffer.append("Course ID "+resultset.getString(1) +"\n");
                    stringBuffer.append("Course Name "+resultset.getString(2) +"\n");
                    stringBuffer.append("Credit "+resultset.getString(3) +"\n");
                    stringBuffer.append("Course Outline "+resultset.getString(4) +"\n");
                    stringBuffer.append("Grading "+resultset.getString(5) +"\n");
                    stringBuffer.append("Policy "+resultset.getString(6) +"\n \n");
                }
                showData("All Course Outline", stringBuffer.toString());
            }
        }

    }
    public void showData(String title, String message){ //View Course Outline
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.show();
    }

    public void showAnswer(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.show();
    }

    public void openQuestionActivity(){
        Intent intent1 = new Intent(this, QuestionActivity.class);
        startActivity(intent1);
    }

    public void openCheckScriptActivity(){
        Intent intent2 = new Intent(this, CheckScriptsActivity.class);
        startActivity(intent2);
    }
}