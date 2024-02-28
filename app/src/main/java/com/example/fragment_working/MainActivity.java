package com.example.fragment_working;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.fragment_working.fragments.MainFragment;
import com.example.fragment_working.models.Repository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository.fragmentManager = getSupportFragmentManager();  //инициализируем менеджер фрагментов

            Repository.fragment = new MainFragment();
            Repository.fragmentManager.beginTransaction()
                    .add(R.id.fragment_container, Repository.fragment).commit();



//    private void result (int money){
//        if (money > 150000){
//            txt.setText("Хорошо!");
//        } else if (money >=100000 && money <= 150000){
//            txt.setText("приемлемо");
//        } else{
//            Toast.makeText(MainActivity.this, "Ну, так нельзя ...!", Toast.LENGTH_LONG).show();
//        }
//    }
    }
}

