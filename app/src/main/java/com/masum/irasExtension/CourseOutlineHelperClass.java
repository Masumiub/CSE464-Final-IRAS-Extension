package com.masum.irasExtension;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class CourseOutlineHelperClass extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Course_Outline.db";
    private static final String TABLE_NAME = "course_details";
    private static final String sno = "_sno";
    private static final String COURSE_ID = "Course_ID";
    private static final String COURSE_Name = "Course_Name";
    private static final String CREDIT = "Credit";
    private static final String COURSE_Outcome = "Course_Outcome";
    private static final String GRADING = "Grading";
    private static final String POLICY = "Policy";
    //private static final String CREATE_TABLE = "Policy";

    private static final int VERSION_NUMBER = 1;

    private Context context;
    public CourseOutlineHelperClass(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try{
            Toast.makeText(context, "OnCreate is called!",Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" ("+sno+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COURSE_ID+" VARCHAR(10), " + ""+COURSE_Name+" VARCHAR(25), "+CREDIT+" INTEGER(2), "+COURSE_Outcome+" VARCHAR(255), "+GRADING+" VARCHAR(255), "+POLICY+" VARCHAR(50) );");
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

    public long insertData(String Course_Id, String Course_Name, String Credit, String Course_outcome, String Grading, String Policy){
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_ID, Course_Id);
        contentValues.put(COURSE_Name, Course_Name);
        contentValues.put(CREDIT, Credit);
        contentValues.put(COURSE_Outcome, Course_outcome);
        contentValues.put(GRADING, Grading);
        contentValues.put(POLICY, Policy);

        long rowID = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return rowID;

    }

    public Cursor displayAllData(){
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();
        Cursor cursor  = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME+" ",null); // cursor interface

        return cursor;
    }

    public int deleteData(String SNO){
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, sno +" = ? ", new String[]{ SNO } );
    }
}
