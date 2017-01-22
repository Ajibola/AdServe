package net.texsoftware.adservelibrary.ads.nativ;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.texsoftware.adservelibrary.R;
import net.texsoftware.adservelibrary.data.NativeAdNetwork;
import net.texsoftware.adservelibrary.data.NativeAdObject;


/**
 * Created by Jibola on 10/6/2015.
 */
public class NativeAdMopub extends NativeAd implements com.mopub.nativeads.MoPubNative.MoPubNativeNetworkListener, com.mopub.nativeads.NativeAd.MoPubNativeEventListener {

    View adView = null;
    Activity activity = null;
    com.mopub.nativeads.MoPubNative moPubNative = null;
    com.mopub.nativeads.NativeAd nativeAdMopub;

    public NativeAdMopub(Activity activity, NativeAdNetwork nativeAdNetwork) {
        this.activity = activity;
        this.adNetwork = nativeAdNetwork;
    }

    public void initNativeAd() {
        moPubNative = new com.mopub.nativeads.MoPubNative(activity, adNetwork.getAd_unit_id(), this);
        com.mopub.nativeads.ViewBinder viewBinder = new com.mopub.nativeads.ViewBinder.Builder(R.layout.native_ad)
                .mainImageId(R.id.mainImage)
                .iconImageId(R.id.imgAdChoices)
                .titleId(R.id.txtTitle)
                .textId(R.id.txtSummary)
                .build();
        com.mopub.nativeads.MoPubStaticNativeAdRenderer moPubStaticNativeAdRenderer = new com.mopub.nativeads.MoPubStaticNativeAdRenderer(viewBinder);
        moPubNative.registerAdRenderer(moPubStaticNativeAdRenderer);
        moPubNative.makeRequest();
    }

    @Override
    public View getNativeAd() throws Exception {
        adView = nativeAdMopub.createAdView(activity, null);
        nativeAdMopub.renderAdView(adView);
        nativeAdMopub.prepare(adView);
        return adView;
    }

    @Override
    public void getNativeAd(View viewLayout, TextView txtTitle, TextView txtSummary, ImageView imgMain, ImageView imgIcon, LinearLayout adChoicesLayout, TextView txtAttribution) {
        adView = nativeAdMopub.createAdView(activity, null);

        txtTitle = (TextView) adView.findViewById(R.id.txtTitle);
        txtSummary = (TextView) adView.findViewById(R.id.txtSummary);
        imgMain = (ImageView) adView.findViewById(R.id.mainImage);
        imgIcon = (ImageView) adView.findViewById(R.id.imgAdChoices);

        nativeAdMopub.renderAdView(viewLayout);
        txtTitle.setText(txtTitle.getText() + " " + txtSummary.getText());

        nativeAdMopub.prepare(viewLayout);
    }

    public View getMediaView() {
        com.mopub.nativeads.MediaViewBinder mediaViewBinder = new com.mopub.nativeads.MediaViewBinder.Builder(R.layout.native_ad)
                .mediaLayoutId(R.id.mainImage)
                .iconImageId(R.id.imgAdChoices)
                .titleId(R.id.txtTitle)
                .textId(R.id.txtSummary)
                .build();

        com.mopub.nativeads.MoPubVideoNativeAdRenderer moPubVideoNativeAdRenderer = new com.mopub.nativeads.MoPubVideoNativeAdRenderer(mediaViewBinder);
        moPubNative.registerAdRenderer(moPubVideoNativeAdRenderer);

        View mediaView = nativeAdMopub.createAdView(activity, null);
        nativeAdMopub.renderAdView(mediaView);
        nativeAdMopub.prepare(mediaView);

        return mediaView;
    }

    public void registerView(View view) {
        if (nativeAdMopub != null)
            nativeAdMopub.prepare(view);
    }

    public void unregisterView(View view) {
        if (nativeAdMopub != null)
            nativeAdMopub.clear(view);
    }

    public NativeAdObject getNativeAdObject() {
        setNativeAdId(String.valueOf(this.hashCode()));
        return null;
    }

    @Override
    public void refreshNativeAd() throws Exception {
        if (moPubNative != null)
            moPubNative.makeRequest();
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
    }

    @Override
    public void onNativeLoad(com.mopub.nativeads.NativeAd nativeAd) {
        this.nativeAdMopub = nativeAd;
        nativeAdMopub.setMoPubNativeEventListener(this);
        super.onRequestSuccess();
    }

    @Override
    public void onNativeFail(com.mopub.nativeads.NativeErrorCode errorCode) {
        super.onRequestFailed();
    }

    @Override
    public void onImpression(View view) {
        super.onImpressionLogged();
    }

    @Override
    public void onClick(View view) {
        super.onClick();
    }
}
