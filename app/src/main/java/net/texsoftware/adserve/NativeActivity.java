package net.texsoftware.adserve;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.texsoftware.adservelibrary.AdManager;
import net.texsoftware.adservelibrary.ads.nativ.NativeAd;
import net.texsoftware.adservelibrary.data.NativeAdNetwork;
import net.texsoftware.adservelibrary.listeners.NativeAdRequestListener;

import static net.texsoftware.adservelibrary.AdManager.NATIVE_AD_INDEX;

public class NativeActivity extends AppCompatActivity {
    Button btnLoadAd;
    TextView txtHeader;
    RelativeLayout adLayout;
    LinearLayout nativeAdLayout;
    AdManager adManager;
    Activity thisObj;
    NativeAdRequestListener adRequestListener;
    NativeAd nativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        thisObj = this;

        adLayout = (RelativeLayout) findViewById(R.id.adLayout);
        nativeAdLayout = (LinearLayout) findViewById(R.id.nativeAdLayout);
        txtHeader = (TextView) findViewById(R.id.txtHeader);

        btnLoadAd = (Button) findViewById(R.id.btnLoadAd);
        btnLoadAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (nativeAd != null) {
                            nativeAd.onDestroy();
                            nativeAd = null;
                        }
                        adManager.swapNativeAd();
                        nativeAd = adManager.loadNativeAd(thisObj, adRequestListener);
                    }
                }, 1000);
            }
        });

        adManager = new AdManager(thisObj, AppAnalytics.getInstance(), "ad_networks.json", true);
        adRequestListener = new NativeAdRequestListener() {
            @Override
            public void onNativeRequestSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NativeAdNetwork nativeAdNetwork = adManager.nativeAdNetworks.get(NATIVE_AD_INDEX);
                        txtHeader.setText(nativeAdNetwork.getAd_network());
                        try {
                            nativeAdLayout.removeAllViews();
                            if (nativeAd != null) {
                                nativeAdLayout.addView(nativeAd.getNativeAd());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onNativeRequestFailed() {

            }

            @Override
            public void onNativeImpressionLogged() {

            }

            @Override
            public void onNativeClick() {

            }
        };

        adManager.NATIVE_AD_INDEX = 0;
        nativeAd = adManager.loadNativeAd(thisObj, adRequestListener);
    }
}
