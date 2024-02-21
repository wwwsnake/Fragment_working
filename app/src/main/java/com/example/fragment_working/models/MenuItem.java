package com.example.fragment_working.models;

public class MenuItem {

    private int img;
    private String description;
    private int price;


    private String title;

    public MenuItem(String title, int img, String description, int price) {
        this.title = title;
        this.img = img;
        this.description = description;
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }
}
