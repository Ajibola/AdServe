package net.texsoftware.adservelibrary.ads.interstitial;

import android.app.Activity;

import net.texsoftware.adservelibrary.data.InterstitialAdNetwork;

/**
 * Created by Jibola on 10/6/2015.
 */
public class InterstitialOgury extends InterstitialAd implements io.presage.IADHandler {

    public InterstitialOgury(Activity activity, InterstitialAdNetwork interstitialAdNetwork) {
        this.adNetwork = interstitialAdNetwork;
    }

    public void initInterstitial(Activity activity) {
        io.presage.Presage.getInstance().load(this);
    }

    public void showInterstitial() {
        super.showInterstitial();

        if (io.presage.Presage.getInstance().canShow())
            io.presage.Presage.getInstance().show(this);
    }

    public void loadInterstitial() {
        io.presage.Presage.getInstance().load(this);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {
        io.presage.Presage.getInstance().removeListeners();
    }

    @Override
    public void onAdAvailable() {
        super.onRequestSuccess();
    }

    @Override
    public void onAdNotAvailable() {
        super.onRequestFailed();
    }

    @Override
    public void onAdLoaded() {

    }

    @Override
    public void onAdClosed() {

    }

    @Override
    public void onAdError(int i) {
    }

    @Override
    public void onAdDisplayed() {
        super.onImpressionLogged();
    }
}
