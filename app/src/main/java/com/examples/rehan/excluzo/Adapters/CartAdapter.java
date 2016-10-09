package com.examples.rehan.excluzo.Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.design.widget.TabItem;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.rehan.excluzo.Models.Product;
import com.examples.rehan.excluzo.R;
import com.examples.rehan.excluzo.database.DatabaseHandler;

import java.util.List;

/**
 * Created by rehan r on 04-10-2016.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    private List<Product> productList;
    Context context;
    DatabaseHandler databaseHandler;
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartAdapter.MyViewHolder holder, final int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getProductname());
        int value = showprice(product.getDiscount(),product.getPrice());
        holder.productPrice.setText(context.getResources().getString(R.string.Rs)+String.valueOf(value));
        holder.productRatings.setText(String.valueOf(product.getRatings()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    private int showprice(int discount,int price) {
        int v = (price-(price*discount)/100);
        return v;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView productName,productPrice,productRatings;
        public ImageView productImage,deleteImage;
        public MyViewHolder(View view) {
            super(view);
            productName = (TextView) view.findViewById(R.id.item_name_cart);
            productPrice = (TextView) view.findViewById(R.id.actual_item_price_cart);
            productRatings = (TextView) view.findViewById(R.id.ratings_cart);
            productImage = (ImageView)view.findViewById(R.id.item_image_cart);
            deleteImage = (ImageView)view.findViewById(R.id.delete_cart);
        }
    }

    public CartAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
        databaseHandler = new DatabaseHandler(context);
    }

}
