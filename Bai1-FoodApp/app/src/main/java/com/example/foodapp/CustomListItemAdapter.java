package com.example.foodapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.recyclerview.widget.RecyclerView.*;

public class CustomListItemAdapter extends ArrayAdapter<ItemsList> {

    Context context;
    ItemsList[] items;
    public static ArrayList<String> selectedFoods = new ArrayList<String>();


    public CustomListItemAdapter(Context context, int layoutTobeInflated, ItemsList[] items) {
        super(context, R.layout.list_item_custom, items);
        this.context = context;
        this.items = items;
    }


    private View.OnClickListener itemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.foodName:
                    TextView fn = (TextView) v;
                    String url = (String) fn.getTag();
                    Intent intent = new Intent(getContext(), FoodDetail.class);
                    intent.putExtra("URL", url);
                    context.startActivity(intent);

                    return;

                case R.id.foodImage:
                    ImageView fi = (ImageView) v;
                    String location2 = (String) fi.getTag();
                    Toast.makeText(context, location2.toString(), Toast.LENGTH_SHORT).show();
                    showView(location2.toString());
                    return;
            }
        }
    };


    private void showView(String v)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(v));
        if(intent.resolveActivity(context.getPackageManager())!=null)
            context.startActivity(intent);
    }

    private class ViewHolder
    {
        private ImageView foodImage;
        private TextView foodLocation, foodName, foodURL, foodPrice;
        private CheckBox checkFood;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if(convertView == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_item_custom,null);

            holder = new ViewHolder();
            holder.foodImage = (ImageView) convertView.findViewById(R.id.foodImage);
            holder.foodLocation = (TextView) convertView.findViewById(R.id.foodLocation);
            holder.foodName = (TextView) convertView.findViewById(R.id.foodName);
            holder.foodURL = (TextView) convertView.findViewById(R.id.foodLink);
            holder.foodPrice = (TextView) convertView.findViewById(R.id.foodPrice);
            holder.checkFood = (CheckBox) convertView.findViewById(R.id.checkFood);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.foodLocation.setText(items[position].getFoodLocation().toString());

        holder.foodImage.setImageResource(items[position].getFoodImage());
        holder.foodImage.setTag(holder.foodLocation.getText().toString());

        holder.foodURL.setText(items[position].getFoodURL().toString());

        holder.foodName.setText(items[position].getFoodName().toString());
        holder.foodName.setTag(holder.foodURL.getText().toString());

        holder.foodPrice.setText("Giá: "+items[position].getFoodPrice()+" Đ");

        holder.checkFood.setTag(holder.foodName.getText().toString());
        holder.checkFood.setChecked(items[position].getSelected());
        holder.checkFood.setTag(items[position]);

        holder.foodName.setOnClickListener(itemClickListener);
        holder.foodImage.setOnClickListener(itemClickListener);
        holder.checkFood.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                CheckBox cb = (CheckBox) v;
                ItemsList item = (ItemsList) cb.getTag();
                item.setSelected(cb.isChecked());

                String food = item.getFoodName();
                if(cb.isChecked())
                {
                    selectedFoods.add(food);
                    Toast.makeText(context, food, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    selectedFoods.remove(food);
                    Toast.makeText(context, "Bỏ chọn món " + food, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }



}
