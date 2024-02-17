package com.example.fragment_working;

public class AgeException extends Exception{

    public AgeException(String message) {
        super(message);
        System.err.println("Ошибка: " + message);
        printStackTrace();
    }
}
