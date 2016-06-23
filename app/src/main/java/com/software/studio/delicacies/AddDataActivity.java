package com.software.studio.delicacies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.software.studio.delicacies.data.DelicaciesItem;
import com.software.studio.delicacies.data.ItemDAO;

public class AddDataActivity extends AppCompatActivity {
    EditText name, tel, location, rating, comment, opentime;
    String add_name, add_tel, add_location, add_comment, add_opentime;
    Integer add_rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        name = (EditText) findViewById(R.id.Add_name);
        tel = (EditText) findViewById(R.id.Add_tel);
        location = (EditText) findViewById(R.id.Add_location);
        rating = (EditText) findViewById(R.id.Add_rating);
        comment = (EditText) findViewById(R.id.Add_comment);
        opentime = (EditText) findViewById(R.id.Add_opentime);
        Button Yes = (Button) findViewById(R.id.Add_yes);
        Button No = (Button) findViewById(R.id.Add_no);

        assert Yes != null;
        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("")){
                    add_name = "Unknown";
                }
                else{
                    add_name = name.getText().toString();
                }
                if(tel.getText().toString().equals("")){
                    add_tel = "Unknown";
                }
                else{
                    add_tel = tel.getText().toString();
                }
                if(location.getText().toString().equals("")){
                    add_location = "Unknown";
                }
                else{
                    add_location = location.getText().toString();
                }
                if(rating.getText().toString().equals("")){
                    add_rating = 0;
                }
                else{
                    add_rating = Integer.parseInt(rating.getText().toString());
                }
                if(comment.getText().toString().equals("")){
                    add_comment = "Unknown";
                }
                else{
                    add_comment = comment.getText().toString();
                }
                if(opentime.getText().toString().equals("")){
                    add_opentime = "Unknown";
                }
                else{
                    add_opentime = opentime.getText().toString();
                }

                ItemDAO myDelicacies = new ItemDAO(getApplicationContext());
                DelicaciesItem item = new DelicaciesItem(0, add_name, add_tel, add_location, add_rating, add_comment, add_opentime, 0);
                myDelicacies.insert(item);
            }
        });

        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
