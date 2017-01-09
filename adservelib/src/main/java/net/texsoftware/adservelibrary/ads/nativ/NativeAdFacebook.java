package net.texsoftware.adservelibrary.ads.nativ;

import android.app.Activity;
import android.view.View;

import net.texsoftware.adservelibrary.component.NativeAdViewItem;
import net.texsoftware.adservelibrary.data.NativeAdNetwork;
import net.texsoftware.adservelibrary.data.NativeAdObject;


/**
 * Created by Jibola on 10/6/2015.
 */
public class NativeAdFacebook extends net.texsoftware.adservelibrary.ads.nativ.NativeAd implements com.facebook.ads.AdListener, com.facebook.ads.ImpressionListener {

    com.facebook.ads.NativeAd fbNativeAd;
    View adView = null;
    Activity activity = null;

    public NativeAdFacebook(Activity activity, NativeAdNetwork nativeAdNetwork) {
        this.adNetwork = nativeAdNetwork;
        this.activity = activity;
    }

    public void initNativeAd() {
        fbNativeAd = new com.facebook.ads.NativeAd(activity, adNetwork.getAd_unit_id());
        fbNativeAd.setImpressionListener(this);
        fbNativeAd.setAdListener(this);
        fbNativeAd.setMediaViewAutoplay(true);
        fbNativeAd.loadAd();
    }

    @Override
    public View getNativeAd() {
        if (adView == null) {
            adView = new NativeAdViewItem(activity, getNativeAdObject());
        }

        return adView;
    }

    public View getMediaView() {
        com.facebook.ads.MediaView mediaView = new com.facebook.ads.MediaView(activity);
        mediaView.setNativeAd(fbNativeAd);
        return mediaView;
    }

    public void registerView(View view) {
        if (fbNativeAd != null)
            fbNativeAd.registerViewForInteraction(view);
    }

    public void unregisterView(View view) {
        if (fbNativeAd != null)
            fbNativeAd.unregisterView();
    }

    public NativeAdObject getNativeAdObject() {
        NativeAdObject tempNativeAdvert = new NativeAdObject();

        String adId = fbNativeAd.getId();
        String titleForAd = fbNativeAd.getAdTitle();
        com.facebook.ads.NativeAd.Image coverImage = fbNativeAd.getAdCoverImage();
        com.facebook.ads.NativeAd.Image iconForAd = fbNativeAd.getAdIcon();
        String socialContextForAd = fbNativeAd.getAdSocialContext();
        String callToAction = fbNativeAd.getAdCallToAction();
        String textForAdBody = fbNativeAd.getAdBody();
        com.facebook.ads.NativeAd.Rating appRatingForAd = fbNativeAd.getAdStarRating();

        tempNativeAdvert.setId(adId);
        tempNativeAdvert.setTitle(titleForAd);
        tempNativeAdvert.setDescription(textForAdBody);
        tempNativeAdvert.setImage_url(coverImage.getUrl());
        tempNativeAdvert.setIcon_url(iconForAd.getUrl());
        tempNativeAdvert.setSocial_text(socialContextForAd);
        tempNativeAdvert.setCta_text(callToAction);

        return tempNativeAdvert;
    }

    @Override
    public void refreshNativeAd() throws Exception {
        if (fbNativeAd != null)
            fbNativeAd.loadAd();
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

        if (fbNativeAd != null) {
            fbNativeAd.unregisterView();
            fbNativeAd.destroy();
            fbNativeAd = null;
        }
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
