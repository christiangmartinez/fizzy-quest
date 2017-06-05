package io.xtian.fizzyquest.services;

import android.util.Log;

import io.xtian.fizzyquest.Constants;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class FizzyService {
    public static void findBeers(String styleId, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.FIZZY_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.FIZZY_STYLE_QUERY_PARAMETER, styleId);
        urlBuilder.addQueryParameter(Constants.FIZZY_QUERY_PARAM, Constants.FIZZY_KEY);
        String url = urlBuilder.build().toString();
        Log.d("URL", url);
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
