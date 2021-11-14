package ir.uto.bamboAssisstant.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RecordDataBase extends SQLiteOpenHelper {
    public static final String DB_RECORD_NAME = "record.db";
    private static final int RECORD_VERSION = 1;
    public static final String TABLE_RECORD = "tbl_record";

    public RecordDataBase(@Nullable Context context) {
        super(context, DB_RECORD_NAME, null, RECORD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table " + TABLE_RECORD + "(id Integer, selectedTime Integer , title varchar(50), stringTime varchar(30), date varchar (15) )";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
