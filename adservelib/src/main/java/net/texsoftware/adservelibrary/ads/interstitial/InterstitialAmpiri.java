package net.texsoftware.adservelibrary.ads.interstitial;

import android.app.Activity;

import net.texsoftware.adservelibrary.data.InterstitialAdNetwork;


/**
 * Created by Jibola on 10/6/2015.
 */
public class InterstitialAmpiri extends InterstitialAd implements com.ampiri.sdk.listeners.AdEventCallback {

    com.ampiri.sdk.banner.InterstitialAd ampiriInterstitialAd;

    public InterstitialAmpiri(Activity activity, InterstitialAdNetwork interstitialAdNetwork) {
        this.adNetwork = interstitialAdNetwork;
    }

    public void initInterstitial(Activity activity) {
        ampiriInterstitialAd = new com.ampiri.sdk.banner.InterstitialAd(activity, adNetwork.getAd_unit_id(), this);
        ampiriInterstitialAd.loadAd();
    }

    public void showInterstitial(){
        super.showInterstitial();

        if(ampiriInterstitialAd != null && ampiriInterstitialAd.isReady())
            ampiriInterstitialAd.showAd();
    }

    public void loadInterstitial(){
        if(ampiriInterstitialAd != null)
            ampiriInterstitialAd.loadAd();
    }

    @Override
    public void onPause() {
        if (ampiriInterstitialAd != null) {
            ampiriInterstitialAd.onActivityPaused();
        }
    }

    @Override
    public void onResume() {
        if (ampiriInterstitialAd != null) {
            ampiriInterstitialAd.onActivityResumed();
        }
    }

    @Override
    public void onDestroy() {
        if (ampiriInterstitialAd != null) {
            ampiriInterstitialAd.onActivityDestroyed();
        }
    }

    @Override
    public void onAdLoaded() {
        super.onRequestSuccess();
    }

    @Override
    public void onAdFailed(com.ampiri.sdk.mediation.ResponseStatus responseStatus) {
        super.onRequestFailed();
    }

    @Override
    public void onAdOpened() {
        super.onImpressionLogged();
    }

    @Override
    public void onAdClicked() {
        super.onClick();
    }

    @Override
    public void onAdClosed() {

    }

    @Override
    public void onAdCompleted() {

    }
}
