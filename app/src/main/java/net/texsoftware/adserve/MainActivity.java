package net.texsoftware.adserve;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.crashlytics.android.answers.Answers;

import net.texsoftware.adservelibrary.AdManager;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
    Button btnBannerAd;
    Button btnVideoAd;
    Button btnNativeAd;
    Button btnInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Answers())
                .build();
        Fabric.with(fabric);

        btnBannerAd = (Button) findViewById(R.id.btnBannerAd);
        btnVideoAd = (Button) findViewById(R.id.btnVideoAd);
        btnNativeAd = (Button) findViewById(R.id.btnNativeAd);
        btnInterstitialAd = (Button) findViewById(R.id.btnInterstitialAd);

        btnBannerAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), BannerActivity.class);
                startActivity(intent);
            }
        });

        btnVideoAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), VideoActivity.class);
                startActivity(intent);
            }
        });

        btnNativeAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), NativeActivity.class);
                startActivity(intent);
            }
        });

        btnInterstitialAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), InterstitialActivity.class);
                startActivity(intent);
            }
        });
    }
}
