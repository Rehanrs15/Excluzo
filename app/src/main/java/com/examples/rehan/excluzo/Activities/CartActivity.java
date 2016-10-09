package com.examples.rehan.excluzo.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.rehan.excluzo.Adapters.CartAdapter;
import com.examples.rehan.excluzo.Models.Product;
import com.examples.rehan.excluzo.R;
import com.examples.rehan.excluzo.ServerUtils.ServerRequest;
import com.examples.rehan.excluzo.Utils.DialogUtils;
import com.examples.rehan.excluzo.database.DatabaseHandler;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {


    private List<Product> productList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CartAdapter mAdapter;
    TextView toolbarTextTV,cartemptyTV,totalItemsTV,totalPriceTV,placeOrderTV;
    LinearLayout placeOrderLayout_LL;
    ProgressBar progressBar;
    DatabaseHandler databaseHandler = new DatabaseHandler(CartActivity.this);
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTextTV = (TextView)findViewById(R.id.categoryname);
        toolbarTextTV.setPadding(0,0,0,0);
        toolbarTextTV.setText("Cart");
        cartemptyTV = (TextView)findViewById(R.id.cart_empty);
        totalItemsTV = (TextView)findViewById(R.id.cart_items);
        totalPriceTV = (TextView)findViewById(R.id.cart_price);
        placeOrderTV = (TextView)findViewById(R.id.placeorder);
        progressBar.setVisibility(View.VISIBLE);
        placeOrderLayout_LL = (LinearLayout)findViewById(R.id.placeorder_layout);
        CountDownTimer countDownTimer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                progressBar.setVisibility(View.GONE);
                loadCart();
            }
        }.start();


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(CartActivity.this, recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                ImageView remove = (ImageView)view.findViewById(R.id.delete_cart);
                remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Product product = productList.get(position);
                        databaseHandler.deleteProduct(product);
                        Toast.makeText(getApplicationContext(),"item Removed",Toast.LENGTH_LONG).show();
                        productList.remove(product);
                        mAdapter.notifyDataSetChanged();
                        if (productList.size() == 0){
                            cartemptyTV.setVisibility(View.VISIBLE);
                            placeOrderLayout_LL.setVisibility(View.GONE);
                        }
                        else{
                            cartemptyTV.setVisibility(View.GONE);
                            placeOrderLayout_LL.setVisibility(View.VISIBLE);
                            loadPrice();
                        }
                    }
                });
            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));

        placeOrderTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String items= "";
                for (Product product : productList){
                   items = items.concat(product.getProductid()+", ");
                }
                items = items.substring(0,items.length() - 2);
                Log.e("items",items);
                Intent intent = new Intent(CartActivity.this,OrderPlacedActivity.class);
                intent.putExtra("itemlist", items);
                startActivity(intent);
            }
        });
    }


    private void loadCart() {
        productList = databaseHandler.getAllProducts();
        if (productList.size() == 0){
            cartemptyTV.setVisibility(View.VISIBLE);
            placeOrderLayout_LL.setVisibility(View.GONE);
        }
        else {
            cartemptyTV.setVisibility(View.GONE);
            placeOrderLayout_LL.setVisibility(View.VISIBLE);
            loadPrice();
        }
        mAdapter = new CartAdapter(productList,CartActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void loadPrice() {
        int totalItems = productList.size();
        int price = 0;
        for (Product product : productList){
            price += product.getPrice();
        }
        totalItemsTV.setText(totalItems+" Items");
        totalPriceTV.setText("Total: "+getResources().getString(R.string.Rs)+price);
    }


    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }


    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }


        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }
        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
}
