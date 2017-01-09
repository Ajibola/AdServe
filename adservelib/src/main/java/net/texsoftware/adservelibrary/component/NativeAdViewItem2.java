package net.texsoftware.adservelibrary.component;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.texsoftware.adservelibrary.R;
import net.texsoftware.adservelibrary.data.NativeAdObject;
import net.texsoftware.adservelibrary.utils.Logger;

import java.net.URL;

/**
 * Created by Jibola on 4/7/2015.
 */
public class NativeAdViewItem2 extends LinearLayout {

    NativeAdViewItem2 thisObj;

    public ImageView iconImage;
    public LinearLayout adChoicesLayout;
    public TextView txtSponsored;
    public TextView txtTitle;
    public TextView txtSummary;
    public TextView txtCTA;

    NativeAdObject nativeAd;

    public NativeAdViewItem2(Context context) {
        super(context);
        loadViews(context);
    }

    public NativeAdViewItem2(Context context, NativeAdObject nativeAd) {
        super(context);
        this.nativeAd = nativeAd;
        setNativeAd(nativeAd);
        loadViews(context);
    }

    public NativeAdViewItem2(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadViews(context);
    }

    public void removeView() {

    }

    public void setNativeAd(final NativeAdObject nativeAd) {
        this.nativeAd = nativeAd;

        try {
            if (nativeAd != null) {
                txtTitle.setText(nativeAd.getTitle());
                txtSummary.setText(nativeAd.getDescription());

                if (nativeAd.getIcon_url() != null && nativeAd.getIcon_url().length() > 1) {
                    iconImage.setVisibility(VISIBLE);

                    final Handler handler = new Handler();
                    final Runnable runnable = new Runnable() {
                        public void run() {
                            URL url = null;
                            try {
                                url = new URL(nativeAd.getIcon_url());
                                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                                iconImage.setImageBitmap(bmp);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    handler.postDelayed(runnable, 100);
                }

                if (nativeAd.getCta_text() != null && nativeAd.getCta_text().length() > 1)
                    txtCTA.setText("Click to " + nativeAd.getCta_text());
                else
                    txtCTA.setText("Click to Learn more");
            }
        } catch (Exception ex) {
            Logger.err(ex);
        }
    }

    private void loadViews(Context context) {
        try {
            inflate(getContext(), R.layout.native_ad2, this);
            thisObj = this;

            iconImage = (ImageView) findViewById(R.id.iconImage);
            txtSponsored = (TextView) findViewById(R.id.txtSponsored);
            txtTitle = (TextView) findViewById(R.id.txtTitle);
            txtSummary = (TextView) findViewById(R.id.txtSummary);
            txtCTA = (TextView) findViewById(R.id.txtCTA);

            adChoicesLayout = (LinearLayout) findViewById(R.id.adChoicesLayout);
        } catch (Exception ex) {
            Logger.err(ex);
        }
    }

}
