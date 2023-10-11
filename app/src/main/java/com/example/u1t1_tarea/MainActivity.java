package com.example.u1t1_tarea;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvLista1;
    private LinearLayoutManager llm;

    private ArrayList<Persona> personas;

    private void inicializarPersonas(){
        personas = new ArrayList<>();
        personas.add(new Persona("Kamal Namasté", "50", R.drawable.foto1));
        personas.add(new Persona("Johnny Melavo", "32", R.drawable.foto2));
        personas.add(new Persona("Ching Chong Aloz", "22", R.drawable.foto3));
    }
    private PersonasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvLista1 = findViewById(R.id.rvLista1);
        rvLista1.setHasFixedSize(true);

        llm = new LinearLayoutManager(getApplicationContext());
        rvLista1.setLayoutManager(llm);

        inicializarPersonas();
        adapter = new PersonasAdapter(personas);
        rvLista1.setAdapter(adapter);

    }


}