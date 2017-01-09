package net.texsoftware.adservelibrary.ads.interstitial;

import android.app.Activity;

import net.texsoftware.adservelibrary.data.InterstitialAdNetwork;

/**
 * Created by Jibola on 10/6/2015.
 */
public class InterstitialAvocarrot extends InterstitialAd {

    com.avocarrot.androidsdk.AvocarrotInterstitial avocarrotInterstitial = null;

    public InterstitialAvocarrot(Activity activity, InterstitialAdNetwork interstitialAdNetwork) {
        this.adNetwork = interstitialAdNetwork;
    }

    public void initInterstitial(Activity activity) {
        avocarrotInterstitial = new com.avocarrot.androidsdk.AvocarrotInterstitial(activity, adNetwork.getApi_key(), adNetwork.getAd_unit_id());
        avocarrotInterstitial.setSandbox(false);
        avocarrotInterstitial.setLogger(true, "ALL");
        avocarrotInterstitial.loadAd();

        avocarrotInterstitial.setListener(new com.avocarrot.androidsdk.AvocarrotInterstitialListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                onRequestSuccess();
            }

            @Override
            public void onAdError(com.avocarrot.androidsdk.AdError error) {
                super.onAdError(error);
                onRequestFailed();
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
                onClick();
            }

            @Override
            public void onAdDisplayed() {
                super.onAdDisplayed();
                onImpressionLogged();
            }

            @Override
            public void onAdDismissed() {
                super.onAdDismissed();
            }
        });
    }

    public void showInterstitial() {
        super.showInterstitial();

        if (avocarrotInterstitial.isReady()) {
            avocarrotInterstitial.showAd();
        }
    }

    public void loadInterstitial() {
        avocarrotInterstitial.loadAd();
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
