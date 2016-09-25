package com.examples.rehan.excluzo.Adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.examples.rehan.excluzo.Models.Product;
import com.examples.rehan.excluzo.R;

import java.util.List;

/**
 * Created by rehan r on 24-09-2016.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {


    private List<Product> productList;
    Context context;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.productName.setText(product.getProductname());
        holder.productCategory.setText(product.getSubid());
        holder.productScratchPrice.setText(context.getResources().getString(R.string.Rs)+String.valueOf(product.getPrice()));
        holder.productScratchPrice.setPaintFlags(holder.productScratchPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        int value = showprice(product.getDiscount(),product.getPrice());
        holder.productPrice.setText(context.getResources().getString(R.string.Rs)+String.valueOf(value));
        holder.discount.setText("( "+String.valueOf(product.getDiscount())+"% OFF)");
        holder.productRatings.setText(String.valueOf(product.getRatings()));
        holder.productTotalRatings.setText("("+String.valueOf(product.getNumberOfRatings())+" ratings)");
        //add image utl
    }

    private int showprice(int discount,int price) {
        int v = (price-(price*discount)/100);
        return v;
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView productName,productCategory,productPrice,productScratchPrice,discount,productRatings,
                        productTotalRatings;
        public ImageView productImage;
        public MyViewHolder(View view) {
            super(view);
            productName = (TextView) view.findViewById(R.id.item_name);
            productCategory = (TextView) view.findViewById(R.id.sub_categoryname);
            productPrice = (TextView) view.findViewById(R.id.actual_item_price);
            productScratchPrice = (TextView) view.findViewById(R.id.scratch_price);
            discount = (TextView) view.findViewById(R.id.discount);
            productRatings = (TextView) view.findViewById(R.id.ratings);
            productTotalRatings = (TextView) view.findViewById(R.id.total_ratings);
            productImage = (ImageView)view.findViewById(R.id.item_image);
        }
    }



    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

}
