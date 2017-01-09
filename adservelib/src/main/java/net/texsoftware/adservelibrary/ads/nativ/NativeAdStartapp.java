package net.texsoftware.adservelibrary.ads.nativ;

import android.app.Activity;
import android.view.View;

import net.texsoftware.adservelibrary.component.NativeAdViewItem;
import net.texsoftware.adservelibrary.data.NativeAdNetwork;
import net.texsoftware.adservelibrary.data.NativeAdObject;

import java.util.ArrayList;


/**
 * Created by Jibola on 10/6/2015.
 */
public class NativeAdStartapp extends NativeAd implements com.startapp.android.publish.adsCommon.adListeners.AdEventListener {

    com.startapp.android.publish.ads.nativead.StartAppNativeAd startAppNativeAd;
    com.startapp.android.publish.ads.nativead.NativeAdDetails nativeAdDetails;
    View adView = null;
    Activity activity = null;

    public NativeAdStartapp(Activity activity, NativeAdNetwork nativeAdNetwork) {
        this.adNetwork = nativeAdNetwork;
        this.activity = activity;
    }

    public void initNativeAd() {
        startAppNativeAd = new com.startapp.android.publish.ads.nativead.StartAppNativeAd(activity);

        com.startapp.android.publish.ads.nativead.NativeAdPreferences nativePrefs = new com.startapp.android.publish.ads.nativead.NativeAdPreferences()
                .setAdsNumber(1)
                .setAutoBitmapDownload(false)
                .setPrimaryImageSize(4)
                .setSecondaryImageSize(2);

        startAppNativeAd.loadAd(nativePrefs, this);
    }

    @Override
    public View getNativeAd() {
        if (adView == null) {
            adView = new NativeAdViewItem(activity, getNativeAdObject());
        }

        return adView;
    }

    public View getMediaView() {
        return getNativeAd();
    }

    public void registerView(View view) {
        if (startAppNativeAd != null)
            nativeAdDetails.sendImpression(activity);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClick(view);
            }
        });
    }

    public void unregisterView(View view) {
    }

    public void handleClick(View view) {
        nativeAdDetails.sendClick(view.getContext());
    }

    public NativeAdObject getNativeAdObject() {
        NativeAdObject tempNativeAdvert = new NativeAdObject();

        ArrayList<com.startapp.android.publish.ads.nativead.NativeAdDetails> ads = startAppNativeAd.getNativeAds();
        if (ads.size() > 0) {

            nativeAdDetails = ads.get(0);

            String adId = String.valueOf(nativeAdDetails.hashCode());
            String titleForAd = nativeAdDetails.getTitle();
            String coverImageURL = nativeAdDetails.getImageUrl();
            String iconImageURL = nativeAdDetails.getSecondaryImageUrl();
            String callToAction = nativeAdDetails.getCampaignAction().toString();
            String textForAdBody = nativeAdDetails.getDescription();

            tempNativeAdvert.setId(adId);
            tempNativeAdvert.setTitle(titleForAd);
            tempNativeAdvert.setDescription(textForAdBody);
            tempNativeAdvert.setImage_url(coverImageURL);
            tempNativeAdvert.setIcon_url(iconImageURL);
            tempNativeAdvert.setCta_text(callToAction);
        }

        return tempNativeAdvert;
    }

    @Override
    public void refreshNativeAd() throws Exception {
        if (startAppNativeAd != null)
            startAppNativeAd.loadAd(new com.startapp.android.publish.ads.nativead.NativeAdPreferences(), this);
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

        if (startAppNativeAd != null) {
            startAppNativeAd = null;
        }
    }

    @Override
    public void onReceiveAd(com.startapp.android.publish.adsCommon.Ad ad) {
        super.onRequestSuccess();
    }

    @Override
    public void onFailedToReceiveAd(com.startapp.android.publish.adsCommon.Ad ad) {
        super.onRequestFailed();
    }
}
