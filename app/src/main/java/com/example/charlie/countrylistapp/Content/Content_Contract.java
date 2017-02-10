package com.example.charlie.countrylistapp.Content;

import com.example.charlie.countrylistapp.Models.Country;
import com.example.charlie.countrylistapp.Templates.BasePresenter;
import com.example.charlie.countrylistapp.Templates.BaseView;

import java.util.List;

/**
 * Created by Charlie on 20/01/2017.
 */

public interface Content_Contract {

    interface View extends BaseView<Presenter> {

        void getCountries();
        void getCountryDetails();
        void receiveCountries(List<Country>countries);
    }

    interface Presenter extends BasePresenter {

        void returnCountries();
        void returnCountryDetails();
    }
}
