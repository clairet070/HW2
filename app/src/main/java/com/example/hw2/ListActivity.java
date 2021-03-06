package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ListFragment listFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

        //Check if we are at the land mode
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }



        listFragment = (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFrag);
        ArrayList<String> list = getIntent().getStringArrayListExtra("list");
        listFragment.setList(list);
        String info = "";
        for (int i = 0; i < list.size(); i++){
            info += (i + 1) + ": " + list.get(i) + "\n";
        }
        listFragment.setText(info);
    }

}