package net.texsoftware.adservelibrary.component;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
public class NativeAdViewItem extends LinearLayout {

    public ImageView imgView;
    public ImageView imgIcon;
    public LinearLayout adChoicesLayout;
    public TextView txtSponsored;
    public TextView txtTitle;
    public TextView txtSummary;

    NativeAdObject nativeAd;

    public NativeAdViewItem(Context context) {
        super(context);
        loadViews(context);
    }

    public NativeAdViewItem(Context context, NativeAdObject nativeAd) {
        super(context);
        this.nativeAd = nativeAd;
        loadViews(context);
    }

    public NativeAdViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadViews(context);
    }

    public void removeView() {

    }

    public void setNativeAd(final NativeAdObject nativeAd) {
        this.nativeAd = nativeAd;

        if (nativeAd != null) {
            txtTitle.setText(nativeAd.getTitle() + " " + nativeAd.getDescription());
            txtSummary.setText(nativeAd.getDescription());

            final Handler handler = new Handler();
            final Runnable runnable = new Runnable() {
                public void run() {
                    URL url = null;
                    try {
                        url = new URL(nativeAd.getImage_url());
                        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        imgView.setImageBitmap(bmp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            handler.postDelayed(runnable, 100);
        }
    }

    private void loadViews(Context context) {
        try {
            LayoutInflater inflater = LayoutInflater.from(context);
            inflater.inflate(R.layout.native_ad, this);

            imgView = (ImageView) findViewById(R.id.imgView);
            imgIcon = (ImageView) findViewById(R.id.imgIcon);
            txtSponsored = (TextView) findViewById(R.id.txtSponsored);
            txtTitle = (TextView) findViewById(R.id.txtTitle);
            txtSummary = (TextView) findViewById(R.id.txtSummary);
            adChoicesLayout = (LinearLayout) findViewById(R.id.adChoicesLayout);

            if (nativeAd != null) {
                txtTitle.setText(nativeAd.getTitle() + " " + nativeAd.getDescription());
                txtSummary.setText(nativeAd.getDescription());
                imgView.setVisibility(GONE);

                final Runnable runnable = new Runnable() {
                    public void run() {
                        URL url = null;
                        try {
                            url = new URL(nativeAd.getImage_url());
                            final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                            imgView.post(new Runnable() {
                                @Override
                                public void run() {
                                    imgView.setImageBitmap(bmp);
                                    imgView.setVisibility(VISIBLE);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                new Thread(runnable).start();

                final Runnable runnable1 = new Runnable() {
                    public void run() {
                        URL url = null;
                        try {
                            url = new URL(nativeAd.getIcon_url());
                            final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                            imgIcon.post(new Runnable() {
                                @Override
                                public void run() {
                                    imgIcon.setImageBitmap(bmp);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                new Thread(runnable1).start();
            }

            setFocusable(true);
            setClickable(true);
        } catch (Exception ex) {
            Logger.err(ex);
        }
    }

}
