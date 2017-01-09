package net.texsoftware.adservelibrary.ads.nativ;

import android.app.Activity;
import android.view.View;

import net.texsoftware.adservelibrary.data.NativeAdNetwork;
import net.texsoftware.adservelibrary.data.NativeAdObject;
import net.texsoftware.adservelibrary.utils.Util;


/**
 * Created by Jibola on 10/6/2015.
 */
public class NativeAdExGoogle extends NativeAd implements com.facebook.ads.AdListener, com.facebook.ads.ImpressionListener {

    com.google.android.gms.ads.NativeExpressAdView mAdViewGoogle;
    com.google.android.gms.ads.AdRequest adRequestBanner;
    View adView = null;
    Activity activity = null;

    public NativeAdExGoogle(Activity activity, NativeAdNetwork nativeAdNetwork) {
        this.adNetwork = nativeAdNetwork;
        this.activity = activity;
    }

    public void initNativeAd() {
        mAdViewGoogle = new com.google.android.gms.ads.NativeExpressAdView(activity);
        mAdViewGoogle.setAdUnitId(adNetwork.getAd_unit_id());
        mAdViewGoogle.setAdSize(new com.google.android.gms.ads.AdSize(com.google.android.gms.ads.AdSize.FULL_WIDTH, 250));
        mAdViewGoogle.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                onRequestFailed();
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                onClick();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                onRequestSuccess();
            }
        });

        adRequestBanner = new com.google.android.gms.ads.AdRequest.Builder()
                .setLocation(Util.geFastLocation(activity))
                .build();

        mAdViewGoogle.loadAd(adRequestBanner);
    }

    @Override
    public View getNativeAd() {
        return mAdViewGoogle;
    }

    public View getMediaView() {
        return null;
    }

    public void registerView(View view) {

    }

    public void unregisterView(View view) {

    }

    public NativeAdObject getNativeAdObject() {
        setNativeAdId(String.valueOf(this.hashCode()));
        return null;
    }

    @Override
    public void refreshNativeAd() throws Exception {
        if (mAdViewGoogle != null)
            mAdViewGoogle.loadAd(adRequestBanner);
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

        if (mAdViewGoogle != null) {
            mAdViewGoogle.destroy();
            mAdViewGoogle = null;
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
