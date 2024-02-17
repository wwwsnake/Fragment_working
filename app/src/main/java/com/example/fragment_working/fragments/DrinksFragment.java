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

public class DrinksFragment extends Fragment {
    private TextView drink_name;
    private TextView drink_description;
    private TextView drink_price;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drinks_fragment, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        drink_name = view.findViewById(R.id.drink_name);
        drink_description = view.findViewById(R.id.drink_description);
        drink_price = view.findViewById(R.id.drink_price);
    }
}
