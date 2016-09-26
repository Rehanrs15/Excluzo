package com.examples.rehan.excluzo.Activities;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.examples.rehan.excluzo.Adapters.RatingsAdapter;
import com.examples.rehan.excluzo.Models.Product;
import com.examples.rehan.excluzo.Models.Rating;
import com.examples.rehan.excluzo.R;
import com.examples.rehan.excluzo.ServerUtils.ServerRequest;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends BaseActivity {

    TextView prodctNameTV,productDiscountPriceTV,productActualPriceTV,productDiscountTV,
            productRatingsTV,productNumberOfRatingsTV,productSpec1TV,productSpec2TV,productSpec3TV,
            productSpec4TV,productLayoutRatingsTV,productLayoutReviewsTV
            ,toolbarTV,
            productRateButtonTV;
    ImageView productImageIV;
    RecyclerView recyclerView;
    RatingsAdapter ratingsAdapter;
    ProgressBar progressBar;
    ScrollView scrollView;
    Toolbar toolbar;
    String title = "";
    Product product;
    List<Rating> ratings = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        //applyFont(ProductActivity.this,findViewById(R.id.base_layout));
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        product = (Product) getIntent().getSerializableExtra("product");
        title = getIntent().getStringExtra("item");
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbarTV = (TextView)findViewById(R.id.categoryname);
        prodctNameTV = (TextView)findViewById(R.id.productname);
        productDiscountPriceTV = (TextView)findViewById(R.id.actual_item_price);
        productActualPriceTV = (TextView)findViewById(R.id.scratch_price);
        productDiscountTV = (TextView)findViewById(R.id.discount);
        productRatingsTV = (TextView)findViewById(R.id.ratings);
        productNumberOfRatingsTV = (TextView)findViewById(R.id.total_ratings);
        productSpec1TV = (TextView)findViewById(R.id.spec_1);
        productSpec2TV = (TextView)findViewById(R.id.spec_2);
        productSpec3TV = (TextView)findViewById(R.id.spec_3);
        productSpec4TV = (TextView)findViewById(R.id.spec_4);
        productLayoutRatingsTV = (TextView)findViewById(R.id.rating_stars);
        productLayoutReviewsTV = (TextView)findViewById(R.id.rating_reviews);
        productRateButtonTV = (TextView)findViewById(R.id.button_ratings);
        productImageIV = (ImageView)findViewById(R.id.productimage);
        recyclerView = (RecyclerView)findViewById(R.id.ratings_recycler_view);
        scrollView = (ScrollView)findViewById(R.id.scroll);
        //scrollView.setVisibility(View.GONE);
        //progressBar.setVisibility(View.VISIBLE);
        //loadRatings();
        init();

        productRateButtonTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.ratings_layout, null);
                TextView name = (TextView)alertLayout.findViewById(R.id.item_name);
                final EditText comment = (EditText)alertLayout.findViewById(R.id.comment);
                final RatingBar ratingBar = (RatingBar)alertLayout.findViewById(R.id.ratingbar);
                name.setText(product.getProductname());
                AlertDialog.Builder alert = new AlertDialog.Builder(ProductActivity.this);
                alert.setView(alertLayout);
                alert.setCancelable(false);
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getBaseContext(), "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setPositiveButton("Rate", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        float ratings = ratingBar.getRating();
                        String comments = comment.getText().toString();
                        uploadRatings(ratings,comments);
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();


            }
        });
        }

    private void loadRatings() {
        new AsyncTask<Void, Void, List<Rating>>(){

            @Override
            protected List<Rating> doInBackground(Void... params) {
                return new ServerRequest().loadRatings(product.getProductid());
            }

            @Override
            protected void onPostExecute(List<Rating> aVoid) {
                super.onPostExecute(aVoid);
            }

        }.execute();
    }

    private void uploadRatings(float ratings, String comments) {
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Void doInBackground(Void... params) {
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }

        }.execute();
    }

    private void init() {
        String[] description = product.getDescription().split("\n");
        toolbarTV.setText(title);
        prodctNameTV.setText(product.getProductname());
        int value = product.getPrice() - ((product.getPrice() * product.getDiscount())/100);
        productDiscountPriceTV.setText(ProductActivity.this.getResources().getString(R.string.Rs)+String.valueOf(value));
        productActualPriceTV.setText(ProductActivity.this.getResources().getString(R.string.Rs)+String.valueOf(product.getPrice()));
        productActualPriceTV.setPaintFlags(productActualPriceTV.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        productDiscountTV.setText("("+String.valueOf(product.getDiscount())+"% Off)");
        productRatingsTV.setText(String.valueOf(product.getRatings()));
        productNumberOfRatingsTV.setText("("+String.valueOf(product.getNumberOfRatings())+" Ratings)");
        productLayoutRatingsTV.setText(String.valueOf(product.getRatings()));
        productLayoutReviewsTV.setText("("+String.valueOf(product.getNumberOfRatings())+" Ratings)");
        productSpec1TV.setText(getResources().getString(R.string.bullet)+" "+description[0].toString().replace("\r",""));
        productSpec2TV.setText(getResources().getString(R.string.bullet)+" "+description[1].toString().replace("\r",""));
        productSpec3TV.setText(getResources().getString(R.string.bullet)+" "+description[2].toString().replace("\r",""));
        productSpec4TV.setText(getResources().getString(R.string.bullet)+" "+description[3].toString().replace("\r",""));

    }
}
