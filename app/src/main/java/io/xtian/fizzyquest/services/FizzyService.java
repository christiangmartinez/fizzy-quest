package io.xtian.fizzyquest.services;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import io.xtian.fizzyquest.Constants;
import io.xtian.fizzyquest.models.Beer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FizzyService {
    public static void findBeers(String name, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.FIZZY_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.FIZZY_STYLE_QUERY_PARAMETER, name + "*");
        urlBuilder.addQueryParameter(Constants.FIZZY_ORDER_PARAM, Constants.FIZZY_ORDER_VALUE);
        urlBuilder.addQueryParameter(Constants.FIZZY_SORTING_PARAM, Constants.FIZZY_SORTING_VALUE);
        urlBuilder.addQueryParameter(Constants.FIZZY_BREW_PARAM, Constants.FIZZY_BREW_VALUE);
        urlBuilder.addQueryParameter(Constants.FIZZY_QUERY_PARAM, Constants.FIZZY_KEY);
        String url = urlBuilder.build().toString();
        Log.d("URL", url);
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Beer> processResults(Response response) {
        ArrayList<Beer> beers = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject fizzyJSON = new JSONObject(jsonData);
                JSONArray dataJSON = fizzyJSON.getJSONArray("data");
                for (int i = 0; i < dataJSON.length(); i++) {
                    JSONObject beerJSON = dataJSON.getJSONObject(i);
                    String beerId = beerJSON.getString("id");
                    String name = beerJSON.getString("nameDisplay");
                    String description = beerJSON.optString("description", "Description not available");
                    String abv = beerJSON.optString("abv", "No Data");
                    String ibu = beerJSON.optString("ibu", "No Data");
                    Beer beer = new Beer(beerId, name, description, abv, ibu);
                    beers.add(beer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();;
        }
        return  beers;
    }
}
