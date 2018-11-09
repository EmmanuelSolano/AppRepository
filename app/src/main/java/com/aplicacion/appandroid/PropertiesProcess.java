package com.aplicacion.appandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class PropertiesProcess extends AppCompatActivity {

    public static String processToExecute = "";
    public ViewProcess viewProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_process);

        final Button btn_realMemory, btn_virtualMemory, btn_cpu, btn_priority, btn_executeTime, btn_kill, btn_stop, btn_createChild;

        btn_realMemory = (Button) findViewById(R.id.btn_memoriaReal);
        btn_virtualMemory = (Button) findViewById(R.id.btn_memoriaVirtual);
        btn_cpu = (Button) findViewById(R.id.btn_cpu);
        btn_priority = (Button) findViewById(R.id.btn_prioridad);
        btn_executeTime = (Button) findViewById(R.id.btn_tiempo);
        btn_kill = (Button) findViewById(R.id.btn_killProcess);
        btn_stop = (Button) findViewById(R.id.btn_stopProcess);
        btn_createChild = (Button) findViewById(R.id.btn_createChild);

        final List_Process listProcess = new List_Process();

        final String processId = viewProcess.itemSelectedInterests;
        final ArrayList<String> listofProcess = viewProcess.procesos;

        btn_realMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadProcessSpecify(processId, listofProcess, "realMemory");
            }
        });

        btn_virtualMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadProcessSpecify(processId, listofProcess, "virtualMemory");
            }
        });

        btn_cpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadProcessSpecify(processId, listofProcess, "cpu");
            }
        });

        btn_priority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadProcessSpecify(processId, listofProcess, "priority");
            }
        });

        btn_executeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadProcessSpecify(processId, listofProcess, "time");
            }
        });

        btn_kill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncTask<Integer, Void, Void>(){
                    @Override
                    protected Void doInBackground(Integer... params) {
                        try {
                            String pid = idfindProcess(processId);
                            boolean done = listProcess.modifyProcess("finish",pid);
                            if(done)
                                Toast.makeText(PropertiesProcess.this, "Proceso "+pid+" terminado", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(PropertiesProcess.this, "No es posible realizar la acción", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute(1);
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncTask<Integer, Void, Void>(){
                    @Override
                    protected Void doInBackground(Integer... params) {
                        try {
                            String pid = idfindProcess(processId);
                            boolean done = listProcess.modifyProcess("stopProcess",pid);
                            if(done)
                                Toast.makeText(PropertiesProcess.this, "Proceso "+pid+" detenido", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(PropertiesProcess.this, "No es posible realizar la acción", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute(1);
            }
        });

        btn_createChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AsyncTask<Integer, Void, Void>(){
                    @Override
                    protected Void doInBackground(Integer... params) {
                        try {
                            String pid = idfindProcess(processId);
                            boolean done = listProcess.modifyProcess("createProcess",pid);
                            if(done)
                                Toast.makeText(PropertiesProcess.this, "Se creó un proceso hijo del proceso "+pid, Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(PropertiesProcess.this, "No es posible realizar la acción", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute(1);
            }
        });
    }


    public void loadProcessSpecify(String idProcess, ArrayList<String> process, String actionToexecute){
        String pid = "";
        String letter = "";
        for(int i = 0; i < process.size(); i++){
            pid="";
            letter = process.get(i);
            String letter2 = idfindProcess(letter);
            pid = idfindProcess(idProcess);
            if(pid.equals(letter2))
                i=process.size();
        }
        if(actionToexecute.equalsIgnoreCase("realMemory")){
            processToExecute = getId(letter, 9);
        }else if(actionToexecute.equalsIgnoreCase("virtualMemory")){
            processToExecute = getId(letter, 5);
        }else if(actionToexecute.equalsIgnoreCase("cpu")){
            processToExecute = getId(letter, 11);
        }else if(actionToexecute.equalsIgnoreCase("priority")){
            processToExecute = getId(letter, 3);
        }else if(actionToexecute.equalsIgnoreCase("time")){
            processToExecute = getId(letter, 7);
        }
        Intent intent=new Intent( PropertiesProcess.this, InformationOfProcessSelected.class);

        Bundle bundle=new Bundle();
        bundle.putString("NAME", "Procesos");

        //coloca el mensaje que la actividad va a transmitir
        intent.putExtras(bundle);


        //hace el paso de actividades

        startActivity(intent);
    }

    public String getId(String register, int position){
        String returnValue="";
        int count = 0;
        for (int i = 0; i<register.length();i++){
            char letter = register.charAt(i);
            if(count==position){
                for (int c=i;c<register.length();c++){
                    char word = register.charAt(c);
                    if(word!=' ' || position==11)
                        returnValue+=word;
                    else if(word==' ' && position!=11)
                        c=register.length();
                }
                i = register.length();
            }else if(letter==' ') {
                count++;
            }
        }
        return returnValue;
    }

    public String idfindProcess(String letter){
        String result="";
        for(int i=0;i<letter.length();i++){
            char word = letter.charAt(i);
            if(word!= ' ')
                result+=""+word;
            else
                i=letter.length();
        }
        return result;
    }

    private HashMap<String, String> showProcess(String key, String name) {
        HashMap<String, String> processID = new HashMap<String, String>();
        processID.put(key, name);
        return processID;

    }

}
