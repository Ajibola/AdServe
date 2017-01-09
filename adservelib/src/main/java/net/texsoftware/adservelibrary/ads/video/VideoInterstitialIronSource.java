package net.texsoftware.adservelibrary.ads.video;

import android.app.Activity;
import android.provider.Settings;

import net.texsoftware.adservelibrary.data.VideoInterstitialAdNetwork;

/**
 * Created by Jibola on 10/6/2015.
 */
public class VideoInterstitialIronSource extends VideoInterstitialAd implements com.supersonic.mediationsdk.sdk.RewardedVideoListener {

    com.supersonic.mediationsdk.sdk.Supersonic mMediationAgent;
    Activity activity = null;

    public VideoInterstitialIronSource(Activity activity, VideoInterstitialAdNetwork videoInterstitialAdNetwork) {
        this.adNetwork = videoInterstitialAdNetwork;
        this.activity = activity;
    }

    public void initInterstitial(Activity activity) {
        mMediationAgent = com.supersonic.mediationsdk.sdk.SupersonicFactory.getInstance();
        try {
            String adInfo = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID);
            mMediationAgent.initRewardedVideo(activity, adNetwork.getApi_key(), adInfo);
            //Set the Rewarded Video Listener
            mMediationAgent.setRewardedVideoListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showInterstitial() {
        super.showInterstitial();

        if (mMediationAgent.isRewardedVideoAvailable())
            mMediationAgent.showRewardedVideo(adNetwork.getAd_unit_id());
    }

    public void loadInterstitial() {
    }

    @Override
    public void onPause() {
        if (mMediationAgent != null) {
            mMediationAgent.onPause(activity);
        }
    }

    @Override
    public void onResume() {
        if (mMediationAgent != null) {
            mMediationAgent.onResume(activity);
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onRewardedVideoInitSuccess() {
    }

    @Override
    public void onRewardedVideoInitFail(com.supersonic.mediationsdk.logger.SupersonicError supersonicError) {
    }

    @Override
    public void onRewardedVideoAdOpened() {
        super.onImpressionLogged();
    }

    @Override
    public void onRewardedVideoAdClosed() {
    }

    @Override
    public void onVideoAvailabilityChanged(boolean available) {
        if (available)
            super.onRequestSuccess();
        else
            super.onRequestFailed();
    }

    @Override
    public void onVideoStart() {
        super.onStarted();
    }

    @Override
    public void onVideoEnd() {
        super.onCompleted();
    }

    @Override
    public void onRewardedVideoAdRewarded(com.supersonic.mediationsdk.model.Placement placement) {
    }

    @Override
    public void onRewardedVideoShowFail(com.supersonic.mediationsdk.logger.SupersonicError supersonicError) {
    }

}
