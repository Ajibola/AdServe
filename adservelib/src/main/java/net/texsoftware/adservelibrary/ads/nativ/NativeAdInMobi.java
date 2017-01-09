package net.texsoftware.adservelibrary.ads.nativ;

import android.app.Activity;
import android.view.View;

import net.texsoftware.adservelibrary.component.NativeAdViewItem;
import net.texsoftware.adservelibrary.data.NativeAdNetwork;
import net.texsoftware.adservelibrary.data.NativeAdObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * Created by Jibola on 10/6/2015.
 */
public class NativeAdInMobi extends NativeAd implements com.inmobi.ads.InMobiNative.NativeAdListener, com.inmobi.ads.InMobiNative.NativeAdEventsListener {

    View adView = null;
    Activity activity = null;
    com.inmobi.ads.InMobiNative nativeAd = null;


    public NativeAdInMobi(Activity activity, NativeAdNetwork nativeAdNetwork) {
        this.activity = activity;
        this.adNetwork = nativeAdNetwork;
    }

    public void initNativeAd() {
        nativeAd = new com.inmobi.ads.InMobiNative(activity, Long.parseLong(adNetwork.getAd_unit_id()), this);
        nativeAd.setNativeAdEventListener(this);
        nativeAd.load();
    }

    @Override
    public View getNativeAd() throws Exception {
        if (nativeAd == null) {
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
        com.inmobi.ads.InMobiNative.bind(adView, nativeAd);
    }

    public void unregisterView(View view) {
        com.inmobi.ads.InMobiNative.unbind(adView);
    }

    public NativeAdObject getNativeAdObject() {
        NativeAdObject tempNativeAdvert = new NativeAdObject();

        JSONObject content = null;
        try {
            content = new JSONObject((String) nativeAd.getAdContent());

            String adId = String.valueOf(nativeAd.hashCode());
            String titleForAd = (content.has("title")) ? content.getString("title") : "";
            String coverImageURL = (content.has("screenshots")) ? content.getJSONObject("screenshots").getString("url") : "";
            String landingURL = (content.has("click_url")) ? content.getString("click_url") : "";
            String iconImageURL = (content.has("icon")) ? content.getJSONObject("icon").getString("url") : "";
            String callToAction = (content.has("cta")) ? content.getString("cta") : "";
            String textForAdBody = (content.has("description")) ? content.getString("description") : "";

            tempNativeAdvert.setId(adId);
            tempNativeAdvert.setTitle(titleForAd);
            tempNativeAdvert.setDescription(textForAdBody);
            tempNativeAdvert.setImage_url(coverImageURL);
            tempNativeAdvert.setIcon_url(iconImageURL);
            tempNativeAdvert.setCta_text(callToAction);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return tempNativeAdvert;
    }

    @Override
    public void refreshNativeAd() throws Exception {
        if (nativeAd != null)
            nativeAd.load();
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
    public void onAdLoadSucceeded(com.inmobi.ads.InMobiNative inMobiNative) {
        super.onRequestSuccess();
    }

    @Override
    public void onAdLoadFailed(com.inmobi.ads.InMobiNative inMobiNative, com.inmobi.ads.InMobiAdRequestStatus inMobiAdRequestStatus) {
        super.onRequestFailed();
    }

    @Override
    public void onAdDismissed(com.inmobi.ads.InMobiNative inMobiNative) {

    }

    @Override
    public void onAdDisplayed(com.inmobi.ads.InMobiNative inMobiNative) {
        super.onImpressionLogged();
    }

    @Override
    public void onUserLeftApplication(com.inmobi.ads.InMobiNative inMobiNative) {

    }

    @Override
    public void onAdImpressed(com.inmobi.ads.InMobiNative inMobiNative) {
        super.onImpressionLogged();
    }
}
