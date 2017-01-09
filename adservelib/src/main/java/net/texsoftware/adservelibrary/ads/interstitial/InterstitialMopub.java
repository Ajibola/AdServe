package net.texsoftware.adservelibrary.ads.interstitial;

import android.app.Activity;

import net.texsoftware.adservelibrary.data.InterstitialAdNetwork;

/**
 * Created by Jibola on 10/6/2015.
 */
public class InterstitialMopub extends InterstitialAd implements com.mopub.mobileads.MoPubInterstitial.InterstitialAdListener {

    com.mopub.mobileads.MoPubInterstitial mInterstitial;

    public InterstitialMopub(Activity activity, InterstitialAdNetwork interstitialAdNetwork) {
        this.adNetwork = interstitialAdNetwork;
    }

    public void initInterstitial(Activity activity) {
        mInterstitial = new com.mopub.mobileads.MoPubInterstitial(activity, adNetwork.getAd_unit_id());
        mInterstitial.setInterstitialAdListener(this);
        mInterstitial.load();
    }

    public void showInterstitial() {
        super.showInterstitial();

        if (mInterstitial != null && mInterstitial.isReady())
            mInterstitial.show();
    }

    public void loadInterstitial() {
        if (mInterstitial != null)
            mInterstitial.load();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        if (mInterstitial != null) {
            mInterstitial.destroy();
        }
    }

    @Override
    public void onInterstitialLoaded(com.mopub.mobileads.MoPubInterstitial interstitial) {
        super.onRequestSuccess();
        isLoaded = true;
    }

    @Override
    public void onInterstitialFailed(com.mopub.mobileads.MoPubInterstitial interstitial, com.mopub.mobileads.MoPubErrorCode errorCode) {
        super.onRequestFailed();
        isLoaded = false;
    }

    @Override
    public void onInterstitialShown(com.mopub.mobileads.MoPubInterstitial interstitial) {
        super.onImpressionLogged();
    }

    @Override
    public void onInterstitialClicked(com.mopub.mobileads.MoPubInterstitial interstitial) {
        super.onClick();
    }

    @Override
    public void onInterstitialDismissed(com.mopub.mobileads.MoPubInterstitial interstitial) {

    }
}
