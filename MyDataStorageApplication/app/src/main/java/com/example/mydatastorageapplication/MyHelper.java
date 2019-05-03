package com.example.mydatastorageapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NAME = "person";
    private static final String DATABASE_NAME = "persondb";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_LASTNAME = "lastname";
    private static final String COLUMN_QUALIFICATION = "qualification";
    private static final String CREATE_TABLE_PERSON =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_LASTNAME + " TEXT, " +
                    COLUMN_QUALIFICATION + " TEXT);";

    public MyHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERSON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion)
            db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }

    // CRUD
    public void addPerson(Person person) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, person.name);
        values.put(COLUMN_LASTNAME, person.lastname);
        values.put(COLUMN_QUALIFICATION, person.qualification);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Person getPersonById(int id) {
        SQLiteDatabase db = getReadableDatabase();
        /* one way
        String sql = "select * from " + TABLE_NAME + " where " + COLUMN_ID + " = " + id;
        Cursor result = db.rawQuery(sql, null);
        */

        final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_LASTNAME, COLUMN_QUALIFICATION};
        Cursor result = db.query(TABLE_NAME, // a. table name
                COLUMNS, // b. columns names
                " id = ?", // c. selections
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null // h. limit
                );

        if (result != null)
            result.moveToFirst();

        Person person = new Person();
        person.setId(result.getInt(0));
        person.setName(result.getString(1));
        person.setLastname(result.getString(2));
        person.setQualification(result.getString(3));

        result.close();
        db.close();

        return person;
    }

    public List<Person> getAllPerson() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from " + TABLE_NAME;
        //Cursor result = db.rawQuery(sql, null);

        final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_LASTNAME, COLUMN_QUALIFICATION};
        Cursor result = db.query(TABLE_NAME, // a. table name
                COLUMNS, // b. columns names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null // h. limit
        );


        List<Person> list = new ArrayList<Person>();

        if (result != null) {
            while (result.moveToNext()) {
                Person person = new Person();
                person.setId(result.getInt(0));
                person.setName(result.getString(1));
                person.setLastname(result.getString(2));
                person.setQualification(result.getString(3));
                list.add(person);
            }
            result.close();
        }
        db.close();

        return list;
    }
}
