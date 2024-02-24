package com.example.fragment_working.models;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class Repository {
    public static FragmentManager fragmentManager;
    public static Fragment fragment;

    public static ArrayList<MenuItem> items = new ArrayList<>(); // Хранилище заказанных товаров
}
