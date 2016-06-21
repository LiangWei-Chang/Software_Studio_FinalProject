package com.software.studio.delicacies.fragments.wheels;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.software.studio.delicacies.R;

public class wheel_wind_cloud extends AppCompatActivity {
    private Button start_button;
    private ImageView wheel;
    private RotateAnimation animation;
    private  float rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel_wind_cloud);

        // Set Wheel and button
        wheel = (ImageView) findViewById(R.id.img_wheel_wind_cloud);
        wheel.setImageResource(R.drawable.wheel_wind_cloud);

        start_button = (Button) findViewById(R.id.spin_button_wind_cloud);
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float turn = (float)(Math.random() * 360f);
                final int which = (int)(turn / 45f);
                final View views = v;
                rand = turn + 360f * 3;
                Log.d("rotate",new Float(rand).toString());
                animation = new RotateAnimation(0f, rand, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Toast.makeText(views.getContext(),new Integer(which).toString(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                animation.setDuration((long)1500);
                animation.setFillAfter(true);
                wheel.startAnimation(animation);
            }
        });
    }
}
