package com.markfeldman.bestmomever;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Question1 extends Fragment  {
    private OnClickedListener listener;
    private MediaPlayer mp;
    private boolean didRotate = false;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a;

        if (context instanceof Activity){
            a=(Activity) context;
            this.listener = (OnClickedListener)a;
        }
    }

    public Question1() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            didRotate = savedInstanceState.getBoolean("flip");
        }
        final View view = inflater.inflate(R.layout.fragment_question1, container, false);
        mp = MediaPlayer.create(getActivity(),R.raw.moanna);
        mp.start();
        RadioGroup radioGroup = (RadioGroup)view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch(checkedId){
                    case R.id.radio_black:{
                            wrongAnswer(view);
                            playSound(R.id.radio_black);
                            break;
                        }
                    case R.id.radio_white:
                        wrongAnswer(view);
                        playSound(R.id.radio_white);
                        break;
                    case R.id.radio_no_pants:
                        rightAnswer(view);
                        playSound(R.id.radio_no_pants);
                        break;
                }

            }
        });

        return view;
    }

    public void wrongAnswer(View view){
        ImageView nextImage = (ImageView)view.findViewById(R.id.questionOneNextArrow);
        ImageView image = (ImageView)view.findViewById(R.id.pantsImage);
        final TextView questionOne = (TextView)view.findViewById(R.id.questionOne);
        questionOne.setText(R.string.question_one);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            ImageView wrong1 = (ImageView)view.findViewById(R.id.question1_landscape_wrong1);
            ImageView wrong2 = (ImageView)view.findViewById(R.id.question1_landscape_wrong2);
            wrong1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.no_mom_no, null));
            wrong2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.no_mom_no, null));
            wrong1.setVisibility(view.VISIBLE);
            wrong2.setVisibility(view.VISIBLE);
            image.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.no_mom_no, null));
        }else{
            image.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.no_mom_no, null));
        }
        nextImage.setVisibility(view.INVISIBLE);
    }

    public void rightAnswer(View view){
        final TextView questionOne = (TextView)view.findViewById(R.id.questionOne);
        questionOne.setText(R.string.question_one_answer);
        ImageView nextImage = (ImageView)view.findViewById(R.id.questionOneNextArrow);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            ImageView image = (ImageView)view.findViewById(R.id.pantsImage);
            ImageView wrong1 = (ImageView)view.findViewById(R.id.question1_landscape_wrong1);
            ImageView wrong2 = (ImageView)view.findViewById(R.id.question1_landscape_wrong2);
            wrong1.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.no_pants, null));
            wrong2.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.no_pants, null));
            wrong1.setVisibility(view.VISIBLE);
            wrong2.setVisibility(view.VISIBLE);
            image.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.no_pants, null));
        }else{
            ImageView image = (ImageView)view.findViewById(R.id.pantsImage);
            image.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.no_pants, null));
        }
        nextImage.setVisibility(view.VISIBLE);
        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.buttonClicked(Question1.this,v);
            }
        });
    }

    public void playSound(int id){
        if(id==R.id.radio_black || id== R.id.radio_white){
            if(!didRotate){
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(getActivity(),R.raw.pity);
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {

                        mp.release();
                        mp = MediaPlayer.create(getActivity(),R.raw.moanna);
                        mp.start();

                    }
                });
                Toast.makeText(getActivity(),"No Mom. No.",Toast.LENGTH_LONG).show();

            }
        }else {
            if (!didRotate){
                mp.stop();
                mp.release();
                mp = MediaPlayer.create(getActivity(),R.raw.yay);
                mp.start();
            }
        }
        didRotate = false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mp.stop();
        mp.release();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        didRotate = true;
        outState.putBoolean("flip",didRotate);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mp!=null)
        {
            mp.start();
        }
        didRotate = false;
    }

    @Override
    public void onPause() {
        super.onPause();
        didRotate = false;
        mp.pause();
    }
}
