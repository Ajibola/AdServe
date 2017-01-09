package net.texsoftware.adservelibrary.ads.interstitial;

import android.app.Activity;

import com.heyzap.sdk.ads.HeyzapAds;

import net.texsoftware.adservelibrary.data.InterstitialAdNetwork;

/**
 * Created by Jibola on 10/6/2015.
 */
public class InterstitialHeyzap extends InterstitialAd implements HeyzapAds.OnStatusListener {

    public InterstitialHeyzap(Activity activity, InterstitialAdNetwork interstitialAdNetwork) {
        this.adNetwork = interstitialAdNetwork;
    }

    public void initInterstitial(Activity activity) {
        com.heyzap.sdk.ads.InterstitialAd.setOnStatusListener(this);
        com.heyzap.sdk.ads.InterstitialAd.display(activity);
    }

    public void showInterstitial(Activity activity) {
        super.showInterstitial();

        if (com.heyzap.sdk.ads.InterstitialAd.isAvailable())
            com.heyzap.sdk.ads.InterstitialAd.display(activity);
    }

    public void loadInterstitial() {
        com.heyzap.sdk.ads.InterstitialAd.fetch();
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

    }

    @Override
    public void onAudioFinished() {

    }
}
