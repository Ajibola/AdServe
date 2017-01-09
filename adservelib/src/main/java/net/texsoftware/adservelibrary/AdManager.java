package net.texsoftware.adservelibrary;

import android.app.Activity;

import com.google.gson.Gson;

import net.texsoftware.adservelibrary.ads.banner.BannerAd;
import net.texsoftware.adservelibrary.ads.interstitial.InterstitialAd;
import net.texsoftware.adservelibrary.ads.nativ.NativeAd;
import net.texsoftware.adservelibrary.ads.video.VideoInterstitialAd;
import net.texsoftware.adservelibrary.data.AdNetwork;
import net.texsoftware.adservelibrary.data.BannerAdNetwork;
import net.texsoftware.adservelibrary.data.InterstitialAdNetwork;
import net.texsoftware.adservelibrary.data.NativeAdNetwork;
import net.texsoftware.adservelibrary.data.SDKNetwork;
import net.texsoftware.adservelibrary.data.VideoInterstitialAdNetwork;
import net.texsoftware.adservelibrary.listeners.BannerAdRequestListener;
import net.texsoftware.adservelibrary.listeners.InterstitialAdRequestListener;
import net.texsoftware.adservelibrary.listeners.NativeAdRequestListener;
import net.texsoftware.adservelibrary.listeners.VideoAdRequestListener;
import net.texsoftware.adservelibrary.utils.Analytics;
import net.texsoftware.adservelibrary.utils.Logger;
import net.texsoftware.adservelibrary.utils.Util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jibola on 10/7/2015.
 */
public final class AdManager {
    public static final String ADVERT = "ADVERT";
    public static int BANNER_AD_INDEX = 0;
    public static int INTERSTITIAL_AD_INDEX = 0;
    public static int VIDEO_INTERSTITIAL_AD_INDEX = 0;
    public static int NATIVE_AD_INDEX = 0;
    public static int AD_RETRY_COUNT = 30;

    Analytics analyticsProvider = null;
    public static List<BannerAdNetwork> bannerAdNetworks = new ArrayList<BannerAdNetwork>();
    public static List<InterstitialAdNetwork> interstitialAdNetworks = new ArrayList<InterstitialAdNetwork>();
    public static List<VideoInterstitialAdNetwork> videoInterstitialAdNetworks = new ArrayList<VideoInterstitialAdNetwork>();
    public static List<NativeAdNetwork> nativeAdNetworks = new ArrayList<NativeAdNetwork>();

    public AdManager(final Activity activity, Analytics analytics, String fileName) {
        loadAdNetworks(activity, fileName);
        analyticsProvider = analytics;
    }

