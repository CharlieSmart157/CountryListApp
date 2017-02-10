package com.example.charlie.countrylistapp.Content;

import android.app.Activity;
import android.util.Log;

import com.example.charlie.countrylistapp.Models.Country;
import com.example.charlie.countrylistapp.Observables.CountryListAPI;
import com.example.charlie.countrylistapp.Utility.Constants;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Charlie on 20/01/2017.
 */

public class Content_Presenter implements Content_Contract.Presenter{

    private final Content_Contract.View mContentView;

    CountryListAPI countryListAPI;
    Activity mActivity;
    Retrofit restapi;
    OkHttpClient client;

    public Content_Presenter (Content_Contract.View v){

        mContentView = v;
        mContentView.setPresenter(this);
       // HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
       // interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder()/*.addInterceptor(interceptor)*/.build();

    }

    @Override
    public void returnCountries() {
        restapi = new Retrofit.Builder()
                .baseUrl(Constants.CountriesInfoURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        countryListAPI = restapi.create(CountryListAPI.class);

        Call<List<Country>> call = countryListAPI.getAllCountries();

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                Log.i("countryApp", "Countries found: "+response.body().size());
                mContentView.receiveCountries(response.body());

            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.i("countryApp", "Error: "+t.getMessage());
            }
        });
    }


    @Override
    public void returnCountryDetails() {

    }
}
