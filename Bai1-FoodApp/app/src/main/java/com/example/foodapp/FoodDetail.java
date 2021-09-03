package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FoodDetail extends AppCompatActivity {
    private WebView webFoodDetail;
    private String url;

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.food_detail);

        init();
        Intent intent = getIntent();
        String url = intent.getStringExtra("URL");
        webFoodDetail.setWebViewClient(new WebViewClient());
        webFoodDetail.loadUrl(url);

    }

    public void init()
    {
        webFoodDetail = findViewById(R.id.webFoodDetail);
    }

}
