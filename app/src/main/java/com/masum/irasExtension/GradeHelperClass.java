package com.masum.irasExtension;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class GradeHelperClass extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Grade_Bank.db";
    private static final String TABLE_NAME = "grade_details";
    private static final String sno = "_sno";
    private static final String COURSE_ID = "Course_ID";
    private static final String STUDENT_ID = "Student_ID";
    private static final String GRADE = "Grade";

    private static final int VERSION_NUMBER = 1;
    private Context context;

    public GradeHelperClass(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            Toast.makeText(context, "OnCreate is called!",Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" ("+sno+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COURSE_ID+" VARCHAR(10), " + ""+STUDENT_ID+" VARCHAR(25), "+GRADE+" VARCHAR(255) );");
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

    public long insertData(String Course_Id, String Student_Id, String Grade){
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_ID, Course_Id);
        contentValues.put(STUDENT_ID, Student_Id);
        contentValues.put(GRADE, Grade);


        long rowID = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return rowID;

    }
    public Cursor displayAllData(){
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();
        Cursor cursor  = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME+" ",null); // cursor interface

        return cursor;
    }
}
