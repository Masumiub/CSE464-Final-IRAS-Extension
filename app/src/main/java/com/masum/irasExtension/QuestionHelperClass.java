package com.masum.irasExtension;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class QuestionHelperClass extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Question_Bank.db";
    private static final String TABLE_NAME = "Question_details";
    private static final String sno = "_sno";
    private static final String COURSE_ID = "Course_ID";
    private static final String QUESTION1 = "Question_1";
    private static final String QUESTION2 = "Question_2";
    private static final String QUESTION3 = "Question_3";
    private static final String QUESTION4 = "Question_4";
    private static final String QUESTION5 = "Question_5";

    private static final int VERSION_NUMBER = 1;
    private Context context;

    public QuestionHelperClass(Context context) {
        super(context, TABLE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            Toast.makeText(context, "OnCreate is called!",Toast.LENGTH_LONG).show();
            sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" ("+sno+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COURSE_ID+" VARCHAR(10), "+QUESTION1+" VARCHAR(255)," + " "+QUESTION2+" VARCHAR(255),"+QUESTION3+" VARCHAR(255), "+QUESTION4+" VARCHAR(255), "+QUESTION5+" VARCHAR(255) );");
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

    public long insertData(String Course_Id, String Question1, String Question2, String Question3, String Question4, String Question5){
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COURSE_ID, Course_Id);
        contentValues.put(QUESTION1, Question1);
        contentValues.put(QUESTION2, Question2);
        contentValues.put(QUESTION3, Question3);
        contentValues.put(QUESTION4, Question4);
        contentValues.put(QUESTION5, Question5);

        long rowID = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        return rowID;

    }

    public Cursor displayAllData(){
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();
        Cursor cursor  = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME+" ",null); // cursor interface

        return cursor;
    }


}
