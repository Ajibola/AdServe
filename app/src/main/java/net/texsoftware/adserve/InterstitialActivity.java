package net.texsoftware.adserve;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.texsoftware.adservelibrary.AdManager;
import net.texsoftware.adservelibrary.ads.interstitial.InterstitialAd;
import net.texsoftware.adservelibrary.data.InterstitialAdNetwork;
import net.texsoftware.adservelibrary.listeners.InterstitialAdRequestListener;

import static net.texsoftware.adservelibrary.AdManager.INTERSTITIAL_AD_INDEX;

public class InterstitialActivity extends AppCompatActivity {
    TextView txtHeader;
    Button btnLoadAd;
    RelativeLayout adLayout;
    AdManager adManager;
    Activity thisObj;
    InterstitialAd interstitialAd = null;
    InterstitialAdRequestListener adRequestListener;
    public int interstitialTryCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interstitial);
        thisObj = this;

        adLayout = (RelativeLayout) findViewById(R.id.adLayout);
        txtHeader = (TextView) findViewById(R.id.txtHeader);

        adManager = new AdManager(thisObj, AppAnalytics.getInstance(), "ad_networks.json");

        btnLoadAd = (Button) findViewById(R.id.btnLoadAd);
        btnLoadAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (interstitialAd != null) {
                            interstitialAd.onDestroy();
                            interstitialAd = null;
                        }
                        adManager.swapInterstitialAd();
                        interstitialAd = adManager.loadInterstitialAd(thisObj, adRequestListener);
                    }
                }, 1000);
            }
        });

        adRequestListener = new InterstitialAdRequestListener() {
            @Override
            public void onInterstitialRequestSuccess() {
                try {
                    adLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            InterstitialAdNetwork interstitialAdNetwork = adManager.interstitialAdNetworks.get(INTERSTITIAL_AD_INDEX);
                            txtHeader.setText(interstitialAdNetwork.getAd_network());
                        }
                    }, 10);

                    adLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            interstitialAd.showInterstitial();
                        }
                    }, 2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onInterstitialRequestFailed() {
                interstitialTryCount = interstitialTryCount + 1;
                if (interstitialTryCount < adManager.AD_RETRY_COUNT) {
                    adLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (interstitialAd != null) {
                                interstitialAd.onDestroy();
                                interstitialAd = null;
                            }
                            adManager.swapInterstitialAd();
                            interstitialAd  = adManager.loadInterstitialAd(thisObj, adRequestListener);
                        }
                    }, 100);
                }
            }

            @Override
            public void onInterstitialImpressionLogged() {

            }

            @Override
            public void onInterstitialClick() {

            }
        };

        interstitialAd  = adManager.loadInterstitialAd(thisObj, adRequestListener);
    }
}
