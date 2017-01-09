package net.texsoftware.adservelibrary.ads.banner;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import net.texsoftware.adservelibrary.data.BannerAdNetwork;
import net.texsoftware.adservelibrary.utils.Util;

/**
 * Created by Jibola on 10/6/2015.
 */
public class BannerNativeExGoogle extends BannerAd {

    com.google.android.gms.ads.NativeExpressAdView mAdViewGoogle;
    com.google.android.gms.ads.AdRequest adRequestBanner;
    private final Activity activity;

    public BannerNativeExGoogle(Activity activity, BannerAdNetwork bannerAdNetwork) {
        this.activity = activity;
        this.adNetwork = bannerAdNetwork;
        initBanner();
    }

    public void initBanner() {
        mAdViewGoogle = new com.google.android.gms.ads.NativeExpressAdView(activity);
        mAdViewGoogle.setAdUnitId(adNetwork.getAd_unit_id());
        mAdViewGoogle.setAdSize(new com.google.android.gms.ads.AdSize(com.google.android.gms.ads.AdSize.FULL_WIDTH, 80));
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
    public View getBanner() throws Exception {
        if (mAdViewGoogle == null) {
            initBanner();
        }

        ViewGroup parent = (ViewGroup) mAdViewGoogle.getParent();
        if (parent != null) {
            parent.removeView(mAdViewGoogle);
            parent.invalidate();
        }
        return mAdViewGoogle;
    }

    @Override
    public void refreshBanner() throws Exception {
        if (mAdViewGoogle != null)
            mAdViewGoogle.loadAd(adRequestBanner);
        else
            initBanner();
    }

    @Override
    public void onResume() {
        if (mAdViewGoogle != null)
            mAdViewGoogle.resume();
    }

    @Override
    public void onPause() {

        if (mAdViewGoogle != null)
            mAdViewGoogle.pause();
    }

    @Override
    public boolean onDestroy() {
        super.onDestroy();

        if (mAdViewGoogle != null) {
            mAdViewGoogle.removeAllViews();
            mAdViewGoogle.destroy();
            mAdViewGoogle = null;
        }
        return true;
    }
}
