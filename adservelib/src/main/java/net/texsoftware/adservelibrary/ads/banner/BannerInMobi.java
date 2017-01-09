package net.texsoftware.adservelibrary.ads.banner;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import net.texsoftware.adservelibrary.data.BannerAdNetwork;

import java.util.Map;

/**
 * Created by Jibola on 10/6/2015.
 */
public class BannerInMobi extends BannerAd implements com.inmobi.ads.InMobiBanner.BannerAdListener {

    com.inmobi.ads.InMobiBanner inMobiBanner;
    RelativeLayout adContainer;
    private final Activity activity;

    public BannerInMobi(Activity activity, BannerAdNetwork bannerAdNetwork) {
        this.activity = activity;
        this.adNetwork = bannerAdNetwork;
        initBanner();
    }

    public void initBanner() {
        inMobiBanner = new com.inmobi.ads.InMobiBanner(activity, Long.parseLong(adNetwork.getAd_unit_id()));
        inMobiBanner.setListener(this);

        adContainer = new RelativeLayout(activity);
        RelativeLayout.LayoutParams bannerLp = new RelativeLayout.LayoutParams(640, 100);
        bannerLp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        bannerLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        adContainer.addView(inMobiBanner);

        inMobiBanner.load();
    }

    @Override
    public View getBanner() throws Exception {
        if (inMobiBanner == null) {
            initBanner();
        }

        ViewGroup parent = (ViewGroup) adContainer.getParent();
        if (parent != null) {
            parent.removeView(adContainer);
            parent.invalidate();
        }
        return adContainer;
    }

    @Override
    public void refreshBanner() throws Exception {
        if (inMobiBanner != null)
            inMobiBanner.load();
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

        if (inMobiBanner != null) {
            inMobiBanner.removeAllViews();
            inMobiBanner = null;
        }
        return true;
    }

    @Override
    public void onAdLoadSucceeded(com.inmobi.ads.InMobiBanner inMobiBanner) {
        super.onRequestSuccess();
    }

    @Override
    public void onAdLoadFailed(com.inmobi.ads.InMobiBanner inMobiBanner, com.inmobi.ads.InMobiAdRequestStatus inMobiAdRequestStatus) {
        super.onRequestFailed();
    }

    @Override
    public void onAdDisplayed(com.inmobi.ads.InMobiBanner inMobiBanner) {
        super.onImpressionLogged();
    }

    @Override
    public void onAdDismissed(com.inmobi.ads.InMobiBanner inMobiBanner) {

    }

    @Override
    public void onAdInteraction(com.inmobi.ads.InMobiBanner inMobiBanner, Map<Object, Object> map) {
        super.onClick();
    }

    @Override
    public void onUserLeftApplication(com.inmobi.ads.InMobiBanner inMobiBanner) {

    }

    @Override
    public void onAdRewardActionCompleted(com.inmobi.ads.InMobiBanner inMobiBanner, Map<Object, Object> map) {

    }
}
