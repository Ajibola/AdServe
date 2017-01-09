package net.texsoftware.adservelibrary.ads.video;

import android.app.Activity;

import net.texsoftware.adservelibrary.data.VideoInterstitialAdNetwork;


/**
 * Created by Jibola on 10/6/2015.
 */
public class VideoInterstitialRevmob extends VideoInterstitialAd {

    Activity activity;
    com.revmob.RevMob revmob;
    com.revmob.ads.interstitial.RevMobFullscreen video;
    com.revmob.RevMobAdsListener listener;
    boolean recieved = false;

    public VideoInterstitialRevmob(Activity activity, VideoInterstitialAdNetwork videoInterstitialAdNetwork) {
        this.adNetwork = videoInterstitialAdNetwork;
        this.activity = activity;
    }

    public void initInterstitial(Activity activity) {
        recieved = false;

        listener = new com.revmob.RevMobAdsListener() {
            @Override
            public void onRevMobAdNotReceived(String message) {
                onRequestFailed();
            }

            @Override
            public void onRevMobAdDismissed() {
            }

            @Override
            public void onRevMobAdClicked() {
                onClick();
            }

            @Override
            public void onRevMobVideoLoaded() {
                onRequestSuccess();
                recieved = true;
            }

            @Override
            public void onRevMobVideoNotCompletelyLoaded() {
                //You tried to show it but it's not loaded yet
            }

            @Override
            public void onRevMobVideoStarted() {
                onStarted();
            }

            @Override
            public void onRevMobVideoFinished() {
                onCompleted();
            }
        };

        revmob = com.revmob.RevMob.startWithListener(activity, new com.revmob.RevMobAdsListener() {
            @Override
            public void onRevMobSessionStarted() {
                loadInterstitial();
            }

            @Override
            public void onRevMobSessionNotStarted(String message) {
                //If the session fails, no ad will be displayed.
            }
        }, adNetwork.getAd_unit_id());
    }

    public void showInterstitial() {
        super.showInterstitial();
        if (recieved)
            video.showVideo();
    }

    public void loadInterstitial() {
        video = revmob.createVideo(activity, listener);
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
}
