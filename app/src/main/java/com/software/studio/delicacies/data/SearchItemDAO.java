package com.software.studio.delicacies.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SearchItemDAO {
    // 表格名稱
    public static final String TABLE_NAME = "Search_record";

    // item id
    public static final String KEY_ID = "_id";

    // Other column
    public static final String COLUMN_NAME = "name";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT NOT NULL)";

    private SQLiteDatabase database;

    public SearchItemDAO(Context context) {
        database = DelicaciesDBHelper.getDatabase(context);
    }

    public void close() {
        database.close();
    }

    public SearchItem insert(SearchItem item) {
        // Data ContentValue
        ContentValues cv = new ContentValues();

        // Add cv datas
        cv.put(COLUMN_NAME, item.getName());

        // insert and get item id
        long id = database.insert(TABLE_NAME, null, cv);
        // 設定Item在Database的編號
        item.setId(id);

        return item;
    }

    public boolean update(SearchItem item) {
        // Data ContentValue
        ContentValues cv = new ContentValues();

        // Add cv datas
        cv.put(COLUMN_NAME, item.getName());

        // 修改資料的條件為編號
        // Format : 欄位名稱=資料
        String where = KEY_ID + "=" + item.getId();

        // 修改資料並回傳修改的資料數量是否成功
        return database.update(TABLE_NAME, cv, where, null) > 0;
    }

    // 讀取所有資料
    public ArrayList<SearchItem> getAll(){
        ArrayList<SearchItem> result = new ArrayList<>();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null, null);

        while(cursor.moveToNext()){
            result.add(getSearchItem(cursor));
        }

        cursor.close();
        return result;
    }

    public SearchItem getSearchItem(Cursor cursor) {
        SearchItem result = new SearchItem();

        // 將 Cursor 包成 Item
        result.setId(cursor.getLong(0));
        result.setName(cursor.getString(1));

        return result;
    }

    // 取得指定編號的資料物件
    public SearchItem get(long id) {
        SearchItem item = null;

        // 使用編號為查詢條件
        String where = KEY_ID + "=" + id;

        Cursor result = database.query(TABLE_NAME, null, where, null, null, null, null, null);

        if(result.moveToFirst()){
            item = getSearchItem(result);
        }

        result.close();

        return item;
    }

    // 取得資料數量
    public int getCount() {
        int result = 0;
        Cursor cursor = database.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if(cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

    public void clearDatabase() {
        database.execSQL("delete from "+ TABLE_NAME);
    }
}
