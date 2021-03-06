package com.example.hw2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private TextView lap;
    private ArrayList<String> list;

    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setRetainInstance(true);
        View view= inflater.inflate(R.layout.list_frag, container, false);
        lap = view.findViewById(R.id.laps);
        if (list == null) {
            list = new ArrayList<String>();
        }
        return view;
    }

    public void setText(String text) {
        lap.setText(text);
    }

    public void setList(ArrayList<String> l){
        list = l;
    }

    public ArrayList<String> getList(){
        return list;
    }

    public String getText() {

        return lap.getText().toString();
    }
}