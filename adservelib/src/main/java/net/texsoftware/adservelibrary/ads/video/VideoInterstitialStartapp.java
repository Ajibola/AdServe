package net.texsoftware.adservelibrary.ads.video;

import android.app.Activity;

import net.texsoftware.adservelibrary.data.VideoInterstitialAdNetwork;

/**
 * Created by Jibola on 11/16/2016.
 */

public class VideoInterstitialStartapp extends VideoInterstitialAd implements com.startapp.android.publish.adsCommon.adListeners.AdEventListener, com.startapp.android.publish.adsCommon.adListeners.AdDisplayListener, com.startapp.android.publish.adsCommon.VideoListener {

    com.startapp.android.publish.adsCommon.StartAppAd startAppAd;

    public VideoInterstitialStartapp(Activity activity, VideoInterstitialAdNetwork videoInterstitialAdNetwork) {
        this.adNetwork = videoInterstitialAdNetwork;
    }

    public void initInterstitial(Activity activity) {
        startAppAd = new com.startapp.android.publish.adsCommon.StartAppAd(activity);
        startAppAd.setVideoListener(this);
        startAppAd.loadAd(com.startapp.android.publish.adsCommon.StartAppAd.AdMode.REWARDED_VIDEO, this);
    }

    public void showInterstitial() {
        super.showInterstitial();

        if (startAppAd != null && startAppAd.isReady())
            startAppAd.showAd();
    }

    public void loadInterstitial() {
        if (startAppAd != null)
            startAppAd.loadAd(com.startapp.android.publish.adsCommon.StartAppAd.AdMode.REWARDED_VIDEO, this);
    }

    @Override
    public void onPause() {
        if (startAppAd != null)
            startAppAd.onPause();
    }

    @Override
    public void onResume() {
        if (startAppAd != null)
            startAppAd.onResume();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onReceiveAd(com.startapp.android.publish.adsCommon.Ad ad) {
        super.onRequestSuccess();
    }

    @Override
    public void onFailedToReceiveAd(com.startapp.android.publish.adsCommon.Ad ad) {
        super.onRequestFailed();
    }

    @Override
    public void adHidden(com.startapp.android.publish.adsCommon.Ad ad) {

    }

    @Override
    public void adDisplayed(com.startapp.android.publish.adsCommon.Ad ad) {
        super.onImpressionLogged();
    }

    @Override
    public void adClicked(com.startapp.android.publish.adsCommon.Ad ad) {
        super.onClick();
    }

    @Override
    public void adNotDisplayed(com.startapp.android.publish.adsCommon.Ad ad) {

    }

    @Override
    public void onVideoCompleted() {
        super.onCompleted();
    }
}
