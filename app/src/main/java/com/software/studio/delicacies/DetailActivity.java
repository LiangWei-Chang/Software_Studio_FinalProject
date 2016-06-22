package com.software.studio.delicacies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.software.studio.delicacies.data.DelicaciesItem;
import com.software.studio.delicacies.data.ItemDAO;

import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity {
    TextView title,tel,location,comment,opentime;
    ImageButton button_favorite;
    int name;
    ArrayList<DelicaciesItem> list;
    DelicaciesItem item;
    ArrayList<Integer> Ratings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        name = getIntent().getIntExtra("name",0);

        final ItemDAO itemDAO = new ItemDAO(this);
        list = itemDAO.getAll();

        title = (TextView)this.findViewById(R.id.detail_name);
        tel = (TextView)this.findViewById(R.id.detail_tel);
        location = (TextView)this.findViewById(R.id.detail_location);
        comment = (TextView)this.findViewById(R.id.detail_comment);
        opentime = (TextView)this.findViewById(R.id.detail_opentime);
        button_favorite = (ImageButton) findViewById(R.id.button_favorite);

        Ratings.add(R.id.rating1);
        Ratings.add(R.id.rating2);
        Ratings.add(R.id.rating3);
        Ratings.add(R.id.rating4);
        Ratings.add(R.id.rating5);
        Ratings.add(R.id.rating6);
        Ratings.add(R.id.rating7);
        Ratings.add(R.id.rating8);
        Ratings.add(R.id.rating9);
        Ratings.add(R.id.rating10);

        item = list.get(name);
        if(item.getFavorite() == 1) {
            button_favorite.setImageResource(R.drawable.favorite_on);
        }

        ImageView star;
        for(int i=0; i<item.getRating(); i++){
            star = (ImageView) findViewById(Ratings.get(i));
            star.setImageResource(R.drawable.rating_on);
        }

        title.setText(item.getName());
        location.setText("地址 : " + item.getLocation());
        tel.setText("電話 : " + item.getTelnumber());
        comment.setText("相關評論 : \n" + item.getComment());
        opentime.setText("營業時間 : " + item.getOpentime());

        button_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getFavorite() == 0) {
                    button_favorite.setImageResource(R.drawable.favorite_on);
                    item.setFavorite(1);
                }
                else if(item.getFavorite() == 1) {
                    button_favorite.setImageResource(R.drawable.favorite_off);
                    item.setFavorite(0);
                }
                itemDAO.update(item);
            }
        });

        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenumber = tel.getText().toString().trim();
                phonenumber.replaceAll("-()","");
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phonenumber));
                startActivity(intent);

            }

        });

    }
}
