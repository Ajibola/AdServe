package net.texsoftware.adservelibrary.ads.banner;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import net.texsoftware.adservelibrary.data.BannerAdNetwork;

/**
 * Created by Jibola on 10/6/2015.
 */
public class BannerMopub extends BannerAd implements com.mopub.mobileads.MoPubView.BannerAdListener {

    private com.mopub.mobileads.MoPubView moPubView;
    private final Activity activity;

    public BannerMopub(Activity activity, BannerAdNetwork bannerAdNetwork) {
        this.activity = activity;
        this.adNetwork = bannerAdNetwork;
        initBanner();
    }

    public void initBanner() {
        //Facebook Audience Network Instantiate an AdView view
        moPubView = new com.mopub.mobileads.MoPubView(activity);
        moPubView.setAdUnitId(adNetwork.getAd_unit_id());
        moPubView.loadAd();
        moPubView.setBannerAdListener(this);
    }

    @Override
    public View getBanner() throws Exception {
        if (moPubView == null) {
            initBanner();
        }

        ViewGroup parent = (ViewGroup) moPubView.getParent();
        if (parent != null) {
            parent.removeView(moPubView);
            parent.invalidate();
        }

        return moPubView;
    }

    @Override
    public void refreshBanner() throws Exception {
        if (moPubView != null)
            moPubView.loadAd();
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

        if (moPubView != null) {
            moPubView.removeAllViews();
            moPubView.destroy();
            moPubView = null;
        }
        return true;
    }

    @Override
    public void onBannerLoaded(com.mopub.mobileads.MoPubView banner) {
        super.onRequestSuccess();
    }

    @Override
    public void onBannerFailed(com.mopub.mobileads.MoPubView banner, com.mopub.mobileads.MoPubErrorCode errorCode) {
        super.onRequestFailed();
    }

    @Override
    public void onBannerClicked(com.mopub.mobileads.MoPubView banner) {
        super.onClick();
    }

    @Override
    public void onBannerExpanded(com.mopub.mobileads.MoPubView banner) {

    }

    @Override
    public void onBannerCollapsed(com.mopub.mobileads.MoPubView banner) {

    }
}
