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

public class CheckScriptsActivity extends AppCompatActivity implements View.OnClickListener{

    GradeHelperClass gradeHelperClass;
    AnswerHelperClass answerHelperClass;

    private Button CheckAnsBtn;

    private EditText course_grade_id, student_grade_id, grade;
    private Button SubmitGradeBtn;
    private Button ViewAllGradesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_scripts);

        answerHelperClass = new AnswerHelperClass(this);
        SQLiteDatabase sqLiteDatabase3 = answerHelperClass.getWritableDatabase();

        gradeHelperClass = new GradeHelperClass(this);
        SQLiteDatabase sqLiteDatabase4 = gradeHelperClass.getWritableDatabase();

        CheckAnsBtn = (Button) findViewById(R.id.CheckAnswerBtnID);
        CheckAnsBtn.setOnClickListener(this);

        course_grade_id = (EditText)findViewById(R.id.CourseGradeID) ;
        student_grade_id = (EditText)findViewById(R.id.StudentGradeID) ;
        grade = (EditText)findViewById(R.id.GradeID);

        SubmitGradeBtn = (Button)findViewById(R.id.submitGradeBtnID);
        SubmitGradeBtn.setOnClickListener(this);

        ViewAllGradesBtn = (Button)findViewById(R.id.viewAllGradesBtnID);
        ViewAllGradesBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.viewAllGradesBtnID ){ //View all grades
            Cursor gradeset = gradeHelperClass.displayAllData();

            if(gradeset.getCount()==0){
                showGrade("Error","No Data Found!");
                return;
            }
            else{
                StringBuffer stringBufferGrade = new StringBuffer();
                while(gradeset.moveToNext()){
                    stringBufferGrade.append("Sno :"+gradeset.getString(0) +"\n");
                    stringBufferGrade.append("Course ID :"+gradeset.getString(1) +"\n");
                    stringBufferGrade.append("Student ID :"+gradeset.getString(2) +"\n");
                    stringBufferGrade.append("Grade :"+gradeset.getString(3) +"\n \n");
                }
                showGrade("All Student Grades", stringBufferGrade.toString());
            }
        }


        if(view.getId()==R.id.CheckAnswerBtnID){  //CheckAnswerBtnID
            Cursor answerset = answerHelperClass.displayAllData();

            if(answerset.getCount()==0){
                showAnswer("Error","No Data Found!");
                return;
            }
            else{
                StringBuffer stringBufferAns = new StringBuffer();
                while(answerset.moveToNext()){
                    stringBufferAns.append("Sno :"+answerset.getString(0) +"\n");
                    stringBufferAns.append("Course ID :"+answerset.getString(1) +"\n");
                    stringBufferAns.append("Student ID :"+answerset.getString(2) +"\n");
                    stringBufferAns.append("Answer 1 :"+answerset.getString(3) +"\n");
                    stringBufferAns.append("Answer 2 :"+answerset.getString(4) +"\n");
                    stringBufferAns.append("Answer 3 :"+answerset.getString(5) +"\n");
                    stringBufferAns.append("Answer 4 :"+answerset.getString(6) +"\n");
                    stringBufferAns.append("Answer 5 :"+answerset.getString(7) +"\n \n");
                }
                showAnswer("All Exam Answers", stringBufferAns.toString());
            }
        }
        String Course_Grade_Id = course_grade_id.getText().toString();
        String Student_Grade_Id = student_grade_id.getText().toString();
        String Grade = grade.getText().toString();

        if(view.getId()==R.id.submitGradeBtnID){
            long rowId =   gradeHelperClass.insertData(Course_Grade_Id, Student_Grade_Id, Grade);
            if(rowId>0){
                Toast.makeText(getApplicationContext(), "Record "+rowId+" has been inserted!",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "OOPs! Record was not inserted!",Toast.LENGTH_LONG).show();
            }
        }


    }

    public void showAnswer(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.show();
    }

    public void showGrade(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.show();
    }
}