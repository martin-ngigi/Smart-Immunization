package com.example.smart_immunization.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.smart_immunization.Adapters.AdapterSliderSplash;
import com.example.smart_immunization.Models.SliderItem;
import com.example.smart_immunization.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private List<SliderItem> mSliderItemsList;

    // Urls of our images.
    String url1 = "https://media.npr.org/assets/img/2022/05/12/gettyimages-1167832768_custom-53568d2150de6a6f3154d095324c2440e7208b53-s800-c85.webp";
    String url2 = "https://th-thumbnailer.cdn-si-edu.com/0AgwC4W8sNHUvp2My6XOaaCKZ2s=/1000x750/filters:no_upscale()/https://tf-cmsv2-smithsonianmag-media.s3.amazonaws.com/filer/f4/80/f48037c0-4b03-4def-a71c-7b33c79412b5/gettyimages-151039973.jpg";
    String url3 = "https://www.statnews.com/wp-content/uploads/2017/04/AP17114312968121-645x645.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//
//        SliderView sliderView = findViewById(R.id.imageSliderSC);
//
//        mSliderItemsList = new ArrayList<>();
//
//        AdapterSliderSplash adapter = new AdapterSliderSplash(this, mSliderItems);
//
//        sliderView.setSliderAdapter(adapter);
//
//        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
//        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
//        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
//        sliderView.setIndicatorSelectedColor(Color.WHITE);
//        sliderView.setIndicatorUnselectedColor(Color.GRAY);
//        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
//        sliderView.startAutoCycle();


        // we are creating array list for storing our image urls.
        mSliderItemsList = new ArrayList<>();

        // initializing the slider view.
        SliderView sliderView = findViewById(R.id.imageSliderSC);

        // adding the urls inside array list
        mSliderItemsList.add(new SliderItem(url1, "Immunization is a global health and development success story, saving millions of lives every year"));
        mSliderItemsList.add(new SliderItem(url2, " In Kenya, full immunization coverage for children aged 12–23 months currently stands at 79 and 75 %, respectively."));
        mSliderItemsList.add(new SliderItem(url3, "According to the 2014 Kenya Demographic and Health Survey (KDHS), about two of every three Kenyan children are considered “fully immunized"));

        // passing this array list inside our adapter class.
        AdapterSliderSplash adapter = new AdapterSliderSplash(this, mSliderItemsList);


        // below method is used to set auto cycle direction in left to
        // right direction you can change according to requirement.
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);

        // below method is used to
        // setadapter to sliderview.
        sliderView.setSliderAdapter(adapter);

        // below method is use to set
        // scroll time in seconds.
        sliderView.setScrollTimeInSec(3);

        // to set it scrollable automatically
        // we use below method.
        sliderView.setAutoCycle(true);

        // to start autocycle below method is used.
        sliderView.startAutoCycle();
    }
}