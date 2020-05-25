package com.examples.yotubelistdemo.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.examples.yotubelistdemo.R;
import com.examples.yotubelistdemo.constant.DeveloperKey;
import com.examples.yotubelistdemo.data.YoutubeData;
import com.examples.yotubelistdemo.utils.ImageUtils;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

/**
 * author zoe
 * created 2018/12/3 9:35
 */

public class YoutubeAdapter extends BaseQuickAdapter<YoutubeData, BaseViewHolder> implements YouTubeThumbnailView.OnInitializedListener {

    private Context context;

    public YoutubeAdapter(@Nullable List<YoutubeData> data, Context context) {
        super(R.layout.youtube_item_view, data);
        this.context = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, YoutubeData item) {
        helper.itemView.setTag(helper.getAdapterPosition());
        YouTubeThumbnailView ivPoster = helper.getView(R.id.thumbnail);
//        ivPoster.setTag(item.getVideoId());
//        ivPoster.initialize(DeveloperKey.DEVELOPER_KEY, this);
        ivPoster.setVisibility(View.VISIBLE);
        ImageUtils.get().loadImg(context, item.getPoster(), ivPoster);
        FrameLayout fl = helper.getView(R.id.fl_play_container);
        fl.removeAllViews();
    }

    @Override
    public void onInitializationSuccess(YouTubeThumbnailView view, YouTubeThumbnailLoader loader) {
//        String videoId = (String) view.getTag();
//        loader.setVideo(videoId);
    }

    @Override
    public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
