package net.texsoftware.adservelibrary.ads.video;

import android.app.Activity;
import android.content.Intent;

import com.fyber.ads.interstitials.InterstitialAdCloseReason;

import net.texsoftware.adservelibrary.data.VideoInterstitialAdNetwork;
import net.texsoftware.adservelibrary.utils.Logger;

import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Jibola on 10/6/2015.
 */
public class VideoInterstitialFyber extends VideoInterstitialAd implements com.fyber.requesters.RequestCallback {

    protected static final int INTERSTITIAL_REQUEST_CODE = 9012;
    Intent interstitialIntent = null;
    Activity activity = null;

    public VideoInterstitialFyber(Activity activity, VideoInterstitialAdNetwork videoInterstitialAdNetwork) {
        this.adNetwork = videoInterstitialAdNetwork;
        this.activity = activity;
    }

    public void initInterstitial(Activity activity) {
        com.fyber.requesters.InterstitialRequester.create(this)
                .request(activity);
    }

    public void showInterstitial() {
        super.showInterstitial();
        activity.startActivityForResult(interstitialIntent, INTERSTITIAL_REQUEST_CODE);
    }

    public void loadInterstitial() {
        com.fyber.requesters.InterstitialRequester.create(this)
                .request(activity);
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
    public void onAdAvailable(Intent intent) {
        super.onRequestSuccess();
        interstitialIntent = intent;
        isLoaded = true;
    }

    @Override
    public void onAdNotAvailable(com.fyber.ads.AdFormat adFormat) {
        super.onRequestFailed();
        isLoaded = false;
        interstitialIntent = null;
    }

    @Override
    public void onRequestError(com.fyber.requesters.RequestError requestError) {
        interstitialIntent = null;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // handle the closing of the interstitial
        if (resultCode == RESULT_OK && requestCode == INTERSTITIAL_REQUEST_CODE) {
            // check the ad status
            com.fyber.ads.interstitials.InterstitialAdCloseReason adStatus = (com.fyber.ads.interstitials.InterstitialAdCloseReason) data.getSerializableExtra(com.fyber.ads.interstitials.InterstitialActivity.AD_STATUS);
            if (adStatus.equals(com.fyber.ads.interstitials.InterstitialAdCloseReason.ReasonUserClickedOnAd)) {
                // The user clicked on the interstitial, which closed the ad
                Logger.out("The interstitial ad was dismissed because the user clicked it");
            } else if (adStatus.equals(com.fyber.ads.interstitials.InterstitialAdCloseReason.ReasonUserClosedAd)) {
                super.onCompleted();
            } else if (adStatus.equals(com.fyber.ads.interstitials.InterstitialAdCloseReason.ReasonError)) {
                super.onCompleted();
            } else if (adStatus.equals(InterstitialAdCloseReason.ReasonVideoEnded)) {
                super.onCompleted();
            } else if (adStatus.equals(com.fyber.ads.interstitials.InterstitialAdCloseReason.ReasonUnknown)) {
                // The interstitial closed, but the reason why is unknown
                Logger.out("The interstitial ad was dismissed for an unknown reason");
            }
        }
    }
}
