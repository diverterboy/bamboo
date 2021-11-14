package ir.uto.bamboAssisstant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.Nullable;

import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

public class WaterDbAdapter extends WaterDataBase {
    public static final String KEY_STRING_DATE = "date";
    public static final String KEY_ID = "id";
    public static final String KEY_DRUNK_WATER = "drankWater";

    public WaterDbAdapter(@Nullable Context context) {
        super(context);
    }

    public Boolean checkWater(String currentPershianDare) {
        Boolean aLong = false;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_WATER, null);

        while (cursor.moveToNext()) {

            if (cursor.getString(cursor.getColumnIndex(KEY_STRING_DATE)).equals(currentPershianDare)) {
                aLong = true;
            }
        }
        return aLong;
    }

    public void addWater(String currentPershianDate) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_STRING_DATE, currentPershianDate);
        contentValues.put(KEY_DRUNK_WATER, 1);
        db.insert(TABLE_WATER, null, contentValues);
    }

    public void updateWater(String currentPershianDate) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.rawQuery("select * from " + TABLE_WATER, null);
        String stringDate = "";
        while (cursor.moveToNext()) {
            stringDate = cursor.getString(cursor.getColumnIndex(KEY_STRING_DATE));
            if (stringDate.equals(currentPershianDate)) {
                int i = cursor.getInt(cursor.getColumnIndex(KEY_DRUNK_WATER)) + 1;
                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                contentValues.put(KEY_DRUNK_WATER, i);
                db.update(TABLE_WATER, contentValues, KEY_ID + "=" + id, null);
            }
        }

    }

    public int getDayWater(String currentPershianDare) {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_WATER, null);
        int water = 0;
        while (cursor.moveToNext()) {

            if (cursor.getString(cursor.getColumnIndex(KEY_STRING_DATE)).equals(currentPershianDare)) {
                water = cursor.getInt(cursor.getColumnIndex(KEY_DRUNK_WATER));
            }
        }
        return water;
    }

}

