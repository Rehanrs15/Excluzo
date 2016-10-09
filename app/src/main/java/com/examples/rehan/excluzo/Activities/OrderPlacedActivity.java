package com.examples.rehan.excluzo.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.examples.rehan.excluzo.Models.Product;
import com.examples.rehan.excluzo.R;
import com.examples.rehan.excluzo.ServerUtils.ServerRequest;
import com.examples.rehan.excluzo.Utils.DialogUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderPlacedActivity extends AppCompatActivity {

    RelativeLayout successLayout;
    TextView failedTV,toolbartitleTV;
    String itemlist = "";
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);
        successLayout = (RelativeLayout)findViewById(R.id.success);
        failedTV = (TextView)findViewById(R.id.failed);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbartitleTV = (TextView)findViewById(R.id.categoryname);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        init();
        placeOrder();
    }

    private void placeOrder() {
        new AsyncTask<Void, Void, JSONObject>(){

            DialogUtils progreedialog = new DialogUtils(OrderPlacedActivity.this,DialogUtils.Type.PROGRESS_DIALOG);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progreedialog.showProgressDialog("placing order","please wait..",false);
            }

            @Override
            protected JSONObject doInBackground(Void... params) {
                return new ServerRequest().placeOrder(OrderPlacedActivity.this,itemlist);
            }

            @Override
            protected void onPostExecute(JSONObject response) {
                super.onPostExecute(response);
                progreedialog.dismissProgressDialog();
                try {
                    if (response.get("status").equals("success")) {
                        failedTV.setVisibility(View.GONE);
                        successLayout.setVisibility(View.VISIBLE);
                    } else if (response.get("status").equals("failed")){
                        successLayout.setVisibility(View.GONE);
                        failedTV.setVisibility(View.VISIBLE);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

        }.execute();
    }


    private void init() {
        toolbartitleTV.setText("Order");
        itemlist= getIntent().getStringExtra("itemlist");
    }
}
