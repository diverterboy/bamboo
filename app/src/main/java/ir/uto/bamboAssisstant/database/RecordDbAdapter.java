package ir.uto.bamboAssisstant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ir.uto.bamboAssisstant.modele.MyRoutine;
import ir.uto.bamboAssisstant.modele.Records;

public class RecordDbAdapter extends RecordDataBase {
    public static final String KEY_ID = "id";
    public static final String KEY_RECORDED_TIME = "selectedTime";
    public static final String KEY_RECORDED_TITLE = "title";
    public static final String KEY_STRING_TIME = "stringTime";
    public static final String KEY_STRING_DATE = "date";


    public RecordDbAdapter(@Nullable Context context) {
        super(context);
    }

    public long addRecord(Records records) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, records.getId());
        contentValues.put(KEY_RECORDED_TIME, records.getRecordedTime());
        contentValues.put(KEY_RECORDED_TITLE, records.getTitle());
        contentValues.put(KEY_STRING_TIME, records.getStringTime());
        contentValues.put(KEY_STRING_DATE, records.getRecordsDate());
        return db.insert(TABLE_RECORD, null, contentValues);
    }


    public Boolean updateRecords(Records records) {
        long recordedTime = 0;
        long totalTime;
        String stringTime;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.rawQuery("select * from " + TABLE_RECORD, null);
        while (cursor.moveToNext()) {

            if (cursor.getInt(cursor.getColumnIndex(KEY_ID)) == records.getId()) {
                recordedTime = cursor.getInt(cursor.getColumnIndex(KEY_RECORDED_TIME));
            }
        }
        totalTime = recordedTime + records.getRecordedTime();

        long hours = totalTime / 3600;
        long minutes = (totalTime % 3600) / 60;
        long secs = totalTime % 60;

        String Time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs);


        contentValues.put(KEY_RECORDED_TIME, totalTime);
        contentValues.put(KEY_STRING_TIME, Time);

        db.update(TABLE_RECORD, contentValues, KEY_ID + "=" + records.getId(), null);
        return true;
    }

    public List<Records> getAllRecords() {
        SQLiteDatabase db = getReadableDatabase();
        List<Records> recordList = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + TABLE_RECORD, null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            long recordedTime = cursor.getInt(cursor.getColumnIndex(KEY_RECORDED_TIME));
            String title = cursor.getString(cursor.getColumnIndex(KEY_RECORDED_TITLE));
            String stringTime = cursor.getString(cursor.getColumnIndex(KEY_STRING_TIME));
            Records records = new Records();

            records.setId(id);
            records.setRecordedTime(recordedTime);
            records.setTitle(title);
            records.setStringTime(stringTime);
            recordList.add(records);
        }
        return recordList;
    }

    public List<Records> getDayRecord(String recordsDate) {
        SQLiteDatabase db = getReadableDatabase();
        List<Records> recordList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_RECORD, null);

        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndex(KEY_STRING_DATE)).equals(recordsDate)) {
                int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
                long recordedTime = cursor.getInt(cursor.getColumnIndex(KEY_RECORDED_TIME));
                String title = cursor.getString(cursor.getColumnIndex(KEY_RECORDED_TITLE));
                String stringTime = cursor.getString(cursor.getColumnIndex(KEY_STRING_TIME));
                Records records = new Records();

                records.setId(id);
                records.setRecordedTime(recordedTime);
                records.setTitle(title);
                records.setStringTime(stringTime);
                recordList.add(records);
            }
        }
        return recordList;
    }

    public long deleteRecord(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_RECORD, KEY_ID + "=" + id, null);
    }

    public Boolean checkRecord(String title, String currentPershianDare) {
        Boolean aLong = false;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_RECORD, null);

        while (cursor.moveToNext()) {

            if (cursor.getString(cursor.getColumnIndex(KEY_RECORDED_TITLE)).equals(title) &&
                    cursor.getString(cursor.getColumnIndex(KEY_STRING_DATE)).equals(currentPershianDare)) {
                aLong = true;
            }
        }
        return aLong;
    }

    public String getEachPercent(String title, String currentPershianDare) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_RECORD, null);
        float myObj = 0;
        float total = 0;
        int i = 0;
        String result = "";
        while (cursor.moveToNext()) {

            if (cursor.getString(cursor.getColumnIndex(KEY_RECORDED_TITLE)).equals(title) &&
                    cursor.getString(cursor.getColumnIndex(KEY_STRING_DATE)).equals(currentPershianDare)) {
                myObj = cursor.getInt(cursor.getColumnIndex(KEY_RECORDED_TIME));
                total = myObj + total;
                i++;

            } else if (cursor.getString(cursor.getColumnIndex(KEY_STRING_DATE)).equals(currentPershianDare)) {
                total = total + cursor.getInt(cursor.getColumnIndex(KEY_RECORDED_TIME));
                i++;
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        float f = (myObj * 100) / total;
        result = String.valueOf(Float.valueOf(decimalFormat.format(f))
        );


        return result;

    }


}
