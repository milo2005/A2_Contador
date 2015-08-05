package com.example.dariofernando.contador;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dariofernando.contador.threads.ContadorAsyncTask;
import com.example.dariofernando.contador.threads.ContadorThread;


public class ContadorAsyncActivity extends ActionBarActivity implements View.OnClickListener, ContadorAsyncTask.ContadorAsyncTaskI {


    TextView txt;
    Button start, pause, stop;

    ContadorAsyncTask contador;


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

        switch (v.getId()) {
            case R.id.btn_start:
                if (contador == null) {
                    contador = new ContadorAsyncTask(this);
                    contador.execute(0);
                } else
                    contador.setPaused(false);
                break;
            case R.id.btn_pause:
                if (contador != null && contador.isRuning())
                    contador.setPaused(true);
                break;
            case R.id.btn_stop:
                if (contador != null)
                    contador.stopContador();
                contador = null;
                break;
        }
    }

    @Override
    public void setTime(int time) {
        txt.setText(time+"");
    }
}
