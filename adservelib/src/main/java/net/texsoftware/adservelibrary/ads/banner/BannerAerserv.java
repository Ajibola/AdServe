package net.texsoftware.adservelibrary.ads.banner;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import net.texsoftware.adservelibrary.data.BannerAdNetwork;

import java.util.List;

/**
 * Created by Jibola on 10/6/2015.
 */
public class BannerAerserv extends BannerAd implements com.aerserv.sdk.AerServEventListener {

    private final Activity activity;
    private com.aerserv.sdk.AerServBanner mAerservBanner = null;
    com.aerserv.sdk.AerServConfig config = null;
    

    public BannerAerserv(Activity activity, BannerAdNetwork bannerAdNetwork) {
        this.adNetwork = bannerAdNetwork;
        this.activity = activity;
        config = new com.aerserv.sdk.AerServConfig(activity, adNetwork.getAd_unit_id()).setEventListener(this);

        initBanner();
    }

    public void initBanner() {
        mAerservBanner = new com.aerserv.sdk.AerServBanner(activity); // Initializing SDK
        mAerservBanner.configure(config).show();
    }

    @Override
    public View getBanner() {
        if (mAerservBanner == null) {
            initBanner();
        }

        ViewGroup parent = (ViewGroup) mAerservBanner.getParent();
        if (parent != null) {
            parent.removeView(mAerservBanner);
            parent.invalidate();
        }
        return mAerservBanner;
    }

    @Override
    public void refreshBanner() {
        if (mAerservBanner != null) {
            mAerservBanner.configure(config).show();
        } else
            initBanner();
    }

    @Override
    public void onResume() {
        mAerservBanner.play();
    }

    @Override
    public void onPause() {
        mAerservBanner.pause();
    }

    @Override
    public boolean onDestroy() {
        super.onDestroy();

        if (mAerservBanner != null) {
            mAerservBanner.kill();
            mAerservBanner = null;
        }
        return true;
    }

    @Override
    public void onAerServEvent(com.aerserv.sdk.AerServEvent aerServEvent, List<Object> list) {
        switch (aerServEvent) {
            case AD_LOADED:
                super.onRequestSuccess();
                break;
            case AD_FAILED:
                super.onRequestFailed();
                break;
            case AD_COMPLETED:
                break;
            case AD_IMPRESSION:
                super.onImpressionLogged();
                break;
            case AD_CLICKED:
                super.onClick();
                break;
        }
    }
}
