package com.examples.yotubelistdemo.ui.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;

import com.examples.yotubelistdemo.R;
import com.examples.yotubelistdemo.ui.fragment.YoutubeListFragment;
import com.google.android.youtube.player.YouTubeBaseActivity;

public class MainActivity extends YouTubeBaseActivity {

    private YoutubeListFragment youtubeListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        youtubeListFragment = YoutubeListFragment.newInstance();
        transaction.replace(R.id.fl_con, youtubeListFragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (youtubeListFragment.isFullScreen()) {
            youtubeListFragment.exitFullScreen();
        } else {
            super.onBackPressed();
        }
    }
}
