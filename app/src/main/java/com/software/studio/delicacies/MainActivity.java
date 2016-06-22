package com.software.studio.delicacies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button login_button;
    private EditText passwordtext;
    private static SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_button = (Button) findViewById(R.id.login_button);
        passwordtext = (EditText) findViewById(R.id.password_text);
        login_button.setOnClickListener(this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        this.finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        settings = getSharedPreferences("Preference",0);
        if(MainActivity.getPref() == null || !MainActivity.getPref().getBoolean("needPassword", false)) {
            startActivity(new Intent(MainActivity.this, FragmentTagsActivity.class));
        }
    }

    @Override
    public void onClick(View v) {
        if(v == login_button){
            String settedpassword = MainActivity.getPref().getString("password", null).toString();
            Log.d("pass", settedpassword);
            if(settedpassword != null && settedpassword.equals(passwordtext.getText().toString())){
                startActivity(new Intent(MainActivity.this, FragmentTagsActivity.class));
            }
            else {
                passwordtext.setText("");
                Toast.makeText(getApplicationContext(), R.string.msg_WrongPassword, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static SharedPreferences getPref(){
        return settings;
    }


}
