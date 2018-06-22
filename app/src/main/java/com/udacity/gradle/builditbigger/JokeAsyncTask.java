package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import baking.alexshr.com.jokesdisplaylib.DisplayContentActivity;

import static baking.alexshr.com.jokesdisplaylib.DisplayContentActivity.CONTENT_KEY;

public class JokeAsyncTask extends AsyncTask<Context, Void, Pair<Context, String>> {

    private static final String LOCAL_API_PATH = "http://10.0.2.2:8080/_ah/api/";
    private static MyApi apiService;

    private FinishListener listener;


    @Override
    protected Pair<Context, String> doInBackground(Context... context) {
        if (apiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)

                    .setRootUrl(LOCAL_API_PATH)
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            apiService = builder.build();
        }

        //context = params[0].first;
        //String name = params[0].second;
        String result = null;
        try {
            result = apiService.provideJoke().execute().getData();
        } catch (IOException e) {
            result = e.getMessage();
            Log.e(getClass().getName(), "", e);
        }
        return new Pair<>(context[0], result);
    }

    @Override
    protected void onPostExecute(Pair<Context, String> pair) {

        Intent intent = new Intent(pair.first, DisplayContentActivity.class);

        intent.putExtra(CONTENT_KEY, pair.second);
        pair.first.startActivity(intent);

        if (listener != null) listener.onFinish();
    }

    public void setFinishListener(FinishListener listener) {
        this.listener = listener;
    }

    public interface FinishListener {
        void onFinish();
    }
}



