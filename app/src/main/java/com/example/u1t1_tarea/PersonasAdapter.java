package com.example.u1t1_tarea;

import android.view.*;
import android.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;


import java.util.List;


public class PersonasAdapter extends RecyclerView.Adapter<PersonasAdapter.PersonasViewHolder> {

    //Clase interna para el ViewHolder que extiende de RecyclerView.ViewHolder.
    // Sirve para almacenar la vista de cada elemento y sus datos asociados en la memoria para que
// no haya que acceder a ellos constantemente. Es decir, para que no haya que hacer findViewById.
    public class PersonasViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnCreateContextMenuListener, PopupMenu.OnMenuItemClickListener {
        private CardView cvPersona;
        private TextView tvNombre;
        private TextView tvEdad;
        private ImageView ivFoto;

        //Constructor del ViewHolder que recibe la vista de un elemento y se la pasa al constructor de
        // la clase padre. Además, inicializa los atributos de la clase con los elementos de la vista.
        public PersonasViewHolder(View itemView) {
            super(itemView);
            //Inicializamos los atributos de la clase con los elementos de la vista.
            cvPersona = itemView.findViewById(R.id.cvTarjeta);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvEdad = itemView.findViewById(R.id.tvEdad);
            ivFoto = itemView.findViewById(R.id.ivFoto);

            //Conectamos los listeners de los eventos que queremos escuchar en el ViewHolder.
            cvPersona.setOnClickListener(this);
            cvPersona.setOnCreateContextMenuListener(this);
        }
        //Métodos obligados por la interfaz
        @Override
        public void onClick(View v) {
            //Esto nos devuelve la posición del adaptador. Tenemos acceso a la posición y a la lista de
            // personas porque es una clase interna.
            int position = getAdapterPosition();
            //Entonces, comprobamos que el listener está bien definido.
            if (listener != null) {
                //Y llamamos al método onItemSelect del listener pasándole la persona que se ha seleccionado.
                listener.onSelect(personas.get(position));
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
           //Creamos el menú contextual, obteniendo el contexto de la vista
            PopupMenu popup = new PopupMenu(v.getContext(), v);
            //Inflamos el menú contextual con el layout que hemos creado
            popup.inflate(R.menu.menu_contextual);
            //Conectamos el listener del menú contextual
            popup.setOnMenuItemClickListener(this);
            //Mostramos el menú contextual
            popup.show();
        }
        //El onMenuItemClickListener del menú del elemento de la lista llama al método onMenuAction del listener
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int position = getAdapterPosition();
            //Entonces, comprobamos que el listener está bien definido.
            if (listener != null) {
                //Obtenemos la persona de la lista de personas
                Persona persona = personas.get(position);
                //Y llamamos al método onMenuAction del listener pasándole la persona que se ha seleccionado.
                listener.onMenuAction(persona, item);
            }
            //Devolvemos false para no consumir el evento y que se propague al resto de listeners.
            return false;
        }
    }

    //Atributos del adaptador que recibe en el constructor una lista de personas y un listener
    private final OnItemSelectListener listener;
    private List<Persona> personas;

    //Constructor del adaptador que recibe una lista de personas y un listener para el click en
    // un elemento de la lista y para el click en el menú de cada elemento.
    PersonasAdapter(List<Persona> personas, OnItemSelectListener listener) {
        this.personas = personas;
        this.listener = listener;
    }

    //Interfaces. Una para cada evento que queremos escuchar en el adaptador.
    public interface OnItemSelectListener { //Interface para el click
        void onSelect(Persona persona); //Método para el click en el elemento
        void onMenuAction(Persona persona, MenuItem item); //Método para el click en el menú del elemento
    }

    //Métodos obligados por la clase padre
    @Override
    public int getItemCount() {
        return personas.size();
    }

    //Método para crear el ViewHolder
    @Override
    public PersonasViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        PersonasViewHolder pvh = new PersonasViewHolder(v);
        return pvh;
    }

    //Método para enlazar el ViewHolder con los datos
    @Override
    public void onBindViewHolder(PersonasViewHolder holder, int position) {
        holder.tvNombre.setText(personas.get(position).getNombre());
        holder.tvEdad.setText(personas.get(position).getEdad());
        holder.ivFoto.setImageResource(personas.get(position).getFotoID());

    }

    //Método para notificar al adaptador que se ha añadido un elemento a la lista
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

