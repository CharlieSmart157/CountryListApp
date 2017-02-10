package com.example.charlie.countrylistapp.Observables;

import com.example.charlie.countrylistapp.Models.Country;
import com.example.charlie.countrylistapp.Utility.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Charlie on 20/01/2017.
 */

public interface CountryListAPI {

    //@POST(Constants.NewSessionURL)
    //Call<Session>createSession(@Body loginRequest loginRequest);

    @GET(Constants.CountriesListURL)
    Call<List<Country>> getAllCountries();



}
