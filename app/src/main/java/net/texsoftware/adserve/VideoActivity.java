package net.texsoftware.adserve;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.texsoftware.adservelibrary.AdManager;
import net.texsoftware.adservelibrary.ads.video.VideoInterstitialAd;
import net.texsoftware.adservelibrary.data.VideoInterstitialAdNetwork;
import net.texsoftware.adservelibrary.listeners.VideoAdRequestListener;

import static net.texsoftware.adservelibrary.AdManager.VIDEO_INTERSTITIAL_AD_INDEX;

public class VideoActivity extends AppCompatActivity {
    Button btnLoadAd;
    TextView txtHeader;
    RelativeLayout adLayout;
    AdManager adManager;
    Activity thisObj;
    VideoAdRequestListener adRequestListener;
    VideoInterstitialAd videoInterstitialAd;
    int videoInterstitialTryCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        thisObj = this;

        adLayout = (RelativeLayout) findViewById(R.id.adLayout);
        txtHeader = (TextView) findViewById(R.id.txtHeader);
        btnLoadAd = (Button) findViewById(R.id.btnLoadAd);

        btnLoadAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (videoInterstitialAd != null) {
                            videoInterstitialAd.onDestroy();
                            videoInterstitialAd = null;
                        }
                        adManager.swapVideoInterstitialAd();
                        videoInterstitialAd = adManager.loadVideoInterstitialAd(thisObj, adRequestListener);
                    }
                }, 1000);
            }
        });

        adManager = new AdManager(thisObj, AppAnalytics.getInstance(), "ad_networks.json", true);
        adRequestListener = new VideoAdRequestListener() {
            @Override
            public void onVideoRequestSuccess() {
                try {
                    adLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            VideoInterstitialAdNetwork interstitialAdNetwork = adManager.videoInterstitialAdNetworks.get(VIDEO_INTERSTITIAL_AD_INDEX);
                            txtHeader.setText("LOADED : " + interstitialAdNetwork.getAd_network());
                        }
                    }, 10);

                    adLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            videoInterstitialAd.showInterstitial();
                        }
                    }, 2000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onVideoRequestFailed() {
                videoInterstitialTryCount = videoInterstitialTryCount + 1;
                if (videoInterstitialTryCount < adManager.AD_RETRY_COUNT) {
                    adLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (videoInterstitialAd != null) {
                                videoInterstitialAd.onDestroy();
                                videoInterstitialAd = null;
                            }
                            adManager.swapVideoInterstitialAd();
                            videoInterstitialAd = adManager.loadVideoInterstitialAd(thisObj, adRequestListener);
                        }
                    }, 100);
                }
            }

            @Override
            public void onVideoImpressionLogged() {

            }

            @Override
            public void onVideoClick() {

            }

            @Override
            public void onVideoStarted() {

            }


            @Override
            public void onVideoFinished() {

            }
        };

        videoInterstitialAd = adManager.loadVideoInterstitialAd(thisObj, adRequestListener);
    }
}
