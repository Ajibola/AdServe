package net.texsoftware.adservelibrary.ads.interstitial;

import android.app.Activity;

import net.texsoftware.adservelibrary.AdManager;
import net.texsoftware.adservelibrary.data.AdNetwork;
import net.texsoftware.adservelibrary.listeners.InterstitialAdRequestListener;
import net.texsoftware.adservelibrary.utils.Analytics;

import java.util.Date;

/**
 * Created by Jibola on 10/6/2015.
 */
public abstract class InterstitialAd {

    Analytics analytics = null;
    InterstitialAdRequestListener interstitialAdRequestListener;
    AdNetwork adNetwork;
    boolean isLoaded = false;
    public final long timeStarted = new Date().getTime();

    public InterstitialAd() {
    }

    public void setAnalytics(Analytics analytics) {
        this.analytics = analytics;
    }

    public void setInterstitialAdRequestListener(InterstitialAdRequestListener interstitialAdRequestListener) {
        this.interstitialAdRequestListener = interstitialAdRequestListener;
    }

    public abstract void initInterstitial(Activity activity);

    public void showInterstitial() {
        isLoaded = false; //dont show again
    }

    public abstract void loadInterstitial();

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setAdNetwork(AdNetwork adNetwork) {
        this.adNetwork = adNetwork;
    }

    public void onRequestSuccess() {
        isLoaded = true;
        if (interstitialAdRequestListener != null)
            interstitialAdRequestListener.onInterstitialRequestSuccess();

        if(analytics != null)
            analytics.sendActionLabelEvent(AdManager.ADVERT, "Successful Request", this.getClass().getSimpleName(), 0L);
    }

    public void onRequestFailed() {
        isLoaded = false;
        if (interstitialAdRequestListener != null)
            interstitialAdRequestListener.onInterstitialRequestFailed();

        if(analytics != null)
            analytics.sendActionLabelEvent(AdManager.ADVERT, "Failed Request", this.getClass().getSimpleName(), 0L);
    }

    public void onImpressionLogged() {
        if (interstitialAdRequestListener != null)
            interstitialAdRequestListener.onInterstitialImpressionLogged();

        if(analytics != null)
            analytics.sendActionLabelEvent(AdManager.ADVERT, "Log Impression", this.getClass().getSimpleName(), 0L);
    }

    public void onClick() {
        if (interstitialAdRequestListener != null)
            interstitialAdRequestListener.onInterstitialClick();

        isLoaded = false; //dont show again

        if(analytics != null)
            analytics.sendActionLabelEvent(AdManager.ADVERT, "Clicked", this.getClass().getSimpleName(), 0L);
    }

    public abstract void onPause();

    public abstract void onResume();

    public abstract void onDestroy();
}
