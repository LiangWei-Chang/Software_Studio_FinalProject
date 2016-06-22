package com.software.studio.delicacies.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by PinYo on 2016/6/22.
 */
public class ItemDAO {
    // 表格名稱
    public static final String TABLE_NAME = "Delicacies_List";

    // item id
    public static final String KEY_ID = "_id";

    // Other column
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TEL = "telnumber";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_COMMENT = "comment";
    public static final String COLUMN_OPENTIME = "opentime";
    public static final String COLUMN_FAVORITE = "favorite";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT NOT NULL, " +
            COLUMN_TEL + " TEXT NOT NULL, " +
            COLUMN_LOCATION + " TEXT NOT NULL, " +
            COLUMN_RATING + " INTEGER NOT NULL, " +
            COLUMN_COMMENT + " TEXT, " +
            COLUMN_OPENTIME + " TEXT NOT NULL, " +
            COLUMN_FAVORITE + " INTEGER NOT NULL)";

    private SQLiteDatabase database;

    public ItemDAO(Context context) {
        database = DelicaciesDBHelper.getDatabase(context);
    }

    public void close() {
        database.close();
    }

    public DelicaciesItem insert(DelicaciesItem item) {
        // Data ContentValue
        ContentValues cv = new ContentValues();

        // Add cv datas
        cv.put(COLUMN_NAME, item.getName());
        cv.put(COLUMN_TEL, item.getTelnumber());
        cv.put(COLUMN_LOCATION, item.getLocation());
        cv.put(COLUMN_RATING, item.getRating());
        cv.put(COLUMN_COMMENT, item.getComment());
        cv.put(COLUMN_OPENTIME, item.getOpentime());
        cv.put(COLUMN_FAVORITE, item.getFavorite());

        // insert and get item id
        long id = database.insert(TABLE_NAME, null, cv);
        // 設定Item在Database的編號
        item.setId(id);

        return item;
    }

    public boolean update(DelicaciesItem item) {
        // Data ContentValue
        ContentValues cv = new ContentValues();

        // Add cv datas
        cv.put(COLUMN_NAME, item.getName());
        cv.put(COLUMN_TEL, item.getTelnumber());
        cv.put(COLUMN_LOCATION, item.getLocation());
        cv.put(COLUMN_RATING, item.getRating());
        cv.put(COLUMN_COMMENT, item.getComment());
        cv.put(COLUMN_OPENTIME, item.getOpentime());
        cv.put(COLUMN_FAVORITE, item.getFavorite());

        // 修改資料的條件為編號
        // Format : 欄位名稱=資料
        String where = KEY_ID + "=" + item.getId();

        // 修改資料並回傳修改的資料數量是否成功
        return database.update(TABLE_NAME, cv, where, null) > 0;
    }

    // 讀取所有資料
    public ArrayList<DelicaciesItem> getAll(){
        ArrayList<DelicaciesItem> result = new ArrayList<>();
        Cursor cursor = database.query(TABLE_NAME, null, null, null, null, null, null, null);

        while(cursor.moveToNext()){
            result.add(getDelicaciesItem(cursor));
        }

        cursor.close();
        return result;
    }

    public DelicaciesItem getDelicaciesItem(Cursor cursor) {
        DelicaciesItem result = new DelicaciesItem();

        // 將 Cursor 包成 Item
        result.setId(cursor.getLong(0));
        result.setName(cursor.getString(1));
        result.setTelnumber(cursor.getString(2));
        result.setLocation(cursor.getString(3));
        result.setRating(cursor.getInt(4));
        result.setComment(cursor.getString(5));
        result.setOpentime(cursor.getString(6));
        result.setFavorite(cursor.getInt(7));

        return result;
    }

    // 取得指定編號的資料物件
    public DelicaciesItem get(long id) {
        DelicaciesItem item = null;

        // 使用編號為查詢條件
        String where = KEY_ID + "=" + id;

        Cursor result = database.query(TABLE_NAME, null, where, null, null, null, null, null);

        if(result.moveToFirst()){
            item = getDelicaciesItem(result);
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

    public void sample(){
        DelicaciesItem item = new DelicaciesItem(0, "八方", "0912345678", "水木", 5, "好吃", "0800-1900", 0);
        DelicaciesItem item2 = new DelicaciesItem(0, "姊妹", "09456658", "小吃", 5, "好吃", "0800-1900", 0);

        insert(item);
        insert(item2);
    }

    public void clearDatabase()
    {
        database.execSQL("delete from "+ TABLE_NAME);
    }
}
