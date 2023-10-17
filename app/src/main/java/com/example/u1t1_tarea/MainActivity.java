package com.example.u1t1_tarea;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvLista1;
    private LinearLayoutManager llm;

    private ArrayList<Persona> personas;

    private String nombre;
    private String edad;

    private void inicializarPersonas(){
        personas = new ArrayList<>();
        personas.add(new Persona("Kamal Namasté", "50", R.drawable.foto1));
        personas.add(new Persona("Johnny Melavo", "32", R.drawable.foto2));
        personas.add(new Persona("Ching Chong Aloz", "22", R.drawable.foto3));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idSeleccionado = item.getItemId();
        if (idSeleccionado == R.id.menuNuevaPersona) {
            Toast.makeText(this, "¡Hola, Pequeño Timmy!", Toast.LENGTH_SHORT).show();
            personas.add(new Persona("Pequeño Timmy", "?", R.drawable.foto4));
            adapter.notifyDataSetChanged();
            return true;
        } else if (idSeleccionado == R.id.menuBorrarPersona) {
            Toast.makeText(this, "¡Nooo Pequeño Timmy!", Toast.LENGTH_SHORT).show();
            personas.remove(3);
            adapter.notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private PersonasAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rvLista1 = findViewById(R.id.rvLista1);
        rvLista1.setHasFixedSize(true);

        llm = new LinearLayoutManager(getApplicationContext());
        rvLista1.setLayoutManager(llm);

        inicializarPersonas();
        adapter = new PersonasAdapter(personas, new OnItemClickListener(){
            @Override
            public void onItemClick(Persona persona) {
                nombre = persona.getNombre();
                if(nombre.equals("Kamal Namasté")){
                    Toast.makeText(getApplicationContext(), nombre + " quien te hace los programas", Toast.LENGTH_SHORT).show();
                }
                else if (nombre.equals("Johnny Melavo")){
                    Toast.makeText(getApplicationContext(), nombre + " tu primo el delincuente", Toast.LENGTH_SHORT).show();
                }
                else if (nombre.equals("Ching Chong Aloz")){
                    Toast.makeText(getApplicationContext(), nombre + " NO LLAMES A TU EX", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "¿Y éste quien es?", Toast.LENGTH_SHORT).show();
            }

        });

        rvLista1.setAdapter(adapter);
    }
}