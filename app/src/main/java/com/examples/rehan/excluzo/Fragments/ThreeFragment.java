package com.examples.rehan.excluzo.Fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.examples.rehan.excluzo.Adapters.ProductAdapter;
import com.examples.rehan.excluzo.Models.Product;
import com.examples.rehan.excluzo.R;
import com.examples.rehan.excluzo.ServerUtils.ServerRequest;
import com.examples.rehan.excluzo.Utils.DialogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThreeFragment extends Fragment {

    private List<Product> productList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductAdapter mAdapter;

    String subCategoryName;
    HashMap<String,String> subCategoryKey = new HashMap<String,String>();
    public ThreeFragment() {
        // Required empty public constructor
    }
    public ThreeFragment(String subcategoryName){
        this.subCategoryName = subcategoryName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        init();
        String subid = subCategoryKey.get(subCategoryName);
        loadAllItems(subid);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        return view;
    }

    private void init() {
        subCategoryKey.put("Mobiles","MOBILE_SUB");
        subCategoryKey.put("Laptops","LAPTOP_SUB");
        subCategoryKey.put("Accessories","ACCESSORIES_SUB");
        subCategoryKey.put("Fruits","FRUITS_SUB");
        subCategoryKey.put("beverages","BEVERAGES_SUB");
        subCategoryKey.put("Branded","BRANDED_SUB");
        subCategoryKey.put("Shirts","SHIRTS_SUB");
        subCategoryKey.put("T-Shirts","TSHIRTS_SUB");
        subCategoryKey.put("Jeans","JEANS_SUB");
    }


    private void loadAllItems(final String subid) {
        new AsyncTask<Void, Void, List<Product>>(){

            DialogUtils dialogUtils = new DialogUtils(getActivity(),DialogUtils.Type.PROGRESS_DIALOG);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialogUtils.showProgressDialog("","Please Wait",false);
            }

            @Override
            protected List<Product> doInBackground(Void... params) {
                return new ServerRequest().loadItem(getContext(),subid);
            }

            @Override
            protected void onPostExecute(List<Product> products) {
                super.onPostExecute(products);
                productList = products;
                dialogUtils.dismissProgressDialog();
                mAdapter = new ProductAdapter(productList,getContext());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }

        }.execute();
    }
}
