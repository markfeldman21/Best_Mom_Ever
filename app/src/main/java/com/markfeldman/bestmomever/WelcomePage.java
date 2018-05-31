package com.markfeldman.bestmomever;


import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomePage extends Fragment implements OnClickedListener {
    public OnClickedListener listener;
    private MediaPlayer mp;

    public WelcomePage() {
    }

    @Override
    public void buttonClicked(Fragment fragment, View v) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity a;

        if (context instanceof Activity){
            a=(Activity) context;
            this.listener = (OnClickedListener)a;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_welcome_page, container, false);
        mp = MediaPlayer.create(getActivity(),R.raw.ren_intro);
        mp.start();
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(3000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        view.findViewById(R.id.introPic).startAnimation(rotateAnimation);
        ImageView playBtn = (ImageView) view.findViewById(R.id.playButton);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.buttonClicked(WelcomePage.this,v);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(mp!=null) {
            mp.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mp.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mp.stop();
        mp.release();
    }
}
