package sg.edu.rp.c346.id20045435.wishlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "wishList.db";
    private static final int DATABASE_VERSION =1;
    private static final String TABLE_LIST = "list";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_WISH = "wish";
    private static final String COLUMN_RATING = "wantRating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSQL = "CREATE TABLE " + TABLE_LIST + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_WISH + " TEXT," + COLUMN_RATING + " REAL )";
        db.execSQL(createSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);
        onCreate(db);
    }

    public void insertWishList(String wish, float wantRating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_WISH, wish);
        values.put(COLUMN_RATING, wantRating);
        db.insert(TABLE_LIST, null, values);
        db.close();
    }

    public ArrayList<List> getLists() {
        ArrayList<List> lists = new ArrayList<List>();
        String selectQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_WISH + ", " + COLUMN_RATING + " FROM " + TABLE_LIST;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String wish = cursor.getString(1);
                float rating = cursor.getFloat(2);
                List obj = new List(id, wish, rating);
                lists.add(obj);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lists;
    }

    public ArrayList<List> getLists(String keyword) {
        ArrayList<List> lists = new ArrayList<List>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_WISH, COLUMN_RATING};
        String conditions = COLUMN_WISH + " Like ?";
        String[] args = { "%" + keyword + "%"};

        Cursor cursor = db.query(TABLE_LIST, columns, conditions, args,
        null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String wish = cursor.getString(1);
                float rating = cursor.getFloat(2);
                List obj = new List(id, wish, rating);
                lists.add(obj);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lists;
    }
}
