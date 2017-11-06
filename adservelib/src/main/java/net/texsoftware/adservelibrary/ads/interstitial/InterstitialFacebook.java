package net.texsoftware.adservelibrary.ads.interstitial;

import android.app.Activity;

import net.texsoftware.adservelibrary.data.InterstitialAdNetwork;

/**
 * Created by Jibola on 10/6/2015.
 */
public class InterstitialFacebook extends InterstitialAd implements com.facebook.ads.InterstitialAdListener {

    com.facebook.ads.InterstitialAd fbInterstitialAd;

    public InterstitialFacebook(Activity activity, InterstitialAdNetwork interstitialAdNetwork) {
        this.adNetwork = interstitialAdNetwork;
    }

    public void initInterstitial(Activity activity) {
        fbInterstitialAd = new com.facebook.ads.InterstitialAd(activity, adNetwork.getAd_unit_id());
        fbInterstitialAd.setAdListener(this);
        fbInterstitialAd.loadAd();
    }

    public void showInterstitial(){
        super.showInterstitial();

        if(fbInterstitialAd != null && fbInterstitialAd.isAdLoaded())
            fbInterstitialAd.show();
    }

    public void loadInterstitial(){
        if(fbInterstitialAd != null)
            fbInterstitialAd.loadAd();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        if (fbInterstitialAd != null) {
            fbInterstitialAd.destroy();
        }
    }

    @Override
    public void onInterstitialDisplayed(com.facebook.ads.Ad ad) {
    }

    @Override
    public void onInterstitialDismissed(com.facebook.ads.Ad ad) {

    }

    @Override
    public void onError(com.facebook.ads.Ad ad, com.facebook.ads.AdError adError) {
        super.onRequestFailed();
        isLoaded = false;
    }

    @Override
    public void onAdLoaded(com.facebook.ads.Ad ad) {
        super.onRequestSuccess();
        isLoaded = true;
    }

    @Override
    public void onAdClicked(com.facebook.ads.Ad ad) {
        super.onClick();
    }

    @Override
    public void onLoggingImpression(com.facebook.ads.Ad ad) {
        super.onImpressionLogged();
    }
}
