package net.texsoftware.adservelibrary.ads.banner;

import android.view.View;

import net.texsoftware.adservelibrary.AdManager;
import net.texsoftware.adservelibrary.data.AdNetwork;
import net.texsoftware.adservelibrary.listeners.BannerAdRequestListener;
import net.texsoftware.adservelibrary.utils.Analytics;

import java.util.Date;

/**
 * Created by Jibola on 10/6/2015.
 */
public abstract class BannerAd {

    Analytics analytics = null;
    BannerAdRequestListener bannerAdRequestListener;
    AdNetwork adNetwork;
    private boolean isLoaded = false;
    private boolean isClicked = false;
    public final long timeStarted = new Date().getTime();

    public BannerAd() {
    }

    public void setAnalytics(Analytics analytics) {
        this.analytics = analytics;
    }

    public void setBannerAdRequestListener(BannerAdRequestListener bannerAdRequestListener) {
        this.bannerAdRequestListener = bannerAdRequestListener;
    }

    //create a new instance of the banner by loading a fresh ad
    public abstract View getBanner() throws Exception;

    public abstract void refreshBanner() throws Exception;

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setAdNetwork(AdNetwork adNetwork) {
        this.adNetwork = adNetwork;
    }

    public void onRequestSuccess() {
        isLoaded = true;
        isClicked = false;
        if (bannerAdRequestListener != null)
            bannerAdRequestListener.onBannerRequestSuccess();

        if (analytics != null)
            analytics.sendActionLabelEvent(AdManager.ADVERT, "Successful Request", this.getClass().getSimpleName(), 0L);
    }

    public void onRequestFailed() {
        isLoaded = false;
        if (bannerAdRequestListener != null)
            bannerAdRequestListener.onBannerRequestFailed();

        if (analytics != null)
            analytics.sendActionLabelEvent(AdManager.ADVERT, "Failed Request", this.getClass().getSimpleName(), 0L);
    }

    public void onImpressionLogged() {
        if (bannerAdRequestListener != null)
            bannerAdRequestListener.onBannerImpressionLogged();

        if (analytics != null)
            analytics.sendActionLabelEvent(AdManager.ADVERT, "Log Impression", this.getClass().getSimpleName(), 0L);
    }

    public void onClick() {
        isClicked = true;

        if (bannerAdRequestListener != null)
            bannerAdRequestListener.onBannerClick();

        if (analytics != null)
            analytics.sendActionLabelEvent(AdManager.ADVERT, "Clicked", this.getClass().getSimpleName(), 0L);
    }

    public boolean isClicked() {
        return isClicked;
    }

    public abstract void onPause();

    public abstract void onResume();

    public boolean onDestroy() {
        return true;
    }
}

