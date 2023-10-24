package com.example.u1t1_tarea;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity
        extends AppCompatActivity
        implements PersonasAdapter.OnItemSelectListener {

    private RecyclerView rvLista1;
    private LinearLayoutManager llm;

    private ArrayList<Persona> personas;

    private String nombre;
    private String edad;

    private AlertDialog dialog;

    private final String CHANNEL_ID = "mi_canal";
    private NotificationManager nManager;

    //Inicializamos las personas con el método que hemos creado en la clase.
    private void inicializarPersonas() {
        personas = new ArrayList<>();
        personas.add(new Persona("Kamal Namasté", "50", R.drawable.foto1));
        personas.add(new Persona("Johnny Melavo", "32", R.drawable.foto2));
        personas.add(new Persona("Ching Chong Aloz", "22", R.drawable.foto3));
    }

    //Creamos el menú. Si devuelve true significa que se ha creado correctamente.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflamos el menú con el layout que hemos creado
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Creamos el listener del menú. Si devuelve true significa que se ha creado correctamente.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Obtenemos el id del item seleccionado
        int idSeleccionado = item.getItemId();
        //Comprobamos que el id del item seleccionado es el que queremos
        if (idSeleccionado == R.id.menuNuevaPersona) {
            Toast.makeText(this, "¡Hola, Pequeño Timmy!", Toast.LENGTH_SHORT).show();
            personas.add(new Persona("Pequeño Timmy", "?", R.drawable.foto4));
            //Notificamos al adaptador que se ha añadido un elemento a la lista
            adapter.notifyDataSetChanged();
            //Devolvemos true para indicar que se ha seleccionado un item
            return true;
            //Si el item seleccionado es "Borrar Persona" borramos al pobre Pequeño Timmy
        } else if (idSeleccionado == R.id.menuBorrarPersona) {
            Toast.makeText(this, "¡Nooo Pequeño Timmy!", Toast.LENGTH_SHORT).show();
            personas.remove(3);
            //Notificamos al adaptador que se ha borrado un elemento de la lista
            adapter.notifyDataSetChanged();
            //Devolvemos true para indicar que se ha seleccionado un item
            return true;
        }
        //Si no se ha seleccionado ningún item, llamamos al método de la clase padre
        return super.onOptionsItemSelected(item);
    }

    private PersonasAdapter adapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Seteamos la vista principal como activity_main
        setContentView(R.layout.activity_main);

        //Seteamos la toolbar como action bar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Inicializamos el RecyclerView
        rvLista1 = findViewById(R.id.rvLista1);
        rvLista1.setHasFixedSize(true);

        //Inicializamos el LinearLayoutManager para que el RecyclerView se muestre como una lista
        llm = new LinearLayoutManager(getApplicationContext());
        rvLista1.setLayoutManager(llm);

        //Inicializamos las personas
        inicializarPersonas();
        //Inicializamos el adaptador
        adapter = new PersonasAdapter(personas, this) {

            //Implementamos los métodos de los listeners
            //OnItemClick sirve para cuando se hace click en un elemento de la lista
            public void onItemClick(Persona persona) {
                nombre = persona.getNombre();
                edad = persona.getEdad();
                Toast.makeText(MainActivity.this, "¡Hola, " + nombre + "!", Toast.LENGTH_SHORT).show();
            }

            //OnMenuClick sirve para cuando se hace click en el menú de un elemento de la lista
            public void onMenuClick(Persona persona) {
                nombre = persona.getNombre();
                edad = persona.getEdad();
                Toast.makeText(MainActivity.this, "¡Adiós, " + nombre + "!", Toast.LENGTH_SHORT).show();
            }
        };
        //Seteamos el adaptador al RecyclerView
        rvLista1.setAdapter(adapter);
    }
    //Implementamos los métodos de los listeners
    //OnSelect sirve para cuando se hace click en un elemento de la lista y qué accion realiza
    @Override
    public void onSelect(Persona persona) {
        //Creamos un AlertDialog para mostrar la edad de la persona seleccionada
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Seteamos el mensaje del AlertDialog con el nombre de la persona seleccionada
        builder.setMessage(getString(R.string.title_dialog, persona.getNombre())).
                //Seteamos el botón positivo con el texto "Sí" y un listener que muestra un Toast con la edad de la persona
                setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    //Creamos un Toast personalizado
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, persona.getEdad() + " años", Toast.LENGTH_SHORT).show();
                    }
                    //Seteamos el botón negativo con el texto "No" y un listener que cierra el AlertDialog
                }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            //Creamos un Toast personalizado (en este caso, cerrar el menu)
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
        //Creamos el AlertDialog y lo mostramos.
        builder.create().show();
    }

    //OnMenuAction sirve para cuando se hace click en el menú de un elemento de la lista y qué accion realiza
    @Override
    public void onMenuAction(Persona persona, MenuItem item) {
        int idSeleccionado = item.getItemId();
        if (idSeleccionado == R.id.menuNuevaPersona) {

        } else if (idSeleccionado == R.id.menuBorrarPersona) {


        }
    }
    //Creamos un método para lanzar un Toast personalizado
    private void lanzarToastPersonalizado(Persona persona){
        View v = getLayoutInflater().inflate(R.layout.custom_toast,
                findViewById(R.id.custom_toast_container));
        TextView tv = v.findViewById(R.id.txtvw);
        tv.setText(persona.getEdad() + " años");
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(v);
        toast.show();
    }



}