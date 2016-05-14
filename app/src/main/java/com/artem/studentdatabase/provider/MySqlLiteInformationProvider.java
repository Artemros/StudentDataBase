package com.artem.studentdatabase.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.artem.studentdatabase.entity.Group;
import com.artem.studentdatabase.entity.Student;

import java.util.ArrayList;

/**
 * Created by denis on 28.02.2016.
 */
public class MySqlLiteInformationProvider extends SQLiteOpenHelper {

    private java.lang.String Students_TABLE_NAME = "Students";
    private static String Students_DATA_BASE = "MyDBName.db";
    public static final String Students_COLUMN_NAME = "Name";
    public static final String Students_COLUMN_SECOUNDNAME = "SecondName";
    public static final String Students_COLUMN_THIRDNAME = "ThirdName";
    public static final String Students_COLUMN_DATEOFBORN = "DateOfBorn";
    public static final String Students_COLUMN_NUMBEROFGROUP = "NumberOfGroup";

    public MySqlLiteInformationProvider(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqlLiteInformationProvider(Context context) {
        super(context, Students_DATA_BASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table Students " +
                        "(id integer primary key, Name text,SecondName text,ThirdName text, DateOfBorn text,NumberOfGroup text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Students");
        onCreate(db);
    }

    public boolean insertStudents(String Name, String SecoundName, String ThirdName, String DateOfBorn, String NumberOfGroup) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", Name);
        contentValues.put("SecondName", SecoundName);
        contentValues.put("ThirdName", ThirdName);
        contentValues.put("DateOfBorn", DateOfBorn);
        contentValues.put("NumberOfGroup", NumberOfGroup);
        db.insert("Students", null, contentValues);
        return true;
    }

    private Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Students where id=" + id + "", null);
        return res;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, Students_TABLE_NAME);
        return numRows;
    }

    public boolean updateStudents(Integer id, String Name, String SecoundName, String ThirdName, String DateOfBorn, String NumberOfGroup) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", Name);
        contentValues.put("SecondName", SecoundName);
        contentValues.put("ThirdName", ThirdName);
        contentValues.put("DateOfBorn", DateOfBorn);
        contentValues.put("NumberOfGroup", NumberOfGroup);
        db.update("Students", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public int deleteStudent(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("Students",
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> array_list = new ArrayList<>();
/*
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Students", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            Student entity = new Student();
            entity.setId(res.getInt(res.getColumnIndex("id")));
            entity.setName(res.getString(res.getColumnIndex("SecondName")));
            array_list.add(entity);
            res.moveToNext();
        }
        res.close();*/
        return array_list;
    }

    public Student getStudentById(Integer id) {
        return null;
    }


    public ArrayList<Group> getAllGroups() {
        ArrayList<Group> array_list = new ArrayList<>();

       /* SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Groups", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            .add(res.getString(res.getColumnIndex("SecondName")));
            res.moveToNext();
        }
        res.close();*/
        return array_list;
    }

    public boolean updateGroups(int value, String s) {
        return false;
    }

    public boolean insertGroups(String s) {
        return false;
    }
}

