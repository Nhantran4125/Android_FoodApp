package com.example.foodapp;

public class ItemsList {
    private String foodName, foodLocation, foodURL;
    private int foodImage, foodPrice;
    private boolean selected;

    public ItemsList()
    {

    }

    public ItemsList(String name, String location, int image, int price, String url, boolean selected)
    {
        this.foodName = name;
        this.foodLocation = location;
        this.foodImage = image;
        this.foodPrice = price;
        this.foodURL = url;
        this.selected =  selected;
    }

    public String getFoodName()
    {
        return foodName;
    }

    public String getFoodLocation()
    {
        return foodLocation;
    }

    public int getFoodImage()
    {
        return foodImage;
    }

    public int getFoodPrice()
    {
        return foodPrice;
    }

    public String getFoodURL()
    {
        return foodURL;
    }

    public boolean getSelected()
    {
        return selected;
    }

    public void setFoodName(String name)
    {
        this.foodName = name;
    }

    public void setFoodLocation(String location)
    {
        this.foodLocation = location;
    }

    public void setFoodImage(int image)
    {
        this.foodImage = image;
    }

    public void setFoodPrice(int price)
    {
        this.foodPrice = price;
    }

    public void setFoodURL(String url)
    {
        this.foodURL = url;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }
}
