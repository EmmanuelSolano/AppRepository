package com.aplicacion.appandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class SelectState extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_state);


        final ListView listView = findViewById(R.id.list_states);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String message = "", numberofState="";
                message+=listView.getItemAtPosition(position).toString();
                int startPosition = 9;
                while(startPosition<message.length()-1){
                    numberofState+=message.charAt(startPosition);
                    startPosition++;
                }
                Toast.makeText(SelectState.this, "Has seleccionado: "+numberofState, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent( SelectState.this, PropertiesProcess.class);

                Bundle bundle=new Bundle();
                bundle.putString("NAME", "Process saved");

                //coloca el mensaje que la actividad va a transmitir
                intent.putExtras(bundle);


                //hace el paso de actividades

                startActivity(intent);
            }
        });
    }
}
