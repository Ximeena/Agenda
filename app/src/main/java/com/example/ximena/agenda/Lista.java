package com.example.ximena.agenda;

/**
 * Created by Ximena on 23/03/18.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;



public class Lista extends AppCompatActivity {

    ListView lv ;
    ArrayList<String> listaA;
    ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactos);
        lv = findViewById(R.id.lista);
        SQLAdmin db = new SQLAdmin(getApplicationContext(),null,null,1);
        listaA = db.llenar();
        adaptador = new ArrayAdapter(this, android.R.layout.activity_list_item,listaA);
        lv.setAdapter(adaptador);
    }

}