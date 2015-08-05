package com.example.dariofernando.contador.threads;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by DarioFernando on 23/07/2015.
 */
public class ContadorThread extends Thread {

    public static final int CONTADOR=0;

    public interface ContadorI{
        Handler getHandler();
    }

    boolean running, paused;
    int cont;

    ContadorI contadorI;

    public ContadorThread(ContadorI contadorI){
        this.contadorI = contadorI;
    }


    @Override
    public void run() {
        running = true;
        cont = 0;
        while (running){
            try {
                Thread.sleep(1000);
                if(!paused) {
                    cont++;
                    Message msg = contadorI.getHandler().obtainMessage();
                    msg.what = CONTADOR;
                    msg.obj = ""+cont;

                    contadorI.getHandler().sendMessage(msg);

                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void setPaused(boolean paused){
        this.paused = paused;
    }

    public boolean isRunning(){
        return running;
    }

    public void stopContador(){
        running = false;
    }

}
