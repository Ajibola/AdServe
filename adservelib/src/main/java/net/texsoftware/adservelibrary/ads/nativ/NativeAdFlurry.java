package net.texsoftware.adservelibrary.ads.nativ;

import android.app.Activity;
import android.view.View;

import net.texsoftware.adservelibrary.component.NativeAdViewItem;
import net.texsoftware.adservelibrary.data.NativeAdNetwork;
import net.texsoftware.adservelibrary.data.NativeAdObject;

/**
 * Created by Jibola on 10/6/2015.
 */
public class NativeAdFlurry extends NativeAd implements com.flurry.android.ads.FlurryAdNativeListener {

    com.flurry.android.ads.FlurryAdNative mFlurryAdNative;
    View adView = null;
    Activity activity = null;

    public NativeAdFlurry(Activity activity, NativeAdNetwork nativeAdNetwork) {
        this.adNetwork = nativeAdNetwork;
        this.activity = activity;
    }

    public void initNativeAd() {
        //Facebook Audience Network Instantiate an AdView view
        mFlurryAdNative = new com.flurry.android.ads.FlurryAdNative(this.activity, adNetwork.getAd_unit_id());
        mFlurryAdNative.setListener(this);
        com.flurry.android.FlurryAgent.onStartSession(this.activity);
        mFlurryAdNative.fetchAd();
    }

    @Override
    public View getNativeAd() throws Exception {
        if (mFlurryAdNative == null) {
            initNativeAd();
        }

        if (adView == null) {
            NativeAdViewItem nativeAdViewItem = new NativeAdViewItem(activity, getNativeAdObject());
            adView = nativeAdViewItem;
        }
        return adView;
    }

    @Override
    public View getMediaView() throws Exception {
        return null;
    }

    public void registerView(View view) {
        if (mFlurryAdNative != null) {
            mFlurryAdNative.setTrackingView(view);
        }
    }

    public void unregisterView(View view) {
        if (mFlurryAdNative != null)
            mFlurryAdNative.removeTrackingView();
    }

    public NativeAdObject getNativeAdObject() {
        NativeAdObject tempNativeAdvert = new NativeAdObject();

        com.flurry.android.ads.FlurryAdNativeAsset headlineAsset = mFlurryAdNative.getAsset("headline");
        com.flurry.android.ads.FlurryAdNativeAsset summaryAsset = mFlurryAdNative.getAsset("summary");
        com.flurry.android.ads.FlurryAdNativeAsset sourceAsset = mFlurryAdNative.getAsset("source");
        com.flurry.android.ads.FlurryAdNativeAsset sponsoredImageUrl = mFlurryAdNative.getAsset("secBrandingLogo");
        com.flurry.android.ads.FlurryAdNativeAsset secHqImageAsset = mFlurryAdNative.getAsset("secHqImage");
        com.flurry.android.ads.FlurryAdNativeAsset secImageAsset = mFlurryAdNative.getAsset("secImage");
        com.flurry.android.ads.FlurryAdNativeAsset ctaAsset = mFlurryAdNative.getAsset("callToAction");

        tempNativeAdvert.setId(String.valueOf(mFlurryAdNative.hashCode()));
        tempNativeAdvert.setTitle(headlineAsset.getValue());
        tempNativeAdvert.setDescription(summaryAsset.getValue());
        tempNativeAdvert.setImage_url(secHqImageAsset.getValue());
        tempNativeAdvert.setIcon_url(secImageAsset.getValue());
        tempNativeAdvert.setSocial_text(sourceAsset.getValue());
        tempNativeAdvert.setCta_text(ctaAsset.getValue());

        return tempNativeAdvert;
    }

    @Override
    public void refreshNativeAd() throws Exception {
        if (mFlurryAdNative != null)
            mFlurryAdNative.fetchAd();
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

        if (mFlurryAdNative != null) {
            mFlurryAdNative.removeTrackingView();
            mFlurryAdNative.destroy();
            mFlurryAdNative = null;
        }
    }

    @Override
    public void onFetched(com.flurry.android.ads.FlurryAdNative flurryAdNative) {
        super.onRequestSuccess();
    }

    @Override
    public void onShowFullscreen(com.flurry.android.ads.FlurryAdNative flurryAdNative) {

    }

    @Override
    public void onCloseFullscreen(com.flurry.android.ads.FlurryAdNative flurryAdNative) {

    }

    @Override
    public void onAppExit(com.flurry.android.ads.FlurryAdNative flurryAdNative) {

    }

    @Override
    public void onClicked(com.flurry.android.ads.FlurryAdNative flurryAdNative) {
        super.onClick();
    }

    @Override
    public void onImpressionLogged(com.flurry.android.ads.FlurryAdNative flurryAdNative) {
        super.onImpressionLogged();
    }

    @Override
    public void onExpanded(com.flurry.android.ads.FlurryAdNative flurryAdNative) {

    }

    @Override
    public void onCollapsed(com.flurry.android.ads.FlurryAdNative flurryAdNative) {

    }

    @Override
    public void onError(com.flurry.android.ads.FlurryAdNative flurryAdNative, com.flurry.android.ads.FlurryAdErrorType flurryAdErrorType, int i) {
        super.onRequestFailed();
    }
}
