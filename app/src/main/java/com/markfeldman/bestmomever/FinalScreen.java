package com.markfeldman.bestmomever;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class FinalScreen extends Fragment {
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



    public FinalScreen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_final_screen, container, false);
        mp = MediaPlayer.create(getActivity(),R.raw.the_best);
        mp.start();
        ImageView play = (ImageView)view.findViewById(R.id.playButton);
        Button playAgain = (Button)view.findViewById(R.id.play_again_button);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
                watchYoutubeVideo("zKbcGEu-im4");
            }
        });
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.buttonClicked(FinalScreen.this,v);
                mp.pause();
            }
        });
        return view;
    }

    public void watchYoutubeVideo(String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=zKbcGEu-im4"));
        startActivity(webIntent);
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
    public void onDestroy() {
        super.onDestroy();
        mp.release();
    }
}
