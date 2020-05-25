package com.examples.yotubelistdemo.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;

/**
 * author zoe
 * created 2019/8/14 14:41
 */

public class ImageUtils {

    private static ImageUtils sImageUtils;
    private RequestOptions options;

    public static ImageUtils get() {
        if (sImageUtils == null) {
            sImageUtils = new ImageUtils();
            LogUtil.d("create ImageUtils instance");
        }
        return sImageUtils;
    }

    private ImageUtils() {
        options = genRequestOptions();
    }

    public void loadImg(Context context, String url, ImageView iv) {
        if (iv == null) return;
        Glide.with(context).load(url).apply(options).into(iv);
    }

    public void loadImgAsGif(Context context, String url, ImageView iv) {
        if (TextUtils.isEmpty(url) || iv == null) return;
        Glide.with(context).asGif().load(url).apply(options).into(iv);
    }

    private RequestOptions genRequestOptions() {
        return new RequestOptions()
//                .placeholder(new PlaceHolderDrawable(App.get(), R.color.img_error_bg, R.mipmap.place_holder_icon))
//                .error(new PlaceHolderDrawable(App.get(), R.color.img_error_bg, R.mipmap.place_holder_icon))
                .fitCenter()
                .format(DecodeFormat.PREFER_RGB_565);
    }
}
