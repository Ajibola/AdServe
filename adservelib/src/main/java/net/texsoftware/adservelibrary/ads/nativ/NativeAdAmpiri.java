package net.texsoftware.adservelibrary.ads.nativ;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ampiri.sdk.banner.NativeAdView;
import com.ampiri.sdk.banner.StoryCardNativeAdView;
import com.ampiri.sdk.banner.StreamAdAdapter;

import net.texsoftware.adservelibrary.component.NativeAdViewItem;
import net.texsoftware.adservelibrary.data.NativeAdNetwork;
import net.texsoftware.adservelibrary.data.NativeAdObject;

/**
 * Created by Jibola on 10/6/2015.
 */
public class NativeAdAmpiri extends NativeAd implements com.ampiri.sdk.listeners.AdEventCallback {

    View adView = null;
    com.ampiri.sdk.banner.NativeAd nativeAd;
    com.ampiri.sdk.banner.NativeAdView nativeAdView = null;
    Activity activity;

    public NativeAdAmpiri(Activity activity, NativeAdNetwork nativeAdNetwork) {
        this.adNetwork = nativeAdNetwork;
        this.activity = activity;
    }

    public void initNativeAd() {
        nativeAdView = new com.ampiri.sdk.banner.NativeAdView(activity);
        nativeAd = new com.ampiri.sdk.banner.NativeAd.Builder()
                .setAdUnitId(adNetwork.getAd_unit_id())
                .setAdView(nativeAdView)
                .setCallback(this)
                .build(activity);
        nativeAd.loadAd();
    }

    @Override
    public View getNativeAd() throws Exception {
        NativeAdViewItem nativeAdViewItem = new NativeAdViewItem(activity);
        nativeAdView.setTitleView(nativeAdViewItem.txtTitle);
        nativeAdView.setTextView(nativeAdViewItem.txtSummary);
        nativeAdView.setCoverImageView(nativeAdViewItem.imgView);
        nativeAdView.setIconView(nativeAdViewItem.imgIcon);
        nativeAd.renderAdView(nativeAdView);

        nativeAdViewItem.txtTitle.setText(nativeAdViewItem.txtTitle.getText() + " " + nativeAdViewItem.txtSummary.getText());
        return nativeAdViewItem;
    }

    @Override
    public void getNativeAd(View viewLayout, TextView txtTitle, TextView txtSummary, ImageView imgMain, ImageView imgIcon, LinearLayout adChoicesLayout, TextView txtAttribution) {
        nativeAdView.setTitleView(txtTitle);
        nativeAdView.setTextView(txtSummary);
        nativeAdView.setCoverImageView(imgMain);
        nativeAdView.setIconView(imgIcon);

        nativeAd.renderAdView(nativeAdView);
        txtTitle.setText(txtTitle.getText() + " " + txtSummary.getText());
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
