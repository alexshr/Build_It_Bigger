package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivityFragment extends Fragment {

    private static final String TEST_AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";

    private InterstitialAd mInterstitialAd;

    private MenuItem progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        setHasOptionsMenu(true);

        Button btn = root.findViewById(R.id.jokeBtn);
        btn.setOnClickListener(this::tellJoke);

        AdView mAdView = root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId(TEST_AD_UNIT_ID);

        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdClosed() {
                performJokeTask();
            }
        });

        mAdView.loadAd(adRequest);
        mInterstitialAd.loadAd(adRequest);

        return root;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        progressBar = menu.findItem(R.id.progress);
    }

    public void tellJoke(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d(getClass().getName(), "The interstitial wasn't loaded yet.");
            performJokeTask();
        }

    }

    private void performJokeTask(){
        JokeAsyncTask task=new JokeAsyncTask();
        task.setFinishListener(()->progressBar.setVisible(false));
        task.execute(getContext());
        progressBar.setVisible(true);
    }


}
