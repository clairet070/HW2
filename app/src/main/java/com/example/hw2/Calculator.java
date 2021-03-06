package com.example.hw2;

import java.util.ArrayList;

public class Calculator {

    private ArrayList<String> laps = new ArrayList<String>();

    public ArrayList<String> getList(){
        return laps;
    }

    public void clearList(){
        laps.clear();
    }
    public String getFormatted(int seconds){
        int sec = seconds % 60;
        int hr = seconds / 60;
        int min = hr % 60;
        hr = hr / 60;


        return String.format("%02d:%02d:%02d", hr, min, sec);
    }
}
