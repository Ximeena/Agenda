package com.example.ximena.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nom, num;
    Button BList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nom = findViewById(R.id.editText1);
        num = findViewById(R.id.editText2);
        BList = findViewById(R.id.l);

        BList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(getApplicationContext(),Lista.class);
                startActivity(intento);
            }
        });
    }

    public void agregar(View v) {
        SQLAdmin admin = new SQLAdmin(this, "admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre = nom.getText().toString();
        String numero = num.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("numero", numero);
        bd.insert("Contactos", null, registro);
        bd.close();
        nom.setText("");
        num.setText("");
        Toast.makeText(this, "Contacto almacenado", Toast.LENGTH_SHORT).show();
    }

    public void buscar(View v) {
        SQLAdmin admin = new SQLAdmin(this, "admin", null, 1);
        SQLiteDatabase bd = admin.getReadableDatabase();
        Cursor fila = bd.rawQuery("SELECT * FROM Contactos WHERE nombre = '" + nom.getText().toString() + "'", null);
        if (fila.moveToFirst()) {
            nom.setText(fila.getString(0));
            num.setText(fila.getString(1));
        } else
            Toast.makeText(this, "No existe contacto", Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void eliminar(View v) {
        SQLAdmin admin = new SQLAdmin(this, "admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        int cant = bd.delete("contactos", "nombre = '" + nom.getText().toString() + "'", null);
        bd.close();
        nom.setText("");
        num.setText("");
        if (cant == 1)
            Toast.makeText(this, "Contacto eliminado", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe contato", Toast.LENGTH_SHORT).show();
    }

}
