package com.example.fragment_working.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragment_working.MapActivity;
import com.example.fragment_working.R;
import com.example.fragment_working.adapters.ChooseAdapter;
import com.example.fragment_working.models.Model;

import java.util.ArrayList;

public class EnterFragment extends Fragment implements View.OnTouchListener{

    private RecyclerView recyclerView;
    private FragmentManager fragmentManager;
    private Fragment fragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enter_fragment, container, false);
        init(view);
        return view;
    }

    private void init(View view) {  //метод для инициализации значений по умолчанию
        recyclerView = view.findViewById(R.id.list_items);
        ArrayList<Model> models = new ArrayList<>();
        models.add(new Model(R.drawable.drinks_menu, "Напитки"));
        models.add(new Model(R.drawable.food_menu, "Еда"));
        models.add(new Model(R.drawable.navigation, "Карта заведения"));

        ChooseAdapter adapter = new ChooseAdapter(getActivity(), models);  //Адаптер для связки списка моделей и разметки
        recyclerView.setAdapter(adapter);  //назначаем адаптер списку
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));  //назначаем в каком виде выведется на экран

        adapter.setListener(new ChooseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (position == 2){
                    Intent intent = new Intent(getActivity(), MapActivity.class);
                    startActivity(intent);
                } else if (position == 0){
                    fragmentManager = getParentFragmentManager(); //получаем родительский менеджер фрагментов
                    fragment = new DrinksFragment();
                    fragmentManager.beginTransaction()
                            .addToBackStack("enter")       // Сохраняем фрагмент
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                } else if (position == 1){
                    fragmentManager = getParentFragmentManager(); //получаем родительский менеджер фрагментов
                    fragment = new FoodFragment();
                    fragmentManager.beginTransaction()
                            .addToBackStack("enter")
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }
            }
        });
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
