package com.markfeldman.bestmomever;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * Created by markfeldman on 4/30/17.
 */

public class Splashscreen extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(3000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);

        findViewById(R.id.introPic).startAnimation(rotateAnimation);

        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(7000);

                }catch(Exception e){
                    e.printStackTrace();
                }finally {
                    Intent i = new Intent(Splashscreen.this,MainActivity.class);
                    startActivity(i);
                }
            }

        };

        timer.start();
    }
}
