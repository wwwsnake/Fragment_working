package com.example.fragment_working;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class); //указываем активность, которую хотим вызвать
                    startActivity(intent);//вызываем
                    finish();//завершаем работу текущей активности (заставки), после чего уже запустится само приложение
                } catch (InterruptedException e) {

                }
            }
        };

        Thread thread = new Thread(runnable);//создаем задачу
        thread.start();//запускаем
    }
}




