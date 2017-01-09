package net.texsoftware.adservelibrary.ads.interstitial;

import android.app.Activity;

import net.texsoftware.adservelibrary.data.InterstitialAdNetwork;

/**
 * Created by Jibola on 10/6/2015.
 */
public class InterstitialOgury extends InterstitialAd implements io.presage.utils.IADHandler {

    public InterstitialOgury(Activity activity, InterstitialAdNetwork interstitialAdNetwork) {
        this.adNetwork = interstitialAdNetwork;
    }

    public void initInterstitial(Activity activity) {
        io.presage.Presage.getInstance().loadInterstitial(this);
    }

    public void showInterstitial() {
        super.showInterstitial();

        if (io.presage.Presage.getInstance().isInterstitialLoaded())
            io.presage.Presage.getInstance().showInterstitial(this);
    }

    public void loadInterstitial() {
        io.presage.Presage.getInstance().loadInterstitial(this);
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
    public void onAdFound() {
        super.onRequestSuccess();
    }

    @Override
    public void onAdNotFound() {
        super.onRequestFailed();
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
