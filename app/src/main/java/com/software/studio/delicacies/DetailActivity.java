package com.software.studio.delicacies;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;

import com.software.studio.delicacies.data.DelicaciesItem;
import com.software.studio.delicacies.data.ItemDAO;

import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity {
    TextView title,tel,location,comment,opentime;
    int name;
    ArrayList<DelicaciesItem> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        name = getIntent().getIntExtra("name",0);

        ItemDAO itemDAO = new ItemDAO(this);
        list = itemDAO.getAll();
        title = (TextView)this.findViewById(R.id.detail_name);
        tel = (TextView)this.findViewById(R.id.detail_tel);
        location = (TextView)this.findViewById(R.id.detail_location);
        comment = (TextView)this.findViewById(R.id.detail_comment);
        opentime = (TextView)this.findViewById(R.id.detail_opentime);

        title.setText(list.get(name).getName());
        location.setText("地址:"+list.get(name).getLocation());
        tel.setText("電話:   "+list.get(name).getTelnumber());
        comment.setText("相關評論:\n"+list.get(name).getComment());
        opentime.setText("營業時間: "+list.get(name).getOpentime());

        /*
        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+tel.getText().toString()));
                startActivity(intent);

            }

        });*/

    }
}
