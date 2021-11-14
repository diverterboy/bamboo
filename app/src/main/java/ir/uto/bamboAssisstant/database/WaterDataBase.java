package ir.uto.bamboAssisstant.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class WaterDataBase extends SQLiteOpenHelper {
    public static final String DB_WATER_NAME = "water.db";
    private static final int RECORD_VERSION = 1;
    public static final String TABLE_WATER = "tbl_water";

    public WaterDataBase(@Nullable Context context) {
        super(context, DB_WATER_NAME, null, RECORD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + TABLE_WATER + "(id Integer PRIMARY KEY autoincrement ,drankWater Integer, date varchar (15) )";
        sqLiteDatabase.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
