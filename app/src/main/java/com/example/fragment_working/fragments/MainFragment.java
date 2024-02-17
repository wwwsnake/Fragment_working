package com.example.fragment_working.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.fragment_working.AgeException;
import com.example.fragment_working.MainActivity;
import com.example.fragment_working.R;
import com.example.fragment_working.models.Repository;

public class MainFragment extends Fragment {
    private Button btn;
    private EditText edt;
    private TextView txt;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        init(view);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int age = 0;
                //TODO вариант фильтра ввода от всего кроме чисел
//                try {
//                     age = Integer.parseInt(edt.getText().toString());//получаем число, преобразуем в строку
//                }catch (Exception ex){
//                    Toast.makeText(MainActivity.this, "Некорректный ввод", Toast.LENGTH_LONG).show();
//                    age = -1;   //выполняем необходимую инициализацию в случае ошибки присваиваем "-1"
//                }

                String str = edt.getText().toString();

                if (check(str)){
                    age = Integer.parseInt(str);   //Парсим целое число из строки
                }
                else {
                    age = -1;
                }
                try {
                    boolean access = pass(age);
                    txt.setText(" " + access);
                    edt.setText(" ");//очищать предыдущий возраст
                    if (access) {
                        Repository.fragment = new EnterFragment();
                        Repository.fragmentManager.beginTransaction().replace(R.id.fragment_container,
                                Repository.fragment).commit();
                    }
                } catch (AgeException ex) {
                    Toast.makeText(getActivity(), "Вы ввели некоррекный возраст", Toast.LENGTH_LONG).show();
                }
            }
        });

return view;
    }

    /**
     * Метод для проверки введенного пользователем текста
     * @param str
     * @return
     */
    private boolean check(String str) {
        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7','8', '9'};


        if (str.charAt(0)== '0'){
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            for (int j = 0; j < numbers.length; j++) {
                if (str.charAt(i) == numbers[j]){
                    break;
                }
                if (j == numbers.length -1){
                    return false;
                }
            }
        }
        return true;
    }

    private void init(View view) {
        btn = view.findViewById(R.id.btn_age);
        txt = view.findViewById(R.id.txt_age);
        edt = view.findViewById(R.id.edt_age);
    }


    private boolean pass (int age) throws AgeException {
        if (age >= 18) {
            return true;
        } else if (age > 0 && age < 18) {
            return false;
        } else
            throw new AgeException("Введен некорректный возраст");
    }
    }



