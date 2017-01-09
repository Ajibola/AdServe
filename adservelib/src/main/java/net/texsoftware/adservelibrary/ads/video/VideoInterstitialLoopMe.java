package net.texsoftware.adservelibrary.ads.video;

import android.app.Activity;

import net.texsoftware.adservelibrary.data.VideoInterstitialAdNetwork;

/**
 * Created by Jibola on 10/6/2015.
 */
public class VideoInterstitialLoopMe extends VideoInterstitialAd implements com.loopme.LoopMeInterstitial.Listener {

    private com.loopme.LoopMeInterstitial mInterstitial = null;

    public VideoInterstitialLoopMe(Activity activity, VideoInterstitialAdNetwork videoInterstitialAdNetwork) {
        this.adNetwork = videoInterstitialAdNetwork;
    }

    public void initInterstitial(Activity activity) {
        mInterstitial = com.loopme.LoopMeInterstitial.getInstance(adNetwork.getAd_unit_id(), activity);
        mInterstitial.setListener(this);

        mInterstitial.load();
    }

    public void showInterstitial() {
        super.showInterstitial();

        if (mInterstitial != null && mInterstitial.isReady())
            mInterstitial.show();
    }

    public void loadInterstitial() {
        if (mInterstitial != null)
            mInterstitial.load();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onLoopMeInterstitialLoadSuccess(com.loopme.LoopMeInterstitial interstitial) {
        super.onRequestSuccess();
    }

    @Override
    public void onLoopMeInterstitialLoadFail(com.loopme.LoopMeInterstitial interstitial, com.loopme.common.LoopMeError error) {
        super.onRequestFailed();
    }

    @Override
    public void onLoopMeInterstitialShow(com.loopme.LoopMeInterstitial interstitial) {
        super.onImpressionLogged();
    }

    @Override
    public void onLoopMeInterstitialHide(com.loopme.LoopMeInterstitial interstitial) {

    }

    @Override
    public void onLoopMeInterstitialClicked(com.loopme.LoopMeInterstitial interstitial) {
        super.onClick();
    }

    @Override
    public void onLoopMeInterstitialLeaveApp(com.loopme.LoopMeInterstitial interstitial) {

    }

    @Override
    public void onLoopMeInterstitialExpired(com.loopme.LoopMeInterstitial interstitial) {

    }

    @Override
    public void onLoopMeInterstitialVideoDidReachEnd(com.loopme.LoopMeInterstitial interstitial) {
        super.onCompleted();
    }
}
