package net.texsoftware.adserve;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

import net.texsoftware.adservelibrary.AdManager;
import net.texsoftware.adservelibrary.ads.banner.BannerAd;
import net.texsoftware.adservelibrary.data.BannerAdNetwork;
import net.texsoftware.adservelibrary.listeners.BannerAdRequestListener;

import static net.texsoftware.adservelibrary.AdManager.BANNER_AD_INDEX;

public class BannerActivity extends AppCompatActivity {
    FrameLayout adLayout;
    TextView txtHeader;
    AdManager adManager;
    Activity thisObj;

    int bannerTryCount = 0;
    BannerAd bannerAd;
    BannerAdRequestListener bannerAdRequestListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        thisObj = this;
        adLayout = (FrameLayout) findViewById(R.id.adLayout);
        txtHeader = (TextView) findViewById(R.id.txtHeader);

        adManager = new AdManager(thisObj, AppAnalytics.getInstance(), "ad_networks.json", true);
        bannerAdRequestListener = new BannerAdRequestListener() {
            @Override
            public void onBannerRequestSuccess() {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            BannerAdNetwork bannerAdNetwork = adManager.bannerAdNetworks.get(BANNER_AD_INDEX);
                            txtHeader.setText(bannerAdNetwork.getAd_network());
                            try {
                                adLayout.removeAllViews();
                                if (bannerAd != null && bannerAd.getBanner() != null)
                                    adLayout.addView(bannerAd.getBanner());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    adLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (bannerAd != null) {
                                adLayout.removeAllViews();
                                bannerAd.onDestroy();
                                bannerAd = null;
                            }
                            adManager.swapBannerAd();
                            bannerAd = adManager.loadBannerAd(thisObj, bannerAdRequestListener);
                        }
                    }, 10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onBannerRequestFailed() {
                bannerTryCount = bannerTryCount + 1;
                if (bannerTryCount < adManager.AD_RETRY_COUNT) {
                    adLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (bannerAd != null) {
                                adLayout.removeAllViews();
                                bannerAd.onDestroy();
                                bannerAd = null;
                            }
                            adManager.swapBannerAd();
                            bannerAd = adManager.loadBannerAd(thisObj, bannerAdRequestListener);
                        }
                    }, 100);
                } else {
                    bannerAd = null;
                }
            }

            @Override
            public void onBannerImpressionLogged() {

            }

            @Override
            public void onBannerClick() {

            }
        };

        bannerAd = adManager.loadBannerAd(thisObj, bannerAdRequestListener);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (bannerAd != null) {
            bannerAd.onDestroy();
        }
    }
}
