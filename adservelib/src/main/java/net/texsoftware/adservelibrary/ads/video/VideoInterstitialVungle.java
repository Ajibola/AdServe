package net.texsoftware.adservelibrary.ads.video;

import android.app.Activity;

import net.texsoftware.adservelibrary.data.VideoInterstitialAdNetwork;

/**
 * Created by Jibola on 11/16/2016.
 */

public class VideoInterstitialVungle extends VideoInterstitialAd implements com.vungle.publisher.EventListener {

    com.vungle.publisher.VunglePub vunglePub;
    Activity activity;

    public VideoInterstitialVungle(Activity activity, VideoInterstitialAdNetwork videoInterstitialAdNetwork) {
        this.adNetwork = videoInterstitialAdNetwork;
        this.activity = activity;
    }

    @Override
    public void initInterstitial(Activity activity) {
        vunglePub = com.vungle.publisher.VunglePub.getInstance();
        vunglePub.addEventListeners(this);
        vunglePub.init(activity, adNetwork.getAd_unit_id());

        if (vunglePub.isAdPlayable()) {
            super.onRequestSuccess();
        }
    }

    public void showInterstitial() {
        super.showInterstitial();

        if (vunglePub != null && vunglePub.isAdPlayable())
            vunglePub.playAd();
    }

    @Override
    public void loadInterstitial() {
        vunglePub.init(activity, adNetwork.getAd_unit_id());

        if (vunglePub.isAdPlayable()) {
            super.onRequestSuccess();
        }
    }

    @Override
    public void onPause() {
        vunglePub.onPause();
    }

    @Override
    public void onResume() {
        vunglePub.onResume();
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onAdEnd(boolean b, boolean b1) {
        super.onCompleted();
    }

    @Override
    public void onAdStart() {
        super.onImpressionLogged();
        super.onStarted();
    }

    @Override
    public void onAdUnavailable(String s) {
        super.onRequestFailed();
    }

    @Override
    public void onAdPlayableChanged(boolean available) {
        if (available && !super.isLoaded)
            super.onRequestSuccess();
    }

    @Override
    public void onVideoView(boolean b, int i, int i1) {
    }
}
