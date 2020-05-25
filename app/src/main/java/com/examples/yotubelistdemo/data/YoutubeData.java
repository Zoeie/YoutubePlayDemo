package com.examples.yotubelistdemo.data;

/**
 * author zoe
 * created 2020/5/25 15:47
 */

public class YoutubeData {

    private String videoId;
    private String poster;

    public YoutubeData(String videoId, String poster) {
        this.videoId = videoId;
        this.poster = poster;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
