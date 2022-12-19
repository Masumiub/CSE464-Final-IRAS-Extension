package com.masum.irasExtension;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AnswerHelperClass extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Answer_Bank.db";
    private static final String TABLE_NAME = "answer_details";
    private static final String sno = "_sno";
    private static final String COURSE_ID = "Course_ID";
    private static final String STUDENT_ID = "Student_ID";
    private static final String ANSWER1 = "Answer_1";
    private static final String ANSWER2 = "Answer_2";
    private static final String ANSWER3 = "Answer_3";
    private static final String ANSWER4 = "Answer_4";
    private static final String ANSWER5 = "Answer_5";

    private static final int VERSION_NUMBER = 1;
    private Context context;

    public AnswerHelperClass(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            Toast.makeText(context, "OnCreate is called!",Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" ("+sno+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COURSE_ID+" VARCHAR(10), " + ""+STUDENT_ID+" VARCHAR(25), "+ANSWER1+" VARCHAR(255), "+ANSWER2+" VARCHAR(255), "+ANSWER3+" VARCHAR(255), "+ANSWER4+" VARCHAR(255), "+ANSWER5+" VARCHAR(255) );");
        }
        catch (Exception e){
            Toast.makeText(context, "Exception: "+ e,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        try {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
        catch (Exception e){
            Toast.makeText(context, "Exception: "+ e,Toast.LENGTH_LONG).show();
        }
    }

    public long insertData(String Course_Id, String Student_Id, String Answer_1, String Answer_2, String Answer_3, String Answer_4, String Answer_5){
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_ID, Course_Id);
        contentValues.put(STUDENT_ID, Student_Id);
        contentValues.put(ANSWER1, Answer_1);
        contentValues.put(ANSWER2, Answer_2);
        contentValues.put(ANSWER3, Answer_3);
        contentValues.put(ANSWER4, Answer_4);
        contentValues.put(ANSWER5, Answer_5);

        long rowID = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return rowID;

    }

    public Cursor displayAllData(){
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();
        Cursor cursor  = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME+" ",null); // cursor interface

        return cursor;
    }
}
