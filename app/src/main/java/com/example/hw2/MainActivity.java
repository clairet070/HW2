package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ControlFragment.OnFragmentInteractionListener{

    boolean run = true;
    Calculator c;
    ListFragment listFragment;
    ControlFragment ctrlFragment;
    MyAsyncTask myAsyncTask;
    //declare counter
    static int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = new Calculator();
        //create fragment references
        listFragment= (ListFragment) getSupportFragmentManager().findFragmentById(R.id.listFrag);
        ctrlFragment= (ControlFragment) getSupportFragmentManager().findFragmentById(R.id.controlFrag);
        //initialize AsyncTask class
        myAsyncTask= new MyAsyncTask();
        if (count != 0){
            myAsyncTask.execute(0);
        }
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt("count");
    }

    @Override
    protected void onDestroy() {
        //checking if asynctask is still runnning
        if(myAsyncTask!=null && myAsyncTask.getStatus()== AsyncTask.Status.RUNNING){
            //cancel the task before destroying activity
            myAsyncTask.cancel(true);
            myAsyncTask= null;
        }
        super.onDestroy();
    }

    @Override
    public void onButtonClicked(int infoID) {
        if(infoID == 0){
            if(myAsyncTask.getStatus()!= AsyncTask.Status.RUNNING){
                run = true;
                myAsyncTask = new MyAsyncTask();
                //passing in 20 as the limit and executing the task
                myAsyncTask.execute(0);
            }
        }
        else if (infoID == 1){
            if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
                listFragment.setText(listFragment.getText() + "\n" + c.getFormatted(count));
            }
            c.getList().add(c.getFormatted(count));
        }
        else if (infoID == 2) {
            run = false;
            count = 0;
            ctrlFragment.time.setText("00:00:00");
            c.clearList();
            if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
                listFragment.setText("");
            }
            myAsyncTask.cancel(true);
        }
        else if (infoID == 3){
            Intent in = new Intent(this, ListActivity.class);
            ArrayList<String> list = c.getList();
            in.putStringArrayListExtra("list", list);
            startActivity(in);

        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    private class MyAsyncTask extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected Void doInBackground(Integer... params) {
            while(run){
                try{
                    //checking if the asynctask has been cancelled, end loop if so
                    if(isCancelled()) break;

                    Thread.sleep(1000);

                    count++;

                    //send count to onProgressUpdate to update UI
                    publishProgress(count);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //setting count to 0 and setting textview to 0 after doInBackground finishes running
            //count= 0;
            //listFragment.lap.setText("00:00:00");

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            ctrlFragment.time.setText(c.getFormatted(values[0]));

        }
    }

}