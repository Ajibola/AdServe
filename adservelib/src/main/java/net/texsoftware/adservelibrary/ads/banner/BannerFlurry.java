package net.texsoftware.adservelibrary.ads.banner;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import net.texsoftware.adservelibrary.data.BannerAdNetwork;

/**
 * Created by Jibola on 10/6/2015.
 */
public class BannerFlurry extends BannerAd implements com.flurry.android.ads.FlurryAdBannerListener {

    private final Activity activity;
    private RelativeLayout mBanner;
    private com.flurry.android.ads.FlurryAdBanner mFlurryAdBanner = null;

    public BannerFlurry(Activity activity, BannerAdNetwork bannerAdNetwork) {
        this.activity = activity;
        this.adNetwork = bannerAdNetwork;
        initBanner();
    }

    public void initBanner() {
        // Initializing SDK
        mBanner = new RelativeLayout(activity);
        mFlurryAdBanner = new com.flurry.android.ads.FlurryAdBanner(activity, mBanner, adNetwork.getAd_unit_id());
        mFlurryAdBanner.setListener(this);
        com.flurry.android.FlurryAgent.onStartSession(activity);
        mFlurryAdBanner.fetchAd();
    }

    @Override
    public View getBanner() {
        if (mBanner == null) {
            initBanner();
        }

        ViewGroup parent = (ViewGroup) mBanner.getParent();
        if (parent != null) {
            parent.removeView(mBanner);
            parent.invalidate();
        }
        return mBanner;
    }

    @Override
    public void refreshBanner() {
        if (mFlurryAdBanner != null)
            mFlurryAdBanner.fetchAd();
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

        if (mFlurryAdBanner != null) {
            com.flurry.android.FlurryAgent.onEndSession(activity);
            mFlurryAdBanner.destroy();
            mFlurryAdBanner = null;
        }

        return true;
    }

    @Override
    public void onFetched(com.flurry.android.ads.FlurryAdBanner flurryAdBanner) {
        super.onRequestSuccess();
        mFlurryAdBanner.displayAd();
    }

    @Override
    public void onRendered(com.flurry.android.ads.FlurryAdBanner flurryAdBanner) {
        super.onImpressionLogged();
    }

    @Override
    public void onShowFullscreen(com.flurry.android.ads.FlurryAdBanner flurryAdBanner) {

    }

    @Override
    public void onCloseFullscreen(com.flurry.android.ads.FlurryAdBanner flurryAdBanner) {

    }

    @Override
    public void onAppExit(com.flurry.android.ads.FlurryAdBanner flurryAdBanner) {

    }

    @Override
    public void onClicked(com.flurry.android.ads.FlurryAdBanner flurryAdBanner) {
        super.onClick();
    }

    @Override
    public void onVideoCompleted(com.flurry.android.ads.FlurryAdBanner flurryAdBanner) {

    }

    @Override
    public void onError(com.flurry.android.ads.FlurryAdBanner flurryAdBanner, com.flurry.android.ads.FlurryAdErrorType flurryAdErrorType, int i) {
        super.onRequestFailed();
    }
}
