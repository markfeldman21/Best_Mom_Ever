package com.markfeldman.bestmomever;


import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Question2 extends Fragment {
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

    public Question2() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            didRotate = savedInstanceState.getBoolean("flip");
        }
        final View view = inflater.inflate(R.layout.fragment_question2, container, false);
        mp = MediaPlayer.create(getActivity(),R.raw.moanna);
        mp.start();
        final ImageView image = (ImageView)view.findViewById(R.id.daddyImage);
        final TextView questionTwo = (TextView)view.findViewById(R.id.questionTwo);
        final ImageView nextImage = (ImageView)view.findViewById(R.id.questionTwoNextArrow);
        RadioGroup radioGroup = (RadioGroup)view.findViewById(R.id.radioGroupTwo);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch(checkedId){
                    case R.id.radio_dad:
                        playSound(R.id.radio_dad);
                        questionTwo.setText(R.string.question_two);
                        nextImage.setVisibility(view.INVISIBLE);
                        image.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.no_mom_no, null));
                        break;
                    case R.id.radio_mom:
                        playSound(R.id.radio_mom);
                        image.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.question_two_answer, null));
                        questionTwo.setText(R.string.question_two_answer);
                        nextImage.setVisibility(view.VISIBLE);
                        nextImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                listener.buttonClicked(Question2.this,v);
                            }
                        });
                        break;
                    case R.id.radio_both:
                        playSound(R.id.radio_both);
                        questionTwo.setText(R.string.question_two);
                        nextImage.setVisibility(view.INVISIBLE);
                        image.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.no_mom_no, null));
                        break;
                }

            }
        });


        return view;
    }

    public void playSound(int id){
        if(id==R.id.radio_dad || id== R.id.radio_both){
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
        mp.pause();
    }
}
