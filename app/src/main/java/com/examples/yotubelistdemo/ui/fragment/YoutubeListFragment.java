package com.examples.yotubelistdemo.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.examples.yotubelistdemo.R;
import com.examples.yotubelistdemo.constant.Const;
import com.examples.yotubelistdemo.constant.DeveloperKey;
import com.examples.yotubelistdemo.data.YoutubeData;
import com.examples.yotubelistdemo.listener.YoutubeListener;
import com.examples.yotubelistdemo.ui.adapter.YoutubeAdapter;
import com.examples.yotubelistdemo.utils.LogUtil;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

/**
 * author zoe
 * created 2020/5/25 14:06
 */

public class YoutubeListFragment extends Fragment implements YouTubePlayer.OnFullscreenListener, BaseQuickAdapter.OnItemClickListener, YouTubePlayer.OnInitializedListener {

    private RecyclerView rvList;
    private List<YoutubeData> youtubeDataList = new ArrayList<>();
    private YouTubePlayerView playerView;
    private YouTubePlayer player;
    private boolean isFullScreen;
    private View currView;//当前选中播放的View;

    public static YoutubeListFragment newInstance() {
        return new YoutubeListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        for (int i = 0; i < Const.posterArr.length; i++) {
            youtubeDataList.add(new YoutubeData(Const.videoArr[i], Const.posterArr[i]));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_youtube, null);
        rvList = mRootView.findViewById(R.id.rv_youtube_list);
        initRvList();
        initPlayerView();
        return mRootView;
    }

    private void initRvList() {
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (currView != null && !isFullScreen) {
                    LinearLayoutManager manager = (LinearLayoutManager) rvList.getLayoutManager();
                    if (manager == null) return;
                    int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                    int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                    int pos = (int) currView.getTag();
                    if (pos < firstVisibleItemPosition || pos > lastVisibleItemPosition) {
                        FrameLayout playCon = currView.findViewById(R.id.fl_play_container);
                        ImageView ivPoster = currView.findViewById(R.id.thumbnail);
                        playCon.removeAllViews();
                        ivPoster.setVisibility(View.VISIBLE);
                        currView = null;
                        if(player != null) {
                            player.pause();
                        }
                        LogUtil.d("xxx remove firstVisibleItemPosition：" + firstVisibleItemPosition + ",lastVisibleItemPosition:" + lastVisibleItemPosition+",pos:"+pos);
                    }
                }
            }
        });
        YoutubeAdapter adapter = new YoutubeAdapter(youtubeDataList, getActivity());
        adapter.setOnItemClickListener(this);
        rvList.setAdapter(adapter);
    }

    private void initPlayerView() {
        playerView = new YouTubePlayerView(getActivity());
        playerView.initialize(DeveloperKey.DEVELOPER_KEY, this);
    }

    @Override
    public void onFullscreen(boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
        LogUtil.d("xxx isFullScreen:"+isFullScreen);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        FrameLayout playCon = view.findViewById(R.id.fl_play_container);
        ImageView ivPoster = view.findViewById(R.id.thumbnail);
        if (currView != null) {
            FrameLayout lastPlayCon = currView.findViewById(R.id.fl_play_container);
            ImageView lastIvPoster = currView.findViewById(R.id.thumbnail);
            lastPlayCon.removeAllViews();
            lastIvPoster.setVisibility(View.VISIBLE);
        }
        ivPoster.setVisibility(View.GONE);
        playCon.addView(playerView);
        currView = view;
        if (player != null) {
            String videoId = youtubeDataList.get(position).getVideoId();
            player.cueVideo(videoId);
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean restored) {
        player = youTubePlayer;
        youTubePlayer.setOnFullscreenListener(this);
        youTubePlayer.setPlayerStateChangeListener(new YoutubeListener() {
            @Override
            public void onLoaded(String s) {
                super.onLoaded(s);
                player.play();
            }

            @Override
            public void onVideoStarted() {
                super.onVideoStarted();
                //视频播放成功
                disableVideoTitle();
            }
        });
        LogUtil.d("xxx player:" + player);
    }

    private void disableVideoTitle() {
        if (playerView == null) return;
        int a = 0x7f0b09fa;//player_video_title_view
        View view = playerView.findViewById(a);
        if (view != null) {
            view.setEnabled(false);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        player = null;
    }

    public boolean isFullScreen() {
        return isFullScreen;
    }

    public void exitFullScreen() {
        if(player != null) {
            player.setFullscreen(false);
        }
    }
}
