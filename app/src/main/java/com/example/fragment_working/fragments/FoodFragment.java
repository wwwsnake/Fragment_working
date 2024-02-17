package com.example.fragment_working.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fragment_working.R;

public class FoodFragment extends Fragment {
    private TextView food_name;
    private TextView food_description;
    private TextView food_price;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_fragment, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        food_name = view.findViewById(R.id.food_name);
        food_description = view.findViewById(R.id.food_description);
        food_price = view.findViewById(R.id.food_price);
    }
}
