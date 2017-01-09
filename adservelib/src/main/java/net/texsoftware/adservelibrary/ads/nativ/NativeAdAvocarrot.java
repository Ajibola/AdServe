package net.texsoftware.adservelibrary.ads.nativ;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import net.texsoftware.adservelibrary.component.NativeAdViewItem;
import net.texsoftware.adservelibrary.data.NativeAdNetwork;
import net.texsoftware.adservelibrary.data.NativeAdObject;

import java.util.List;


/**
 * Created by Jibola on 10/6/2015.
 */
public class NativeAdAvocarrot extends NativeAd {

    View adView = null;
    Activity activity = null;
    com.avocarrot.androidsdk.AvocarrotCustom avocarrotCustom = null;
    com.avocarrot.androidsdk.AvocarrotCustomListener avocarrotCustomListener = null;
    com.avocarrot.androidsdk.CustomModel customModel = null;

    public NativeAdAvocarrot(Activity activity, NativeAdNetwork nativeAdNetwork) {
        this.adNetwork = nativeAdNetwork;
        this.activity = activity;
    }

    public void initNativeAd() {
        try {
            avocarrotCustom = new com.avocarrot.androidsdk.AvocarrotCustom(activity, adNetwork.getApi_key(), adNetwork.getAd_unit_id());
            avocarrotCustomListener = new com.avocarrot.androidsdk.AvocarrotCustomListener() {
                @Override
                public void onAdLoaded(List<com.avocarrot.androidsdk.CustomModel> ads) {
                    super.onAdLoaded(ads);

                    if ((ads == null) || (ads.size() < 1)) {
                        return;
                    }
                    customModel = ads.get(0);
                    onRequestSuccess();
                }

                @Override
                public void onAdError(com.avocarrot.androidsdk.AdError error) {
                    super.onAdError(error);
                    onRequestFailed();
                }

                @Override
                public void onAdImpression() {
                    super.onAdImpression();
                    onImpressionLogged();
                }

                @Override
                public void onAdClicked() {
                    super.onAdClicked();
                    onClick();
                }
            };
            avocarrotCustom.setListener(avocarrotCustomListener);
            avocarrotCustom.setSandbox(false);

            avocarrotCustom.loadAd();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public View getNativeAd() throws Exception {
        if (adView == null) {
            adView = new NativeAdViewItem(activity, getNativeAdObject());
        }

        return adView;
    }

    public View getMediaView() {
        ImageView imageView = new ImageView(activity);
        com.avocarrot.androidsdk.VideoView videoView = new com.avocarrot.androidsdk.VideoView(activity);
        avocarrotCustom.loadMedia(customModel, imageView, videoView);

        return videoView;
    }

    public void registerView(View view) {
        if (avocarrotCustom != null) {
            com.avocarrot.androidsdk.ui.AdChoicesView adChoicesView = new com.avocarrot.androidsdk.ui.AdChoicesView(activity);
            avocarrotCustom.bindView(customModel, view, adChoicesView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    avocarrotCustom.handleClick(customModel);
                }
            });
        }
    }

    public void unregisterView(View view) {
        if (avocarrotCustom != null)
            avocarrotCustom.clear();
    }

    public NativeAdObject getNativeAdObject() {
        NativeAdObject tempNativeAdvert = new NativeAdObject();

        if (customModel == null)
            return null;

        String adId = customModel.getId();
        String titleForAd = customModel.getTitle();
        String coverImageURL = customModel.getImageUrl();
        String iconImageURL = customModel.getIconUrl();
        String callToAction = customModel.getCTAText();
        String textForAdBody = customModel.getDescription();

        tempNativeAdvert.setId(adId);
        tempNativeAdvert.setTitle(titleForAd);
        tempNativeAdvert.setDescription(textForAdBody);
        tempNativeAdvert.setImage_url(coverImageURL);
        tempNativeAdvert.setIcon_url(iconImageURL);
        tempNativeAdvert.setCta_text(callToAction);

        return tempNativeAdvert;
    }

    @Override
    public void refreshNativeAd() throws Exception {
        if (avocarrotCustom != null)
            avocarrotCustom.loadAd();
        else
            initNativeAd();
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (avocarrotCustom != null) {
            avocarrotCustom.clear();
            avocarrotCustom = null;
        }
    }
}
