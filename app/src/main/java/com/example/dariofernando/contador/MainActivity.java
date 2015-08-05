package com.example.dariofernando.contador;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dariofernando.contador.threads.ContadorThread;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, ContadorThread.ContadorI {



    Button start, pause, stop;
    TextView txt;

    ContadorThread contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.btn_start);
        pause = (Button) findViewById(R.id.btn_pause);
        stop = (Button) findViewById(R.id.btn_stop);

        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        txt = (TextView) findViewById(R.id.txt);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_start:
                if(contador == null ){
                    contador =  new ContadorThread(this);
                    contador.start();
                }else
                    contador.setPaused(false);
                break;
            case R.id.btn_pause:

                if(contador != null && contador.isRunning())
                    contador.setPaused(true);

                break;
            case R.id.btn_stop:

                if(contador!=null)
                    contador.stopContador();
                contador = null;
                break;


        }

    }


    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if(msg.what==ContadorThread.CONTADOR){
                String time = (String) msg.obj;
                txt.setText(time);
            }
        }
    };


    @Override
    public Handler getHandler() {
        return handler;
    }
}
