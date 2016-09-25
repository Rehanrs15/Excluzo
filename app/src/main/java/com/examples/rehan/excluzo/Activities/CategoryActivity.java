package com.examples.rehan.excluzo.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.examples.rehan.excluzo.Adapters.ViewPagerAdapter;
import com.examples.rehan.excluzo.Fragments.OneFragment;
import com.examples.rehan.excluzo.Fragments.ThreeFragment;
import com.examples.rehan.excluzo.Fragments.TwoFragment;
import com.examples.rehan.excluzo.R;

import java.util.ArrayList;

public class CategoryActivity extends BaseActivity {

    public Toolbar toolbar;
    public TabLayout tabLayout;
    public ViewPager viewPager;
    TextView toobarItemNameTV;
    Intent intent;
    ArrayList<String> subCategories = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        //applyFont(CategoryActivity.this,findViewById(R.id.base_layout));
        intent = getIntent();
        String categoryName = intent.getStringExtra("item");
        init(categoryName);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toobarItemNameTV = (TextView)findViewById(R.id.categoryname);
        toobarItemNameTV.setText(categoryName);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(subCategories.get(0)), subCategories.get(0));
        adapter.addFragment(new TwoFragment(subCategories.get(1)), subCategories.get(1));
        adapter.addFragment(new ThreeFragment(subCategories.get(2)), subCategories.get(2));
        viewPager.setAdapter(adapter);
    }


    private void init(String categoryName) {
        if (categoryName.equalsIgnoreCase("Electronics")){
            subCategories.add("Mobiles");
            subCategories.add("Laptops");
            subCategories.add("Accessories");
        }
        else if (categoryName.equalsIgnoreCase("Grocery")){
            subCategories.add("Beverages");
            subCategories.add("Branded");
            subCategories.add("Fruits");
        }
        else if (categoryName.equalsIgnoreCase("Fashion")){
            subCategories.add("Shirts");
            subCategories.add("T-Shirts");
            subCategories.add("Jeans");
        }
        else{

        }
    }
}
