package com.software.studio.delicacies.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

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
        ArrayList<DelicaciesItem> Datas = new ArrayList<>();
        Datas.add(new DelicaciesItem(0, "曼托瓦義式廚房", "03-5267650", "新竹市東區中華路二段312巷1號", 8,
                "焗烤類很好吃\n義大利麵看個人口味\n不時推出豐富划算的雙人套餐", "11:00 - 21:00 (週一公休)", 0));

        Datas.add(new DelicaciesItem(0, "三層山", "03-5710973", "新竹市東區光復路二段100號", 9,
                "冰淇淋口味每天都不一樣\n非常好吃", "11:00 - 21:00", 0));

        Datas.add(new DelicaciesItem(0, "宮原眼科", "04-22271927", "台中市中區中山路20號", 10,
                "冰淇淋超好吃\n小編們大推\n只是排隊時間很久", "10:00 - 22:00", 0));

        Datas.add(new DelicaciesItem(0, "慢食堂日式剉冰", "03-3362667", "桃園區鎮三街52號", 9,
                "比起抹茶更愛他的焙茶冰\n微微苦澀不死甜的焙茶冰淇淋加上Q彈的白玉跟茶凍\n和甜而不膩的紅豆泥結合的恰到好處~\n季節限定的水果冰也很特別\n店裡賣好很的考糰子一定要配麥茶", "12:00 - 售完為止", 0));

        Datas.add(new DelicaciesItem(0, "鼎泰豐", "02-23218928", "台北市信義路二段194號", 10,
                "餃子類充滿湯汁\n餃子皮的手工細膩", "週一至週五 10:00 - 21:00\n週六例假日 09:00 - 21:00", 0));

        Datas.add(new DelicaciesItem(0, "高雄丹丹漢堡", "03-5330573", "高雄市鼓山區臨海二路24號", 9,
                "No Comment", "06:40 - 22:00", 0));

        Datas.add(new DelicaciesItem(0, "台南元氣果汁", "06-2213875", "台南市安平區安平路53號", 9,
                "No Comment", "11:00 - 售完為止 (最晚 18:00)", 0));

        Datas.add(new DelicaciesItem(0, "台南幽靈炒飯", "0929211860", "台南市北區長北街1號", 10,
                "No Comment", "22:00 - 02:00 (週三公休)", 0));

        Datas.add(new DelicaciesItem(0, "高雄楠梓區-北方麵食", "07-3551192", "高雄市楠梓區立民路2號", 8,
                "蔥油餅、煎餃、拌麵都很好吃，價格實在", "週一到週六 11:00 - 14:00 \n 17:00 - 20:00", 0));


        Datas.add(new DelicaciesItem(0, "WOW 瓦屋餐廚美式餐廳", "03-5332757", "新竹市東區中央路342號", 8,
                "No Comment", "10:00 - 14:30 、 17:00 - 22:00", 0));

        Datas.add(new DelicaciesItem(0, "Elvis Diner 貓王餐廳", "03-5726420", "新竹市東區東勝路5號", 9,
                "No Comment", "\n週二至週五 10:30 - 22:00\n週六週日 10:00 - 22:00", 0));

        Datas.add(new DelicaciesItem(0, "高雄楠梓楊寶寶", "07-3650136", "高雄市楠梓區朝明路106號", 10,
                "人有點多但出餐速度還不錯，餃類都很有水準\n特推烙餅沾玉米濃湯!!!", "11:00 - 14:00 \n 16:00 - 21:00", 0));

        Datas.add(new DelicaciesItem(0, "旗山老街許家豆花", "0935377258", "高雄市旗山區中山路10號", 8,
                "好吃的傳統豆花，冬天賣湯圓", "15:00 - 22:00", 0));

        Datas.add(new DelicaciesItem(0, "台中忠孝夜市蔡家豆花", "04-22855684", "台中市南區忠孝路111號", 8,
                "豆花好吃，也有賣刈包四神湯，小吃好夥伴", "15:00 - 02:00", 0));

        Datas.add(new DelicaciesItem(0, "台南六千牛肉湯", "06-2227603", "台南市海安路一段63號", 9,
                "湯頭鮮美肉質軟嫩，扣一分是因為要早起排隊...", "5:00 - 售完為止 (週二公休)", 0));

        Datas.add(new DelicaciesItem(0, "新竹老段牛肉麵", "03-5728207", "新竹市建功一路182號", 8,
                "豆花好吃，也有賣刈包四神湯，小吃好夥伴", "15:00 - 02:00", 0));

        Datas.add(new DelicaciesItem(0, "台中忠孝夜市蔡家豆花", "04-22855684", "台中市南區忠孝路111號", 9,
                "肉超軟，湯很好喝很香，麵很特別的是細的那種麵條\n價格又便宜~~~小菜豆干也很好吃", "11:00 - 14:00\n17:00 - 21:00", 0));

        Datas.add(new DelicaciesItem(0, "合味拉麵", "02-27517981", "台北市大安區大安路一段51巷14號", 10,
                "超級好吃", "Unknown", 0));

        Datas.add(new DelicaciesItem(0, "如意紅豆餅", "Unknown", "新竹市東區學府路36號 (竹中口)", 8,
                "料滿不錯吃，沒看過的口味很多", "Unknown", 0));

        Datas.add(new DelicaciesItem(0, "千葉火鍋", "03-5723525", "新竹市東區忠孝路281號", 7,
                "平價的火鍋吃到飽不論平日假日總是客滿，各式各樣的火鍋料還有現作手卷以及現切的新鮮高級肉品絕對讓你值回票價!!!!", "11:00 - 22:00", 0));

        Datas.add(new DelicaciesItem(0, "義式屋古拉爵", "03-5437102", "新竹市東區文化街10號", 9,
                "有套餐吃法和單點吃法，但不論哪種，古拉爵絕對能讓妳吃飽，省錢，又吃到各種不同的美食。義大利麵、披薩、燉飯還有超好吃的牛排、海鮮大餐，各種西方的異國料理CP值絕對超高!!!", "11:00 - 23:00", 0));

        Datas.add(new DelicaciesItem(0, "台南鹽水碳烤雞蛋糕", "Unknown", "台南中山路中正路交叉口", 9,
                "有生以來吃過最好吃的雞蛋糕，一口咬下滿嘴濃濃的雞蛋香，一星期只開一天，排隊排一小時也願意", "12:00 前收攤", 0));

        for(DelicaciesItem item : Datas){
            insert(item);
        }
    }

    public void clearDatabase() {
        database.execSQL("delete from "+ TABLE_NAME);
    }
}
