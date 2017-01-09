package net.texsoftware.adservelibrary.ads.banner;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import net.texsoftware.adservelibrary.data.BannerAdNetwork;

/**
 * Created by Jibola on 10/6/2015.
 */
public class BannerFacebook extends BannerAd implements com.facebook.ads.AdListener, com.facebook.ads.ImpressionListener {

    com.facebook.ads.AdView fbAdView;
    private final Activity activity;

    public BannerFacebook(Activity activity, BannerAdNetwork bannerAdNetwork) {
        this.activity = activity;
        this.adNetwork = bannerAdNetwork;
        initBanner();
    }

    public void initBanner() {
        //Facebook Audience Network Instantiate an AdView view
        fbAdView = new com.facebook.ads.AdView(activity, adNetwork.getAd_unit_id(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        fbAdView.setAdListener(this);
        fbAdView.setImpressionListener(this);
        fbAdView.loadAd();
    }

    @Override
    public View getBanner() throws Exception {
        if (fbAdView == null) {
            initBanner();
        }

        ViewGroup parent = (ViewGroup) fbAdView.getParent();
        if (parent != null) {
            parent.removeView(fbAdView);
            parent.invalidate();
        }

        return fbAdView;
    }

    @Override
    public void refreshBanner() throws Exception {
        if (fbAdView != null)
            fbAdView.loadAd();
        else
            initBanner();
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {

    }

    @Override
    public boolean onDestroy() {
        super.onDestroy();

        if (fbAdView != null) {
            fbAdView.removeAllViews();
            fbAdView.destroy();
            fbAdView = null;
        }
        return true;
    }

    @Override
    public void onError(com.facebook.ads.Ad ad, com.facebook.ads.AdError adError) {
        super.onRequestFailed();
    }

    @Override
    public void onAdLoaded(com.facebook.ads.Ad ad) {
        super.onRequestSuccess();
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
