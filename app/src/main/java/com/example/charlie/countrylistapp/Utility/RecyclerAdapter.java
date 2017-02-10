package com.example.charlie.countrylistapp.Utility;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.charlie.countrylistapp.Models.Country;
import com.example.charlie.countrylistapp.R;

import java.util.List;

/**
 * Created by Charlie on 10/02/2017.
 */


    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
        private String[] mDataset;
        Context context;
        int cardLayout;
        ViewGroup parent;
        List<Country> countries;
        int mExpandedPosition = -1;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTextView;
            public ImageView mImageView;
            public LinearLayout details;
            public TextView tvCapital;
            public TextView tvRegion;
            public TextView tvCurrency;

            public ViewHolder(View v) {
                super(v);
                mTextView = (TextView)v.findViewById(R.id.countryName);
                tvCapital = (TextView)v.findViewById(R.id.capitalText);
                tvCurrency = (TextView)v.findViewById(R.id.currencyText);
                tvRegion  = (TextView)v.findViewById(R.id.regionText);
                mImageView = (ImageView)v.findViewById(R.id.flagImage);
                details = (LinearLayout)v.findViewById(R.id.details);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public RecyclerAdapter(Context context , List<Country> countries, int cardLayout) {
            this.context = context;
            this.cardLayout = cardLayout;
            this.countries = countries;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            this.parent = parent;
            View v = LayoutInflater.from(parent.getContext()).inflate(cardLayout, parent, false);
            return new ViewHolder(v);
        }

    // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.setText(countries.get(position).getName());
            holder.tvCurrency.setText("Currencies: "+countries.get(position).getCurrencies().toString());
            holder.tvCapital.setText("Capital: "+countries.get(position).getCapital());
            holder.tvRegion.setText("Region: "+countries.get(position).getRegion());
            Glide.with(context)
                    .load(Constants.CountryImagesURL+Constants.XtraSizeFlagURL+countries.get(position).getAlpha2Code().toLowerCase()+".gif")
                    .dontTransform()
                    .into(holder.mImageView);

            final int subPos = position;
            final boolean isExpanded = position==mExpandedPosition;
            holder.details.setVisibility(isExpanded?View.VISIBLE:View.GONE);
            //holder.itemView.setActivated(isExpanded);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mExpandedPosition = isExpanded ? -1:subPos;
                //    TransitionManager.beginDelayedTransition(parent);
                    notifyItemChanged(subPos);
                    Log.i("countryApp", "Position: "+subPos);
                }
            });
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return countries.size();
        }
    }
