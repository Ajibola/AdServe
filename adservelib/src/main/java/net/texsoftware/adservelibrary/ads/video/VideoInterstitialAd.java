package net.texsoftware.adservelibrary.ads.video;

import android.app.Activity;

import net.texsoftware.adservelibrary.AdManager;
import net.texsoftware.adservelibrary.data.AdNetwork;
import net.texsoftware.adservelibrary.listeners.VideoAdRequestListener;
import net.texsoftware.adservelibrary.utils.Analytics;

import java.util.Date;

/**
 * Created by Jibola on 10/6/2015.
 */
public abstract class VideoInterstitialAd {

    Analytics analytics = null;
    VideoAdRequestListener videoAdRequestListener;
    AdNetwork adNetwork;
    boolean isLoaded = false;
    public final long timeStarted = new Date().getTime();

    public VideoInterstitialAd() {
    }

    public void setAnalytics(Analytics analytics) {
        this.analytics = analytics;
    }

    public void setInterstitialAdRequestListener(VideoAdRequestListener videoAdRequestListener) {
        this.videoAdRequestListener = videoAdRequestListener;
    }

    public void showInterstitial() {
        isLoaded = false; //dont show again
    };

    public abstract void initInterstitial(Activity activity);

    public abstract void loadInterstitial();

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setAdNetwork(AdNetwork adNetwork) {
        this.adNetwork = adNetwork;
    }

    public void onRequestSuccess() {
        isLoaded = true;
        if (videoAdRequestListener != null)
            videoAdRequestListener.onVideoRequestSuccess();

        if(analytics != null)
            analytics.sendActionLabelEvent(AdManager.ADVERT, "Successful Request", this.getClass().getSimpleName(), 0L);
    }

    public void onRequestFailed() {
        isLoaded = false;
        if (videoAdRequestListener != null)
            videoAdRequestListener.onVideoRequestFailed();

        if(analytics != null)
            analytics.sendActionLabelEvent(AdManager.ADVERT, "Failed Request", this.getClass().getSimpleName(), 0L);
    }

    public void onImpressionLogged() {
        if (videoAdRequestListener != null)
            videoAdRequestListener.onVideoImpressionLogged();

        if(analytics != null)
            analytics.sendActionLabelEvent(AdManager.ADVERT, "Log Impression", this.getClass().getSimpleName(), 0L);
    }

    public void onClick() {
        if (videoAdRequestListener != null)
            videoAdRequestListener.onVideoClick();

        isLoaded = false;
        if(analytics != null)
            analytics.sendActionLabelEvent(AdManager.ADVERT, "Clicked", this.getClass().getSimpleName(), 0L);
    }

    public void onStarted() {
        if (videoAdRequestListener != null)
            videoAdRequestListener.onVideoStarted();

        isLoaded = false;
        if(analytics != null)
            analytics.sendActionLabelEvent(AdManager.ADVERT, "Video Started", this.getClass().getSimpleName(), 0L);
    }

    public void onCompleted() {
        if (videoAdRequestListener != null)
            videoAdRequestListener.onVideoFinished();

        isLoaded = false;
        if(analytics != null)
            analytics.sendActionLabelEvent(AdManager.ADVERT, "Video Finished", this.getClass().getSimpleName(), 0L);
    }

    public abstract void onPause();

    public abstract void onResume();

    public abstract void onDestroy();
}