    public static void initSDKs(List<SDKNetwork> adSDKs, Activity activity) {
        try {
            for (int i = 0; i < adSDKs.size(); i++) {
                AdNetwork adNetwork = adSDKs.get(i);
                switch (adNetwork.getLibrary_class_name()) {
                    case "com.facebook.ads.AdNetwork":
                        if (Util.isClassAvailable(adNetwork.getLibrary_class_name())) {
                        }
                        break;
                    case "com.flurry.android.FlurryAgent":
                        if (Util.isClassAvailable(adNetwork.getLibrary_class_name())) {
                            new com.flurry.android.FlurryAgent.Builder()
                                    .build(activity, adNetwork.getApi_key());

                            com.flurry.android.FlurryAgent.init(activity, adNetwork.getApi_key());
                            com.flurry.android.FlurryAgent.setCaptureUncaughtExceptions(true);
                            com.flurry.android.FlurryAgent.setLogEnabled(true);
                            com.flurry.android.FlurryAgent.setLogEvents(true);
                            com.flurry.android.FlurryAgent.setReportLocation(true);
                            com.flurry.android.FlurryAgent.setCaptureUncaughtExceptions(true);
                        }
                        break;
                    case "com.aerserv.sdk.AerServSdk":
                        if (Util.isClassAvailable(adNetwork.getLibrary_class_name())) {
                            com.aerserv.sdk.AerServSdk.init(activity, adNetwork.getApi_key());
                        }
                        break;
                    case "com.ampiri.sdk.Ampiri":
                        if (Util.isClassAvailable(adNetwork.getLibrary_class_name())) {
                            com.ampiri.sdk.Ampiri.sdkVersion();
                        }
                        break;
                    case "com.adcolony.sdk.AdColony":
                        if (Util.isClassAvailable(adNetwork.getLibrary_class_name())) {
                            com.adcolony.sdk.AdColony.configure(activity, adNetwork.getApi_key(), adNetwork.getAd_unit_id());
                        }
                        break;
                    case "com.google.android.gms.ads.MobileAds":
                        if (Util.isClassAvailable(adNetwork.getLibrary_class_name())) {
                            com.google.android.gms.ads.MobileAds.initialize(activity, adNetwork.getApi_key());
                        }
                        break;
                    case "com.inmobi.sdk.InMobiSdk":
                        if (Util.isClassAvailable(adNetwork.getLibrary_class_name())) {
                            com.google.android.gms.ads.MobileAds.initialize(activity, adNetwork.getApi_key());
                            com.inmobi.sdk.InMobiSdk.init(activity, adNetwork.getApi_key());
                        }
                        break;
                    case "com.fyber.Fyber":
                        if (Util.isClassAvailable(adNetwork.getLibrary_class_name())) {
                            com.fyber.Fyber.with(adNetwork.getApi_key(), activity)
                                    .withSecurityToken("77f2ca00f2f0ac23a4cd29de08f10915")
                                    .start();
                        }
                        break;
                    case "com.heyzap.sdk.ads.HeyzapAds":
                        if (Util.isClassAvailable(adNetwork.getLibrary_class_name())) {
                            com.heyzap.sdk.ads.HeyzapAds.start(adNetwork.getApi_key(), activity);
                        }
                        break;
                    case "io.presage.Presage":
                        if (Util.isClassAvailable(adNetwork.getLibrary_class_name())) {
                            io.presage.Presage.getInstance().setContext(activity.getBaseContext());
                            io.presage.Presage.getInstance().start();
                        }
                        break;
                    case "com.applovin.sdk.AppLovinSdk":
                        if (Util.isClassAvailable(adNetwork.getLibrary_class_name())) {
                            com.applovin.sdk.AppLovinSdk.initializeSdk(activity);

                        }
                        break;
                    case "com.supersonic.mediationsdk.sdk.Supersonic":
                        if (Util.isClassAvailable(adNetwork.getLibrary_class_name())) {
                            com.supersonic.mediationsdk.sdk.Supersonic mMediationAgent = com.supersonic.mediationsdk.sdk.SupersonicFactory.getInstance();
                        }
                        break;
                    case "com.startapp.android.publish.adsCommon.StartAppSDK":
                        if (Util.isClassAvailable(adNetwork.getLibrary_class_name())) {
                            com.startapp.android.publish.adsCommon.StartAppSDK.init(activity, adNetwork.getApi_key(), false);
                            com.startapp.android.publish.adsCommon.StartAppAd.disableSplash();
                            com.startapp.android.publish.adsCommon.StartAppAd.disableAutoInterstitial();
                        }
                        break;
                    case "com.vungle.publisher.VunglePub":
                        if (Util.isClassAvailable(adNetwork.getLibrary_class_name())) {
                            com.vungle.publisher.VunglePub vunglePub = com.vungle.publisher.VunglePub.getInstance();
                            vunglePub.init(activity, adNetwork.getApi_key());
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception ex) {
            Logger.err(ex);
        }
    }

    public static void loadAdNetworks(Activity activity, String fileName) {
        try {
            String adNetworkJSON = Util.readResourceFile(activity, fileName);

            JSONObject dataJSON = new JSONObject(adNetworkJSON);
            JSONArray sdkNetworksArrStr = dataJSON.getJSONArray("ad_networks");
            JSONArray bannerAdsArrStr = dataJSON.getJSONArray("banner_ads");
            JSONArray interstitialAdsArrStr = dataJSON.getJSONArray("interstitial_ads");
            JSONArray videoInterstitialAdsArrStr = dataJSON.getJSONArray("video_interstitial_ads");
            JSONArray nativeAdsArrStr = dataJSON.getJSONArray("native_ads");

            Gson gson = new Gson();
            SDKNetwork[] sdkNetworksArr = gson.fromJson(sdkNetworksArrStr.toString(), SDKNetwork[].class);
            BannerAdNetwork[] bannerAdsArr = gson.fromJson(bannerAdsArrStr.toString(), BannerAdNetwork[].class);
            InterstitialAdNetwork[] interstitialAdsArr = gson.fromJson(interstitialAdsArrStr.toString(), InterstitialAdNetwork[].class);
            VideoInterstitialAdNetwork[] videoInterstitialAdsArr = gson.fromJson(videoInterstitialAdsArrStr.toString(), VideoInterstitialAdNetwork[].class);
            NativeAdNetwork[] nativeAdsArr = gson.fromJson(nativeAdsArrStr.toString(), NativeAdNetwork[].class);

            initSDKs(Arrays.asList(sdkNetworksArr), activity);
            bannerAdNetworks = Arrays.asList(bannerAdsArr);
            interstitialAdNetworks = Arrays.asList(interstitialAdsArr);
            videoInterstitialAdNetworks = Arrays.asList(videoInterstitialAdsArr);
            nativeAdNetworks = Arrays.asList(nativeAdsArr);
        } catch (Exception ex) {
            Logger.err(ex);
        }
    }

    public NativeAd loadNativeAd(Activity activity, NativeAdRequestListener nativeAdRequestListener) {
        NativeAd nativeAd = null;
        try {
            if (nativeAdNetworks.size() > NATIVE_AD_INDEX) {
                NativeAdNetwork nativeAdNetwork = nativeAdNetworks.get(NATIVE_AD_INDEX);

                if (!Util.isClassAvailable(nativeAdNetwork.getLibrary_class_name()))
                    return null;

                    Class<?> objClass = Class.forName(nativeAdNetwork.getClass_name());
                Constructor<?> constructor = objClass.getConstructor(Activity.class, NativeAdNetwork.class);
                nativeAd = (NativeAd) constructor.newInstance(activity, nativeAdNetwork);
                nativeAd.setNativeAdRequestListener(nativeAdRequestListener);
                nativeAd.setNativeAdId(String.valueOf(nativeAd.hashCode()));
                nativeAd.initNativeAd();
                if (analyticsProvider != null) {
                    nativeAd.setAnalytics(analyticsProvider);
                }
            }
        } catch (Exception ex) {
            Logger.err(ex);
        }

        return nativeAd;
    }

    public BannerAd loadBannerAd(Activity activity, BannerAdRequestListener requestListener) {
        BannerAd bannerAd = null;
        try {
            if (bannerAdNetworks.size() > BANNER_AD_INDEX) {
                BannerAdNetwork bannerAdNetwork = bannerAdNetworks.get(BANNER_AD_INDEX);

                if (!Util.isClassAvailable(bannerAdNetwork.getLibrary_class_name()))
                    return null;

                Class<?> objClass = Class.forName(bannerAdNetwork.getClass_name());
                Constructor<?> constructor = objClass.getConstructor(Activity.class, BannerAdNetwork.class);
                bannerAd = (BannerAd) constructor.newInstance(activity, bannerAdNetwork);
                bannerAd.setBannerAdRequestListener(requestListener);
                if (analyticsProvider != null) {
                    bannerAd.setAnalytics(analyticsProvider);
                }
            }
        } catch (Exception ex) {
            Logger.err(ex);
        }
        return bannerAd;
    }

    public InterstitialAd loadInterstitialAd(Activity activity, InterstitialAdRequestListener interstitialAdRequestListener) {
        InterstitialAd interstitialAd = null;
        try {
            if (interstitialAdNetworks.size() > INTERSTITIAL_AD_INDEX) {
                InterstitialAdNetwork interstitialAdNetwork = interstitialAdNetworks.get(INTERSTITIAL_AD_INDEX);

                if (!Util.isClassAvailable(interstitialAdNetwork.getLibrary_class_name()))
                    return null;

                Class<?> objClass1 = Class.forName(interstitialAdNetwork.getClass_name());
                Constructor<?> constructor1 = objClass1.getConstructor(Activity.class, InterstitialAdNetwork.class);

                interstitialAd = (InterstitialAd) constructor1.newInstance(activity, interstitialAdNetwork);
                interstitialAd.setInterstitialAdRequestListener(interstitialAdRequestListener);
                interstitialAd.setAdNetwork(interstitialAdNetwork);
                interstitialAd.initInterstitial(activity);
                if (analyticsProvider != null) {
                    interstitialAd.setAnalytics(analyticsProvider);
                }
            }
        } catch (Exception ex) {
            Logger.err(ex);
        }
        return interstitialAd;
    }

    public VideoInterstitialAd loadVideoInterstitialAd(Activity activity, VideoAdRequestListener videoAdRequestListener) {
        VideoInterstitialAd videoInterstitialAd = null;
        try {
            if (videoInterstitialAdNetworks.size() > VIDEO_INTERSTITIAL_AD_INDEX) {
                VideoInterstitialAdNetwork videoInterstitialAdNetwork = videoInterstitialAdNetworks.get(VIDEO_INTERSTITIAL_AD_INDEX);

                if (!Util.isClassAvailable(videoInterstitialAdNetwork.getLibrary_class_name()))
                    return null;

                Class<?> objClass1 = Class.forName(videoInterstitialAdNetwork.getClass_name());
                Constructor<?> constructor1 = objClass1.getConstructor(Activity.class, VideoInterstitialAdNetwork.class);

                videoInterstitialAd = (VideoInterstitialAd) constructor1.newInstance(activity, videoInterstitialAdNetwork);
                videoInterstitialAd.setInterstitialAdRequestListener(videoAdRequestListener);
                videoInterstitialAd.setAdNetwork(videoInterstitialAdNetwork);
                videoInterstitialAd.initInterstitial(activity);
                if (analyticsProvider != null) {
                    videoInterstitialAd.setAnalytics(analyticsProvider);
                }
            }
        } catch (Exception ex) {
            Logger.err(ex);
        }
        return videoInterstitialAd;
    }

    public void swapNativeAd() {
        try {
            if (nativeAdNetworks != null && nativeAdNetworks.size() > 0) {
                NATIVE_AD_INDEX = NATIVE_AD_INDEX + 1;

                if (NATIVE_AD_INDEX >= nativeAdNetworks.size()) {
                    NATIVE_AD_INDEX = 0;
                }
            }
        } catch (Exception ex) {
            Logger.err(ex);
        }
    }

    public void swapBannerAd() {
        try {
            if (bannerAdNetworks != null && bannerAdNetworks.size() > 0) {
                BANNER_AD_INDEX = BANNER_AD_INDEX + 1;

                if (BANNER_AD_INDEX >= bannerAdNetworks.size()) {
                    BANNER_AD_INDEX = 0;
                }
            }
        } catch (Exception ex) {
            Logger.err(ex);
        }
    }

    public void swapInterstitialAd() {
        try {
            if (interstitialAdNetworks != null && interstitialAdNetworks.size() > 0) {
                INTERSTITIAL_AD_INDEX = INTERSTITIAL_AD_INDEX + 1;

                if (INTERSTITIAL_AD_INDEX >= interstitialAdNetworks.size()) {
                    INTERSTITIAL_AD_INDEX = 0;
                }
            }
        } catch (Exception ex) {
            Logger.err(ex);
        }
    }

    public void swapVideoInterstitialAd() {
        try {
            if (videoInterstitialAdNetworks != null && videoInterstitialAdNetworks.size() > 0) {
                VIDEO_INTERSTITIAL_AD_INDEX = VIDEO_INTERSTITIAL_AD_INDEX + 1;

                if (VIDEO_INTERSTITIAL_AD_INDEX >= videoInterstitialAdNetworks.size()) {
                    VIDEO_INTERSTITIAL_AD_INDEX = 0;
                }
            }
        } catch (Exception ex) {
            Logger.err(ex);
        }
    }
}
