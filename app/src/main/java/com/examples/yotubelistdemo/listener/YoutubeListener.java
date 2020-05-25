package com.examples.yotubelistdemo.listener;

import com.examples.yotubelistdemo.utils.LogUtil;
import com.google.android.youtube.player.YouTubePlayer;

/**
 * author zoe
 * created 2020/5/25 16:38
 */

public class YoutubeListener implements YouTubePlayer.PlayerStateChangeListener {

    @Override
    public void onLoading() {
        LogUtil.d("xxx onLoading");
    }

    @Override
    public void onLoaded(String s) {
        LogUtil.d("xxx onLoaded:" + s);
    }

    @Override
    public void onAdStarted() {
        LogUtil.d("xxx onAdStarted");
    }

    @Override
    public void onVideoStarted() {
        LogUtil.d("xxx onVideoStarted");
    }

    @Override
    public void onVideoEnded() {
        LogUtil.d("xxx onVideoEnded");
    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {
        LogUtil.d("xxx onError:" + errorReason.name());
    }
}
