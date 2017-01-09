package net.texsoftware.adservelibrary.ads.video;

import android.app.Activity;

import net.texsoftware.adservelibrary.data.VideoInterstitialAdNetwork;

/**
 * Created by Jibola on 11/16/2016.
 */

public class VideoInterstitialHeyzap extends VideoInterstitialAd implements com.heyzap.sdk.ads.HeyzapAds.OnStatusListener {

    Activity activity = null;

    public VideoInterstitialHeyzap(Activity activity, VideoInterstitialAdNetwork videoInterstitialAdNetwork) {
        this.adNetwork = videoInterstitialAdNetwork;
        this.activity = activity;
    }

    public void initInterstitial(Activity activity) {
        com.heyzap.sdk.ads.IncentivizedAd.setOnStatusListener(this);
        com.heyzap.sdk.ads.IncentivizedAd.fetch();
    }

    public void showInterstitial() {
        super.showInterstitial();

        if (com.heyzap.sdk.ads.IncentivizedAd.isAvailable())
            com.heyzap.sdk.ads.IncentivizedAd.display(activity);
    }

    public void loadInterstitial() {
        com.heyzap.sdk.ads.IncentivizedAd.fetch();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void onShow(String s) {
        super.onImpressionLogged();
    }

    @Override
    public void onClick(String s) {
        super.onClick();
    }

    @Override
    public void onHide(String s) {

    }

    @Override
    public void onFailedToShow(String s) {

    }

    @Override
    public void onAvailable(String s) {
        super.onRequestSuccess();
        isLoaded = true;
    }

    @Override
    public void onFailedToFetch(String s) {
        super.onRequestFailed();
        isLoaded = false;
    }

    @Override
    public void onAudioStarted() {
        super.onStarted();
    }

    @Override
    public void onAudioFinished() {
        super.onCompleted();
    }
}
