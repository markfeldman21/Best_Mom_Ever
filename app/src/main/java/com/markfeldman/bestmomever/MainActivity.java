package com.markfeldman.bestmomever;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements OnClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new WelcomePage())
                    .commit();
        }
    }

    @Override
    public void buttonClicked(Fragment fragment, View v) {

        switch (v.getId()){
            case R.id.playButton:{
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new Question1())
                        .commit();
                break;
            }
            case R.id.questionOneNextArrow:{
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new Question2())
                        .commit();
                break;
            }
            case R.id.questionTwoNextArrow:{
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new Question3())
                        .commit();
                break;
            }
            case R.id.questionThreeNextArrow:{
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new FinalScreen())
                        .commit();
                break;
            }
            case R.id.play_again_button:{
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new WelcomePage())
                        .commit();
                break;
            }
        }
    }
}
