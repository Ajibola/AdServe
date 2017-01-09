package net.texsoftware.adservelibrary.ads.nativ;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.texsoftware.adservelibrary.AdManager;
import net.texsoftware.adservelibrary.data.AdNetwork;
import net.texsoftware.adservelibrary.data.NativeAdObject;
import net.texsoftware.adservelibrary.listeners.NativeAdRequestListener;
import net.texsoftware.adservelibrary.utils.Analytics;

import java.util.Date;

/**
 * Created by Jibola on 10/6/2015.
 */
public abstract class NativeAd {

    Analytics analytics = null;
    NativeAdRequestListener nativeAdRequestListener;
    AdNetwork adNetwork;
    private boolean isLoaded = false;
    private boolean isAdded = false;
    private boolean isClicked = false;
    String nativeAdId = "";
    public final long timeStarted = new Date().getTime();

    public NativeAd() {
    }

    public abstract void initNativeAd();

    public void setAnalytics(Analytics analytics) {
        this.analytics = analytics;
    }

    public String getNativeAdId() {
        return nativeAdId;
    }

    public void setNativeAdId(String nativeAdId) {
        this.nativeAdId = nativeAdId;
    }

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
    }

    public void setAdNetwork(AdNetwork adNetwork) {
        this.adNetwork = adNetwork;
    }

    public void setNativeAdRequestListener(NativeAdRequestListener nativeAdRequestListener) {
        this.nativeAdRequestListener = nativeAdRequestListener;
    }

    public NativeAdRequestListener getNativeAdRequestListener() {
        return nativeAdRequestListener;
    }

    public abstract void registerView(View view) throws Exception;

    public abstract void unregisterView(View view) throws Exception;

    public abstract View getNativeAd() throws Exception;

    public void getNativeAd(TextView txtTitle, TextView txtSummary, ImageView imgMain, ImageView imgIcon, LinearLayout adChoicesLayout, TextView txtAttribution) {
    }

    public abstract View getMediaView() throws Exception;

    public abstract NativeAdObject getNativeAdObject();

    public abstract void refreshNativeAd() throws Exception;

    public boolean isLoaded() {
        return isLoaded;
    }

    public void onRequestSuccess() {
        isLoaded = true;
        isClicked = false;
        if (nativeAdRequestListener != null)
            nativeAdRequestListener.onNativeRequestSuccess();

        if (analytics != null)
            analytics.sendActionLabelEvent(AdManager.ADVERT, "Successful Request", this.getClass().getSimpleName(), 0L);
    }

    public void onRequestFailed() {
        isLoaded = false;
        if (nativeAdRequestListener != null)
            nativeAdRequestListener.onNativeRequestFailed();

        if (analytics != null)
            analytics.sendActionLabelEvent(AdManager.ADVERT, "Failed Request", this.getClass().getSimpleName(), 0L);
    }

    public void onImpressionLogged() {
        if (nativeAdRequestListener != null)
            nativeAdRequestListener.onNativeImpressionLogged();

        if (analytics != null)
            analytics.sendActionLabelEvent(AdManager.ADVERT, "Log Impression", this.getClass().getSimpleName(), 0L);
    }

    public void onClick() {
        isClicked = true;

        if (nativeAdRequestListener != null)
            nativeAdRequestListener.onNativeClick();

        if (analytics != null)
            analytics.sendActionLabelEvent(AdManager.ADVERT, "Clicked", this.getClass().getSimpleName(), 0L);
    }

    public boolean isClicked() {
        return isClicked;
    }

    public abstract void onPause();

    public abstract void onResume();

    public void onDestroy() {
    }
}

