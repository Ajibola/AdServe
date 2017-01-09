package net.texsoftware.adservelibrary.ads.interstitial;

import android.app.Activity;

import net.texsoftware.adservelibrary.data.InterstitialAdNetwork;


/**
 * Created by Jibola on 10/6/2015.
 */
public class InterstitialGoogle extends InterstitialAd {

    com.google.android.gms.ads.InterstitialAd adMobInterstitialAd;
    com.google.android.gms.ads.AdRequest adRequest;

    public InterstitialGoogle(Activity activity, InterstitialAdNetwork interstitialAdNetwork) {
        this.adNetwork = interstitialAdNetwork;
    }

    public void initInterstitial(Activity activity) {
        adMobInterstitialAd = new com.google.android.gms.ads.InterstitialAd(activity);
        adMobInterstitialAd.setAdUnitId(adNetwork.getAd_unit_id());
        // Create ad request.
        adRequest = new com.google.android.gms.ads.AdRequest.Builder().build();

        // Begin loading your interstitial.
        adMobInterstitialAd.loadAd(adRequest);
        adMobInterstitialAd.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdClosed() {
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                onRequestFailed();
                isLoaded = false;
            }

            @Override
            public void onAdLeftApplication() {
                onClick();
            }

            @Override
            public void onAdOpened() {
                onImpressionLogged();
            }

            @Override
            public void onAdLoaded() {
                onRequestSuccess();
                isLoaded = true;
            }
        });
    }

    public void showInterstitial() {
        super.showInterstitial();

        if (adMobInterstitialAd != null && adMobInterstitialAd.isLoaded())
            adMobInterstitialAd.show();
    }

    public void loadInterstitial() {
        if (adMobInterstitialAd != null)
            adMobInterstitialAd.loadAd(adRequest);
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
