package com.software.studio.delicacies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DelicaciesDBHelper extends SQLiteOpenHelper{

    // Database name
    static final String DATABASE_NAME = "delicacies.db";
    // Database version
    private static final int DATABASE_VERSION = 1;
    // Database Object
    private static SQLiteDatabase database;

    public DelicaciesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteDatabase getDatabase(Context context) {
        if( database == null || !database.isOpen()) {
            database = new DelicaciesDBHelper(context).getWritableDatabase();
        }
        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 建立App所需要的表格
        db.execSQL(ItemDAO.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 刪除原本表格
        db.execSQL("DROP TABLE IF EXISTS " + ItemDAO.TABLE_NAME);
        // 建立新表格
        onCreate(db);
    }
}
