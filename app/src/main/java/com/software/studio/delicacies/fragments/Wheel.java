package com.software.studio.delicacies.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.software.studio.delicacies.R;

import java.util.Random;

public class Wheel extends Fragment {
    Button start_button;
    ImageView wheel;
    RotateAnimation animation;
    float rand;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wheel, container, false);
        wheel = (ImageView)view.findViewById(R.id.img_wheels);
        wheel.setImageResource(R.drawable.wheel);

        start_button = (Button)view.findViewById(R.id.spin_button);
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float turn = (float)(Math.random()*360f);
               final int which = (int)(turn/30f);
                final View views =v;
                rand = turn+360f*3;
                Log.d("rotate",new Float(rand).toString());
                animation = new RotateAnimation(0f,rand, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
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

        return view;
    }
}
