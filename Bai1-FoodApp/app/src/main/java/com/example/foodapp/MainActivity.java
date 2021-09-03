package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list;
    Button btnOrder;
    ItemsList []items;
    CustomListItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        prepareData();
        adapter = new CustomListItemAdapter(this, R.layout.list_item_custom, items);
        list.setAdapter(adapter);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                if(CustomListItemAdapter.selectedFoods.isEmpty())
                {
                    Toast.makeText(getApplication(),"Bạn chưa chọn món ăn !!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent sms_intent = new Intent(getApplication(), SMS.class);
                    startActivity(sms_intent);
                    if(!checkPermission(Manifest.permission.SEND_SMS))
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);
                }
            }
        });

    }

    private boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return  check == PackageManager.PERMISSION_GRANTED;
    }

    private void prepareData()
    {
        items = new ItemsList[12];
        items[0] = new ItemsList("Beefsteak","geo:0,0?q=14+Đồng+Đen+phường+14+Hồ+Chí+Minh+Bến+Nghé", R.drawable.f1, 180000,"file:///android_asset/food1.html", false);
        items[1] = new ItemsList("Pasta hải sản", "geo:10.7588455,106.6850891", R.drawable.f2, 200000, "file:///android_asset/food1.html", false);
        items[2] = new ItemsList("Burger bò phô mai", "geo:10.7588455,106.6850891", R.drawable.f3, 100000, "file:///android_asset/food1.html", false);
        items[3] = new ItemsList("Mì sốt kem", "geo:10.7588455,106.6850891", R.drawable.f4, 150000, "file:///android_asset/food1.html", false);
        items[4] = new ItemsList("Pizza gà", "geo:10.7588455,106.6850891", R.drawable.f5, 200000, "file:///android_asset/food1.html", false);
        items[5] = new ItemsList("Trà dâu","geo:0,0?q=14+Đồng+Đen+phường+14+Hồ+Chí+Minh+Bến+Nghé", R.drawable.f6, 50000,"file:///android_asset/food1.html", false);
        items[6] = new ItemsList("Trà chanh", "geo:10.7588455,106.6850891", R.drawable.f7, 30000, "file:///android_asset/food1.html", false);
        items[7] = new ItemsList("Burger gà quay", "geo:10.7588455,106.6850891", R.drawable.f8, 800000, "file:///android_asset/food1.html", false);
        items[8] = new ItemsList("Dâu tây đá xay", "geo:10.7588455,106.6850891", R.drawable.f9, 60000, "file:///android_asset/food1.html", false);
        items[9] = new ItemsList("Burger gà rán", "geo:10.7588455,106.6850891", R.drawable.f10, 800000, "file:///android_asset/food1.html", false);
        items[10] = new ItemsList("Pizza Hawaii", "geo:10.7588455,106.6850891", R.drawable.f11, 200000, "file:///android_asset/food1.html", false);
        items[11] = new ItemsList("Choco Muffin", "geo:10.7588455,106.6850891", R.drawable.f12, 25000, "file:///android_asset/food1.html", false);
    }

    public void init()
    {
        list = findViewById(R.id.listMain);
        btnOrder = findViewById(R.id.btnOrder);
    }

}