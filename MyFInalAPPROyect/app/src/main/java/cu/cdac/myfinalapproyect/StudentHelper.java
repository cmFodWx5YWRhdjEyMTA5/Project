package cu.cdac.myfinalapproyect;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "student";
    private static final String DATABASE_NAME = "finaldb";
    private static final String KEY_NAME = "name";
    private static final String KEY_FATHERNAME = "fathername";
    private static final String KEY_MOTHERNAME = "mothername";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_ID = "id";
    private static final String USER_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + KEY_NAME + " TEXT, " +
            KEY_FATHERNAME + " TEXT, " + KEY_MOTHERNAME + " TEXT, " + KEY_ADDRESS + " TEXT," + KEY_PHONE + " TEXT, " + KEY_LOCATION + " TEXT );";
    String COLUMNS[] = {KEY_ID, KEY_NAME, KEY_FATHERNAME, KEY_MOTHERNAME, KEY_ADDRESS, KEY_PHONE, KEY_LOCATION};

    public StudentHelper(Context context) {
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

    public void addStudents(String name, String fatherName, String motherName, String address, String phone, String location) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_FATHERNAME, fatherName);
        contentValues.put(KEY_MOTHERNAME, motherName);
        contentValues.put(KEY_ADDRESS, address);
        contentValues.put(KEY_PHONE, phone);
        contentValues.put(KEY_LOCATION, location);
        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

//    public List<User> getAllPersonList() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "select * from " + TABLE_NAME;
//        Cursor cursor = db.rawQuery(query, null);
//        List<User> personList = new ArrayList<>();
//        if (cursor != null) {
//            cursor.moveToFirst();
//
//            while (cursor.moveToNext()) {
//                User p = new User();
//                p.setId(Integer.parseInt(cursor.getString(0)));
//                p.setUserName(cursor.getString(1));
//                p.setEmail(cursor.getString(2));
//                p.setPassword(cursor.getString(3));
//                personList.add(p);
//            }
//
//        }
//        return personList;
//    }

//    public Boolean getUserByEmail(String email, String passw) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//
//        Cursor cursor = db.query(TABLE_NAME,
//                COLUMNS,
//                "email = ?",
//                new String[]{email},
//                null, null, null, null);
//
//        if (cursor != null) {
//
//            cursor.moveToFirst();
//            String pass = cursor.getString(3);
//
//            if (pass.equals(passw)) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//
//        return false;
//
//
//    }
}
