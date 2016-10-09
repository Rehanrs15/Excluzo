package com.examples.rehan.excluzo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.examples.rehan.excluzo.Models.User;
import com.examples.rehan.excluzo.Preferences.LoginPreferences;
import com.examples.rehan.excluzo.R;

import java.util.HashMap;

public class MainActivity extends BaseActivity {


    SliderLayout mDemoSlider;
    TextView titleToolbarTV,tabElectronicsTV,tabFashionTV,tabOffersTV,tabFoodTV,tabRechargeTV
            ,headerUserNameTV,headerUsrMobileTV,headerUserLocation;
    ImageView tabElectronicsIV,tabFashionIV,tabOffersIV,tabFoodIV,tabRechargeIV,hamburgerIV;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    User user;
    LoginPreferences loginPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //applyFont(MainActivity.this,findViewById(R.id.base_layout));
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);
        titleToolbarTV = (TextView)findViewById(R.id.title_text);
        tabElectronicsTV = (TextView)findViewById(R.id.ec_tv);
        tabFashionTV = (TextView)findViewById(R.id.fash_tv);
        tabOffersTV = (TextView)findViewById(R.id.offers_tv);
        tabFoodTV = (TextView)findViewById(R.id.food_tv);
        tabRechargeTV = (TextView)findViewById(R.id.recharge_tv);
        tabElectronicsIV =  (ImageView)findViewById(R.id.ec_img);
        tabFashionIV =  (ImageView)findViewById(R.id.fash_img);
        tabOffersIV = (ImageView)findViewById(R.id.offers_img);
        tabFoodIV = (ImageView)findViewById(R.id.food_img);
        tabRechargeIV = (ImageView)findViewById(R.id.recharge_img);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        hamburgerIV = (ImageView)findViewById(R.id.hamburger);
        drawerLayout = (DrawerLayout) findViewById(R.id.base_layout);
        navigationView = (NavigationView)findViewById(R.id.navigation_drawer);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        loginPreferences = new LoginPreferences(MainActivity.this);
        user = loginPreferences.getUser();
        hamburgerIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drawerLayout.isDrawerOpen(GravityCompat.START)){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else{
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        View view = navigationView.getHeaderView(0);
        init(view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                return false;
            }
        });

        //add the images here
        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("1",R.drawable.step_2);
        file_maps.put("2",R.drawable.step_2);
        file_maps.put("3", R.drawable.step_2);


        for(String name : file_maps.keySet()){
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {

                        }
                    });

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(5000);
        mDemoSlider.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabElectronicsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openCategoryActivity(getResources().getString(R.string.electronics));
            }
        });

        tabElectronicsIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryActivity(getResources().getString(R.string.electronics));

            }
        });

        tabFashionTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryActivity(getResources().getString(R.string.fashion));
            }
        });

        tabFashionIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryActivity(getResources().getString(R.string.fashion));
            }
        });

        tabOffersTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryActivity(getResources().getString(R.string.offers));
            }
        });

        tabOffersIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryActivity(getResources().getString(R.string.offers));
            }
        });

        tabFoodTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryActivity(getResources().getString(R.string.food));
            }
        });

        tabRechargeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryActivity(getResources().getString(R.string.recharge));
            }
        });

        tabRechargeIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategoryActivity(getResources().getString(R.string.recharge));
            }
        });
    }

    private void init(View view) {
        //CurrentLocation location = new CurrentLocation(MainActivity.this);
        if(user.getUsername() != null)
            titleToolbarTV.setText("Hello "+user.getUsername()+",");
        headerUserNameTV = (TextView)view.findViewById(R.id.headerusername);
        headerUsrMobileTV = (TextView)view.findViewById(R.id.headermobile);
        headerUserLocation = (TextView)view.findViewById(R.id.headerlocation);
        if (user.getUsername() != null)
            headerUserNameTV.setText(user.getUsername().toString());
        if (user.getMobile() != null)
            headerUsrMobileTV.setText(user.getMobile().toString());
        //headerUserLocation.setText(location.getCurrentLocation());
    }


    /*
        call this activity when any of the tab is clicked
        this method is used for all the clicks depending the the type of item clicked
    */
    public void openCategoryActivity(String item){
       // Toast.makeText(MainActivity.this,item,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,CategoryActivity.class);
        intent.putExtra("item",item);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainactivity_menu,menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId() == R.id.cart){
            startActivity(new Intent(MainActivity.this,CartActivity.class));
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_OK);
        finish();
    }
}
