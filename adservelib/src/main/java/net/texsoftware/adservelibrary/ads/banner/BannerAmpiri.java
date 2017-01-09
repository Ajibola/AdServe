package net.texsoftware.adservelibrary.ads.banner;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import net.texsoftware.adservelibrary.data.BannerAdNetwork;


/**
 * Created by Jibola on 10/6/2015.
 */
public class BannerAmpiri extends BannerAd implements com.ampiri.sdk.listeners.AdEventCallback {

    com.ampiri.sdk.banner.StandardAd standardAd;
    FrameLayout adView;
    private final Activity activity;

    public BannerAmpiri(Activity activity, BannerAdNetwork bannerAdNetwork) {
        this.activity = activity;
        this.adNetwork = bannerAdNetwork;
        initBanner();
    }

    public void initBanner() {
        //Facebook Audience Network Instantiate an AdView view
        adView = new FrameLayout(activity);
        adView.setBackgroundColor(Color.TRANSPARENT);

        standardAd = new com.ampiri.sdk.banner.StandardAd(activity, adView, adNetwork.getAd_unit_id(), com.ampiri.sdk.mediation.BannerSize.BANNER_SIZE_320x50, this);
        standardAd.loadAd();
    }

    @Override
    public View getBanner() throws Exception {
        if (standardAd == null) {
            initBanner();
        }

        ViewGroup parent = (ViewGroup) adView.getParent();
        if (parent != null) {
            parent.removeView(adView);
            parent.invalidate();
        }

        adView.setVisibility(View.VISIBLE);

        return adView;
    }

    @Override
    public void refreshBanner() throws Exception {
        if (standardAd != null)
            standardAd.loadAd();
        else
            initBanner();
    }


    @Override
    public void onPause() {
        if (standardAd != null) {
            standardAd.onActivityPaused();
        }
    }

    @Override
    public void onResume() {
        if (standardAd != null) {
            standardAd.onActivityResumed();
        }
    }

    @Override
    public boolean onDestroy() {
        super.onDestroy();
        if (standardAd != null) {
            standardAd.onActivityDestroyed();
            standardAd = null;
        }
        return true;
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
