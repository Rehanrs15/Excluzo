package com.examples.rehan.excluzo.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.examples.rehan.excluzo.Models.Product;
import com.examples.rehan.excluzo.Models.Rating;
import com.examples.rehan.excluzo.R;

import java.util.List;

/**
 * Created by rehan r on 26-09-2016.
 */
public class RatingsAdapter extends RecyclerView.Adapter<RatingsAdapter.MyViewHolder>{


    private List<Rating> ratingList;
    Context context;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ratings, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Rating rating = ratingList.get(position);
        holder.ratingsTV.setText(String.valueOf(rating.getRating()));
        holder.ratingsDescriptionTV.setText(rating.getReview());
        holder.useridTV.setText(rating.getUserid());
    }

    @Override
    public int getItemCount() {
        return ratingList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView ratingsTV,ratingsDescriptionTV,useridTV;
        public MyViewHolder(View view) {
            super(view);
            ratingsTV = (TextView)view.findViewById(R.id.ratings);
            ratingsDescriptionTV = (TextView)view.findViewById(R.id.ratingstext);
            useridTV = (TextView)view.findViewById(R.id.userid);

        }
    }

    public RatingsAdapter(List<Rating> ratingList, Context context) {
        this.ratingList = ratingList;
        this.context = context;
    }

}
