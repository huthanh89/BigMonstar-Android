package com.mathtingz.mathtingz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Show WebView

        WebView webView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webView.loadUrl("http://bigmonstar.net/");

        // Show AdMob

        MobileAds.initialize(this, "ca-app-pub-4342286684165248~5200938835");
        mInterstitialAd = new InterstitialAd(this);

        // Real AD Unit.
  //      mInterstitialAd.setAdUnitId("ca-app-pub-4342286684165248/3776176522");

        // Test AD Unit.
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/8691691433");

        AdRequest adRequest = new AdRequest.Builder().build();

        mInterstitialAd.loadAd(adRequest);


        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }

            }

            @Override
            public void onAdOpened() {


            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }
        });

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "User opened app");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        Bundle params = new Bundle();
        params.putString("image_name", "foo");
        params.putString("full_text", "bar");
        mFirebaseAnalytics.logEvent("test_params", params);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
