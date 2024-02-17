package com.example.fragment_working.models;

public class Model {   //Модель сущности, которая будет отвечать за категорию клуба (еда, напитки и т.д.)
    private int img;
    private String title;

    public Model(int img, String title) {
        this.img = img;
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }
}
