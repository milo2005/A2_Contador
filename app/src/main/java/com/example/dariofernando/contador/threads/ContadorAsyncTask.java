package com.example.dariofernando.contador.threads;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * Created by DarioFernando on 23/07/2015.
 */
public class ContadorAsyncTask extends AsyncTask<Integer,Integer,Integer> {

    public interface ContadorAsyncTaskI{
        void setTime(int time);
    }


    boolean paused, runing;
    int cont;

    ContadorAsyncTaskI contadorAsyncTaskI;

    public ContadorAsyncTask(ContadorAsyncTaskI contadorAsyncTaskI) {
        this.contadorAsyncTaskI = contadorAsyncTaskI;
    }

    @Override
    protected Integer doInBackground(Integer... params) {
        runing = true;
        while(runing){

            try {
                Thread.sleep(1000);
                if(!paused)
                    cont++;
                publishProgress(cont);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        contadorAsyncTaskI.setTime(values[0]);
    }

    public boolean isRuning(){
        return  runing;
    }

    public void setPaused(boolean paused){
        this.paused = paused;
    }

    public void stopContador(){
        runing = false;
    }

}
