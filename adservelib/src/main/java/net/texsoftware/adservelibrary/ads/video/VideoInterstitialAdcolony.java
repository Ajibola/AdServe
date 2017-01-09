package net.texsoftware.adservelibrary.ads.video;

import android.app.Activity;

import net.texsoftware.adservelibrary.data.VideoInterstitialAdNetwork;

/**
 * Created by Jibola on 10/6/2015.
 */
public class VideoInterstitialAdcolony extends VideoInterstitialAd {

    com.adcolony.sdk.AdColonyInterstitial adColonyInterstitial = null;
    com.adcolony.sdk.AdColonyInterstitialListener adColonyInterstitialListener;

    public VideoInterstitialAdcolony(Activity activity, VideoInterstitialAdNetwork videoInterstitialAdNetwork) {
        this.adNetwork = videoInterstitialAdNetwork;
    }

    public void initInterstitial(Activity activity) {
        adColonyInterstitialListener = new com.adcolony.sdk.AdColonyInterstitialListener() {
            @Override
            public void onRequestFilled(com.adcolony.sdk.AdColonyInterstitial ad) {
                VideoInterstitialAdcolony.super.onRequestSuccess();
                adColonyInterstitial = ad;
            }

            @Override
            public void onOpened(com.adcolony.sdk.AdColonyInterstitial ad) {
                VideoInterstitialAdcolony.super.onImpressionLogged();
            }

            @Override
            public void onRequestNotFilled(com.adcolony.sdk.AdColonyZone adZone) {
                VideoInterstitialAdcolony.super.onRequestFailed();
            }

            @Override
            public void onClicked(com.adcolony.sdk.AdColonyInterstitial ad) {
                VideoInterstitialAdcolony.super.onClick();
            }
        };

        com.adcolony.sdk.AdColony.requestInterstitial(adNetwork.getAd_unit_id(), adColonyInterstitialListener);
    }

    public void showInterstitial() {
        super.showInterstitial();

        if (adColonyInterstitial != null && !adColonyInterstitial.isExpired())
            adColonyInterstitial.show();
    }

    public void loadInterstitial() {
        if (adColonyInterstitial != null)
            com.adcolony.sdk.AdColony.requestInterstitial(adNetwork.getAd_unit_id(), adColonyInterstitialListener);
    }

    @Override
    public void onPause() {
        if (adColonyInterstitial != null) {

        }
    }

    @Override
    public void onResume() {
        if (adColonyInterstitial != null) {
        }
    }

    @Override
    public void onDestroy() {
        if (adColonyInterstitial != null) {
            adColonyInterstitial.destroy();
        }
    }
}
