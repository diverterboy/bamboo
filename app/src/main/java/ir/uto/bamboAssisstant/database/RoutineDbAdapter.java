package ir.uto.bamboAssisstant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import ir.uto.bamboAssisstant.modele.MyRoutine;

public class RoutineDbAdapter extends RoutineDataBase {
    public static final String KEY_TITLE = "title";
    public static final String KEY_HOUR_ROUTINE = "hourRoutine";
    public static final String KEY_MIN_ROUTINE = "minRoutine";
    public static final String KEY_ID = "id";
    public static final String KEY_SELECTED_TIME = "selectedTime";
    public static final String KEY_CHOOSEN_TIME = "choosenTime";

    public RoutineDbAdapter(@Nullable Context context) {
        super(context);
    }

    public long addRoutine(MyRoutine routine) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TITLE, routine.getRoutineName());
        contentValues.put(KEY_HOUR_ROUTINE, routine.getHour());
        contentValues.put(KEY_MIN_ROUTINE, routine.getMin());
        contentValues.put(KEY_SELECTED_TIME, routine.getSelectedTime());
        contentValues.put(KEY_CHOOSEN_TIME, routine.getChoosenTime());
        return db.insert(TABLE_LOGGER, null, contentValues);
    }

    public List<String> showAllRoutines() {
        SQLiteDatabase db = getReadableDatabase();

        List<String> routineList = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + TABLE_LOGGER, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String title = cursor.getString(cursor.getColumnIndex(KEY_TITLE));
            String hourRoutine = cursor.getString(cursor.getColumnIndex(KEY_HOUR_ROUTINE));
            String minRoutine = cursor.getString(cursor.getColumnIndex(KEY_MIN_ROUTINE));
            routineList.add(title);
        }
        return routineList;

    }

    public List<MyRoutine> getAllRoutines() {
        SQLiteDatabase db = getReadableDatabase();
        List<MyRoutine> routineList = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + TABLE_LOGGER, null);

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String title = cursor.getString(cursor.getColumnIndex(KEY_TITLE));
            int hourRoutine = cursor.getInt(cursor.getColumnIndex(KEY_HOUR_ROUTINE));
            int minRoutine = cursor.getInt(cursor.getColumnIndex(KEY_MIN_ROUTINE));
            String selectedTime = cursor.getString(cursor.getColumnIndex(KEY_SELECTED_TIME));
            MyRoutine myRoutine = new MyRoutine();

            myRoutine.setRoutineName(title);
            myRoutine.setMin(minRoutine);
            myRoutine.setHour(hourRoutine);
            myRoutine.setId(id);
            myRoutine.setSelectedTime(selectedTime);
            myRoutine.setChoosenTime(cursor.getInt(cursor.getColumnIndex(KEY_CHOOSEN_TIME)));
            routineList.add(myRoutine);
        }
        return routineList;
    }

    public long deleteRoutine(int id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_LOGGER, KEY_ID + "=" + id, null);
    }

}
