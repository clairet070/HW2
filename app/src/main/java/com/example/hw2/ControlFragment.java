package com.example.hw2;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class ControlFragment extends Fragment implements View.OnClickListener{

    Button startB;
    Button lapB;
    Button resetB;
    Button n;
    TextView time;
    //declare interaction listener
    private OnFragmentInteractionListener mListener;

    public ControlFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.control_frag, container, false);

        //initialize buttons and textview
        startB = (Button) view.findViewById(R.id.start);
        lapB= (Button) view.findViewById(R.id.lap);
        resetB = (Button) view.findViewById(R.id.reset);
        n = (Button) view.findViewById(R.id.next);
        time= (TextView) view.findViewById(R.id.timer);

        //add listeners
        startB.setOnClickListener(this);
        lapB.setOnClickListener(this);
        resetB.setOnClickListener(this);
        n.setOnClickListener(this);


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof OnFragmentInteractionListener){
            this.mListener= (OnFragmentInteractionListener) context;
        }else{
            throw new RuntimeException(context.toString()+" must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void onClick(View view){
        if(view.getId() == startB.getId()){

            mListener.onButtonClicked(0);

        }else if (view.getId() == lapB.getId()){

            mListener.onButtonClicked(1);

        } else if(view.getId() == resetB.getId() ){

            mListener.onButtonClicked(2);
        }
        else if(view.getId() == n.getId() ){
            mListener.onButtonClicked(3);
        }
    }

    public void setText(String text) {
        time.setText(text);
    }

    public interface OnFragmentInteractionListener{
        void onButtonClicked(int infoID);
    }

}