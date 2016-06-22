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


public class DetailActivity extends AppCompatActivity {
    TextView tel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String name = getIntent().getStringExtra("name");
        tel = (TextView)this.findViewById(R.id.tel);
        tel.setText("0975310732");
        tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+tel.getText().toString()));
                startActivity(intent);

            }

        });
        Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
    }
}
