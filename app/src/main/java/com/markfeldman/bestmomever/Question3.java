package com.markfeldman.bestmomever;


import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Question3 extends Fragment{
    private int mainProgress;
    private OnClickedListener listener;
    private MediaPlayer mp;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a;

        if (context instanceof Activity){
            a=(Activity) context;
            this.listener = (OnClickedListener)a;
        }
    }


    public Question3() {
        mainProgress = 0;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_question3, container, false);
        mp = MediaPlayer.create(getActivity(),R.raw.moanna);
        mp.start();
        SeekBar seekBar = (SeekBar)view.findViewById(R.id.seekBar);
        final TextView seekBarProgress = (TextView)view.findViewById(R.id.seekBarProgress);
        final ImageView nextImage = (ImageView)view.findViewById(R.id.questionThreeNextArrow);
        final TextView questionThree = (TextView)view.findViewById(R.id.questionThree);
        final Button submitButton = (Button)view.findViewById(R.id.question_three_button);
        final ImageView wrong_one = (ImageView)view.findViewById(R.id.question_three_wrong1);
        final ImageView wrong_two = (ImageView)view.findViewById(R.id.question_three_wrong2);

        seekBar.setMax(1000000);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String barProgress = Integer.toString(progress);
                mainProgress = progress;
                seekBarProgress.setText(barProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainProgress!=1000000){
                    playSound(mainProgress);
                    questionThree.setText(R.string.question_three);
                    nextImage.setVisibility(view.INVISIBLE);
                    wrong_one.setVisibility(view.VISIBLE);
                    wrong_two.setVisibility(view.VISIBLE);
                    Toast.makeText(getActivity(),"No.",Toast.LENGTH_LONG).show();
                }else{
                    playSound(mainProgress);
                    questionThree.setText(R.string.question_three_answer);
                    wrong_one.setVisibility(view.INVISIBLE);
                    wrong_two.setVisibility(view.INVISIBLE);
                    nextImage.setVisibility(view.VISIBLE);
                }
            }
        });

        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.buttonClicked(Question3.this,v);
            }
        });

        return view;
    }

    public void playSound(int progress){
        if(progress!=1000000){
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
        }else {

                mp.stop();
                mp.release();
                mp = MediaPlayer.create(getActivity(),R.raw.yay);
                mp.start();
            }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mp.stop();
        mp.release();
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mp!=null)
        {
            mp.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mp.pause();
    }
}
