package ir.uto.bamboAssisstant.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RoutineDataBase extends SQLiteOpenHelper {
    public static final String DB_NAME = "logger.db";

    private static final int VERSION = 1;
    public static final String TABLE_LOGGER = "tbl_logger";

    public RoutineDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_LOGGER + "(id Integer PRIMARY KEY autoincrement , title varchar(50),hourRoutine Integer, minRoutine Integer , selectedTime varchar(50) , choosenTime int  )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
