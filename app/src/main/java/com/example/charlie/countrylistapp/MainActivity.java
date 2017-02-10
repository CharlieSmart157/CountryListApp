package com.example.charlie.countrylistapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.charlie.countrylistapp.Content.Content_Contract;
import com.example.charlie.countrylistapp.Content.Content_Presenter;
import com.example.charlie.countrylistapp.Models.Country;
import com.example.charlie.countrylistapp.Utility.RecyclerAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Content_Contract.View{

    RecyclerView recycler;
    RecyclerView.Adapter mAdapter;
    Content_Contract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setPresenter(new Content_Presenter(this));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mPresenter.returnCountries();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getCountries() {
        mPresenter.returnCountries();
    }

    @Override
    public void getCountryDetails() {

    }

    @Override
    public void receiveCountries(List<Country> countries) {

        initializeRecyclerView(countries);
    }

    @Override
    public void setPresenter(Content_Contract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    public void initializeRecyclerView(List<Country> countries){
        recycler = (RecyclerView)findViewById(R.id.countryRecycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new RecyclerAdapter(this,countries,R.layout.country_card);
        recycler.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
