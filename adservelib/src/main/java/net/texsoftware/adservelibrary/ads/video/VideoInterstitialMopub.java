package net.texsoftware.adservelibrary.ads.video;

import android.app.Activity;
import android.support.annotation.NonNull;

import net.texsoftware.adservelibrary.data.VideoInterstitialAdNetwork;

import java.util.Set;

/**
 * Created by Jibola on 11/16/2016.
 */

public class VideoInterstitialMopub extends VideoInterstitialAd implements com.mopub.mobileads.MoPubRewardedVideoListener {

    public VideoInterstitialMopub(Activity activity, VideoInterstitialAdNetwork videoInterstitialAdNetwork) {
        this.adNetwork = videoInterstitialAdNetwork;
    }

    public void initInterstitial(Activity activity) {
        com.mopub.mobileads.MoPubRewardedVideos.initializeRewardedVideo(activity);
        com.mopub.mobileads.MoPubRewardedVideos.loadRewardedVideo(adNetwork.getAd_unit_id());
        com.mopub.mobileads.MoPubRewardedVideos.setRewardedVideoListener(this);
    }

    public void showInterstitial() {
        super.showInterstitial();

        com.mopub.mobileads.MoPubRewardedVideos.showRewardedVideo(adNetwork.getAd_unit_id());
    }

    public void loadInterstitial() {
        com.mopub.mobileads.MoPubRewardedVideos.loadRewardedVideo(adNetwork.getAd_unit_id());
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
    public void onRewardedVideoLoadSuccess(@NonNull String adUnitId) {
        super.onRequestSuccess();
        isLoaded = true;
    }

    @Override
    public void onRewardedVideoLoadFailure(@NonNull String adUnitId, @NonNull com.mopub.mobileads.MoPubErrorCode errorCode) {
        super.onRequestFailed();
        isLoaded = false;
    }

    @Override
    public void onRewardedVideoStarted(@NonNull String adUnitId) {
        super.onImpressionLogged();
        super.onStarted();
    }

    @Override
    public void onRewardedVideoPlaybackError(@NonNull String adUnitId, @NonNull com.mopub.mobileads.MoPubErrorCode errorCode) {

    }

    @Override
    public void onRewardedVideoClosed(@NonNull String adUnitId) {
        super.onCompleted();
    }

    @Override
    public void onRewardedVideoCompleted(@NonNull Set<String> adUnitIds, @NonNull com.mopub.common.MoPubReward reward) {
        super.onCompleted();
    }
}
