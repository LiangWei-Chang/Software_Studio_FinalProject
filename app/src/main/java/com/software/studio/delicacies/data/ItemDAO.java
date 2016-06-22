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

    public void ConstructDelicacies(){
        DelicaciesItem item1 = new DelicaciesItem(0, "曼托瓦義式廚房", "03-5267650", "新竹市東區中華路二段312巷1號", 8,
                "焗烤類很好吃\n義大利麵看個人口味\n不時推出豐富划算的雙人套餐", "11:00 - 21:00 (週一公休)", 0);

        DelicaciesItem item2 = new DelicaciesItem(0, "三層山", "03-5710973", "新竹市東區光復路二段100號", 9,
                "冰淇淋口味每天都不一樣\n非常好吃", "11:00 - 21:00", 0);

        DelicaciesItem item3 = new DelicaciesItem(0, "宮原眼科", "04-22271927", "台中市中區中山路20號", 10,
                "冰淇淋超好吃\n小編們大推\n只是排隊時間很久", "10:00 - 22:00", 0);

        DelicaciesItem item4 = new DelicaciesItem(0, "慢食堂日式剉冰", "03-3362667", "桃園區鎮三街52號", 9,
                "比起抹茶更愛他的焙茶冰\n微微苦澀不死甜的焙茶冰淇淋加上Q彈的白玉跟茶凍\n和甜而不膩的紅豆泥結合的恰到好處~\n季節限定的水果冰也很特別\n店裡賣好很的考糰子一定要配麥茶", "12:00 - 售完為止", 0);

        DelicaciesItem item5 = new DelicaciesItem(0, "鼎泰豐", "02-23218928", "台北市信義路二段194號", 10,
                "餃子類充滿湯汁\n餃子皮的手工細膩", "週一至週五 10:00 - 21:00\n週六例假日 09:00 - 21:00", 0);

        DelicaciesItem item6 = new DelicaciesItem(0, "高雄丹丹漢堡", "03-5330573", "高雄市鼓山區臨海二路24號", 9,
                "No Comment", "06:40 - 22:00", 0);

        DelicaciesItem item7 = new DelicaciesItem(0, "台南元氣果汁", "06-2213875", "台南市安平區安平路53號", 9,
                "No Comment", "11:00 - 售完為止 (最晚 18:00)", 0);

        DelicaciesItem item8 = new DelicaciesItem(0, "台南幽靈炒飯", "0929-211-860", "水木", 10,
                "No Comment", "22:00 - 02:00 (週三公休)", 0);

        DelicaciesItem item9 = new DelicaciesItem(0, "高雄楠梓區-北方麵食", "07-3551192", "高雄市楠梓區立民路2號", 8,
                "蔥油餅、煎餃、拌麵都很好吃，價格實在", "週一到週六 11:00 - 14:00 、 17:00 - 20:00", 0);


        DelicaciesItem item10 = new DelicaciesItem(0, "WOW 瓦屋餐廚美式餐廳", "03-5332757", "新竹市東區中央路342號", 8,
                "No Comment", "10:00 - 14:30 、 17:00 - 22:00", 0);

        DelicaciesItem item11 = new DelicaciesItem(0, "Elvis Diner 貓王餐廳", "03-5726420", "新竹市東區東勝路5號", 9,
                "No Comment", "週二至週五 10:30 - 22:00\n週六週日 10:00 - 22:00", 0);

        DelicaciesItem item12 = new DelicaciesItem(0, "高雄楠梓楊寶寶", "07-3650136", "高雄市楠梓區朝明路106號", 10,
                "人有點多但出餐速度還不錯，餃類都很有水準\n特推烙餅沾玉米濃湯!!!", "11:00 - 14:00 、 16:00 - 21:00", 0);

        insert(item1);
        insert(item2);
        insert(item3);
        insert(item4);
        insert(item5);
        insert(item6);
        insert(item7);
        insert(item8);
        insert(item9);
        insert(item10);
        insert(item11);
        insert(item12);
    }

    public void clearDatabase() {
        database.execSQL("delete from "+ TABLE_NAME);
    }
}
