package com.example.fragment_working.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fragment_working.R;
import com.example.fragment_working.adapters.MenuAdapter;
import com.example.fragment_working.models.MenuItem;
import com.example.fragment_working.models.Repository;

import java.util.ArrayList;

public class DrinksFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drinks_fragment, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
      recyclerView = view.findViewById(R.id.drinks_list);
      button = view.findViewById(R.id.btn_back);
      button.setOnClickListener(this);

        ArrayList<MenuItem> drinks = new ArrayList<>();
        drinks.add(new MenuItem("Пина колада", R.drawable.drink1, "Легкий и невероятно " +
                "приятный напиток с чудным послевкусием", 400));
        drinks.add(new MenuItem("Золотая фея", R.drawable.drink2, "Сладкий нежный " +
                "напиток", 500));
        drinks.add(new MenuItem("Голубая лагуна", R.drawable.drink3, "Невероятно комбинированный" +
                " вкус джина и вишни", 700));
        drinks.add(new MenuItem("Маргарита", R.drawable.drink4, "Нежный вкусный" +
                " коктейль", 300));

        ArrayList<MenuItem> orders = new ArrayList<>();

        MenuAdapter adapter = new MenuAdapter(getActivity(), drinks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter.setListener(new MenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                orders.add(drinks.get(position));
                Repository.items.add(drinks.get(position)); // Добавляем заказ в глобальное хранилище
                Toast.makeText(getActivity(),"Сумма заказа: " + String.valueOf(countSum(orders)), Toast.LENGTH_LONG).show();
            }
        });
    }

    public static int countSum(ArrayList<MenuItem> orders) {
       int res = 0;
        for (int i = 0; i < orders.size(); i++) {
            res += orders.get(i).getPrice();
        }
        return res;
    }

    /*
    Стек - структура данных работающая по принципу LIFO(Last in first out) - последним зашел, первым
    вышел. Аналогия со стопкой блинов - первым берется верхний, который последним был положен.
    Базовые операции стека: push(добавить) pop(извлечь) peak(показать вершину)
    Обратная структура данных для стека - очередь

    Задача: Есть математическое выражение удостовериться в правильности расположени скобок в этом
    выражении
    Например: []()(({})) - Правильное выражение, а [(]){}(({})) - выражение неправильное
    Подсказка: Подумайте о стековом решении этой задачи
     */
    @Override
    public void onClick(View v) {
        Repository.fragmentManager.popBackStack();  //  Закрываем текущий фрагмент
    }
}
