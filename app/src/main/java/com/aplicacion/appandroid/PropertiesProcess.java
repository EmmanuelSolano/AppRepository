package com.aplicacion.appandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PropertiesProcess extends AppCompatActivity {

    public static String processToExecute = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_process);

        final Button btn_realMemory, btn_virtualMemory, btn_cpu, btn_priority, btn_executeTime;

        btn_realMemory = (Button) findViewById(R.id.btn_memoriaReal);
        btn_virtualMemory = (Button) findViewById(R.id.btn_memoriaVirtual);
        btn_cpu = (Button) findViewById(R.id.btn_cpu);
        btn_priority = (Button) findViewById(R.id.btn_prioridad);
        btn_executeTime = (Button) findViewById(R.id.btn_tiempo);

        btn_realMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_virtualMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_cpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_priority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_executeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
