package net.texsoftware.adservelibrary.ads.video;

import android.app.Activity;

import net.texsoftware.adservelibrary.data.VideoInterstitialAdNetwork;


/**
 * Created by Jibola on 10/6/2015.
 */
public class VideoInterstitialAppLovin extends VideoInterstitialAd implements com.applovin.sdk.AppLovinAdDisplayListener, com.applovin.sdk.AppLovinAdLoadListener, com.applovin.sdk.AppLovinAdClickListener {

    Activity activity;
    com.applovin.adview.AppLovinIncentivizedInterstitial adVideoInterstitial = null;
    boolean recieved = false;

    public VideoInterstitialAppLovin(Activity activity, VideoInterstitialAdNetwork videoInterstitialAdNetwork) {
        this.adNetwork = videoInterstitialAdNetwork;
        this.activity = activity;
    }

    public void initInterstitial(Activity activity) {
        adVideoInterstitial = com.applovin.adview.AppLovinIncentivizedInterstitial.create(activity);
        adVideoInterstitial.preload(this);

        recieved = false;
        if (adVideoInterstitial.isAdReadyToDisplay()) {
            recieved = true;
            super.onRequestSuccess();
        }
    }

    public void showInterstitial() {
        super.showInterstitial();

        if (adVideoInterstitial.isAdReadyToDisplay()) {
            // An ad is available to display.  It's safe to call show.
            adVideoInterstitial.show(activity);
        }
    }

    public void loadInterstitial() {
        adVideoInterstitial.preload(this);

        if (adVideoInterstitial.isAdReadyToDisplay()) {
            super.onRequestSuccess();
        }
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
    public void adDisplayed(com.applovin.sdk.AppLovinAd appLovinAd) {
        super.onImpressionLogged();
    }

    @Override
    public void adHidden(com.applovin.sdk.AppLovinAd appLovinAd) {

    }

    @Override
    public void adReceived(com.applovin.sdk.AppLovinAd appLovinAd) {
        if (!recieved)
            super.onRequestSuccess();
    }

    @Override
    public void failedToReceiveAd(int i) {
        super.onRequestFailed();
    }

    @Override
    public void adClicked(com.applovin.sdk.AppLovinAd appLovinAd) {
        super.onClick();
    }
}
