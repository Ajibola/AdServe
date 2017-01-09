package net.texsoftware.adservelibrary.ads.nativ;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ampiri.sdk.banner.FeedCardNativeAdView;
import com.ampiri.sdk.banner.NativeAdView;

import net.texsoftware.adservelibrary.component.NativeAdViewItem;
import net.texsoftware.adservelibrary.data.NativeAdNetwork;
import net.texsoftware.adservelibrary.data.NativeAdObject;

/**
 * Created by Jibola on 10/6/2015.
 */
public class NativeAdAmpiri extends NativeAd implements com.ampiri.sdk.listeners.AdEventCallback {

    View adView = null;
    com.ampiri.sdk.banner.NativeAd nativeAd;
    NativeAdView nativeAdView = null;
    Activity activity;

    public NativeAdAmpiri(Activity activity, NativeAdNetwork nativeAdNetwork) {
        this.adNetwork = nativeAdNetwork;
        this.activity = activity;
    }

    public void initNativeAd() {
        nativeAdView = new NativeAdView(activity);
        nativeAd = new com.ampiri.sdk.banner.NativeAd.Builder()
                .setAdUnitId(adNetwork.getAd_unit_id())
                .setAdViewBuilder(FeedCardNativeAdView.BUILDER)
                .setAdView(nativeAdView)
                .setCallback(this)
                .build(activity);

        nativeAd.loadAd();
    }

    @Override
    public View getNativeAd() throws Exception {
        nativeAdView = nativeAd.renderAdView();
        return nativeAdView;
    }

    @Override
    public void getNativeAd(TextView txtTitle, TextView txtSummary, ImageView imgMain, ImageView imgIcon, LinearLayout adChoicesLayout, TextView txtAttribution) {
        nativeAd.renderAdView().setTitleView(txtTitle);
        nativeAd.renderAdView().setTextView(txtSummary);
        nativeAd.renderAdView().setCoverImageView(imgMain);
        nativeAd.renderAdView().setIconView(imgIcon);
        nativeAd.renderAdView().setAdChoiceContainerView(adChoicesLayout);
        nativeAd.renderAdView().setAdAttributionView(txtAttribution);
    }

    @Override
    public View getMediaView() throws Exception {
        return null;
    }

    public void registerView(View view) {

    }

    public void unregisterView(View view) {
    }

    public NativeAdObject getNativeAdObject() {
        setNativeAdId(String.valueOf(this.hashCode()));
        return new NativeAdObject();
    }

    @Override
    public void refreshNativeAd() throws Exception {
        if (nativeAd != null)
            nativeAd.loadAd();
        else
            initNativeAd();
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (nativeAd != null) {
            nativeAd.onActivityDestroyed();
            nativeAd = null;
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
