package cu.cdac.myfinalapproyect;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SignUpHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "user";
    private static final String DATABASE_NAME = "finaldb";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ID = "id";
    private static final String USER_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + KEY_USERNAME + " TEXT, " +
            KEY_EMAIL + " TEXT, " + KEY_PASSWORD + " TEXT);";
    String COLUMNS[] = {KEY_ID, KEY_USERNAME, KEY_EMAIL, KEY_PASSWORD};

    public SignUpHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE " + TABLE_NAME);
            onCreate(db);
        }
    }

    public void addUser(String name, String email, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_USERNAME, name);
        contentValues.put(KEY_EMAIL, email);
        contentValues.put(KEY_PASSWORD, password);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    public List<User> getAllPersonList() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        List<User> personList = new ArrayList<>();
        if (cursor != null) {
            cursor.moveToFirst();

            while (cursor.moveToNext()) {
                User p = new User();
                p.setId(Integer.parseInt(cursor.getString(0)));
                p.setUserName(cursor.getString(1));
                p.setEmail(cursor.getString(2));
                p.setPassword(cursor.getString(3));
                personList.add(p);
            }

        }
        return personList;
    }

    public Boolean getUserByEmail(String email, String passw) {
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABLE_NAME,
                COLUMNS,
                "email = ?",
                new String[]{email},
                null, null, null, null);

        if (cursor != null) {

            cursor.moveToFirst();
            String pass = cursor.getString(3);

            if (pass.equals(passw)) {
                return true;
            } else {
                return false;
            }
        }

        return false;


    }
}
