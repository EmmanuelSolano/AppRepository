package com.aplicacion.appandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {


    public static String userString, ipString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText lbl_user, lbl_ip, lbl_password;
        final Button process;

        lbl_user = (EditText) findViewById(R.id.txt_user);
        lbl_ip = (EditText) findViewById(R.id.tfd_ip);
        lbl_password = (EditText) findViewById(R.id.lbl_password);

        process = (Button) findViewById(R.id.btn_process);

        userString = lbl_user.getText().toString();
        ipString = lbl_ip.getText().toString();

        new AsyncTask<Integer, Void, Void>(){
            @Override
            protected Void doInBackground(Integer... params) {
                try {
                    List_Process list = new List_Process();
                    list.lista(userString,ipString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(1);

        process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadProcessToServer();
                Intent intent=new Intent( MainActivity.this, ViewProcess.class);

                Bundle bundle=new Bundle();
                bundle.putString("NAME", "Procesos");

                //coloca el mensaje que la actividad va a transmitir
                intent.putExtras(bundle);


                //hace el paso de actividades

                startActivity(intent);
                Toast.makeText(MainActivity.this, "Process", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loadProcessToServer(){
        new AsyncTask<Integer, Void, Void>(){
            @Override
            protected Void doInBackground(Integer... params) {
                try {
                    List_Process list = new List_Process();
                    list.lista(userString,ipString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(1);
    }
}
