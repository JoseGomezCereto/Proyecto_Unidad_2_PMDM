package com.example.u1t1_tarea;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import java.util.List;


public class PersonasAdapter extends RecyclerView.Adapter<PersonasAdapter.PersonasViewHolder>{

    public class PersonasViewHolder extends RecyclerView.ViewHolder {

        private CardView cvPersona;
        private TextView tvNombre;
        private TextView tvEdad;
        private ImageView ivFoto;

        public PersonasViewHolder(View itemView) {
            super(itemView);
            cvPersona = itemView.findViewById(R.id.cvTarjeta);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvEdad = itemView.findViewById(R.id.tvEdad);
            ivFoto = itemView.findViewById(R.id.ivFoto);
        }
    }
        private List<Persona> personas;
        PersonasAdapter(List<Persona> personas){
            this.personas = personas;
        }

        @Override
        public int getItemCount(){
            return personas.size();
        }

        @Override
        public PersonasViewHolder onCreateViewHolder(ViewGroup parent, int i){
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
            PersonasViewHolder pvh = new PersonasViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(PersonasViewHolder personasViewHolder, int i){
            personasViewHolder.tvNombre.setText(personas.get(i).getNombre());
            personasViewHolder.tvEdad.setText(personas.get(i).getEdad());
            personasViewHolder.ivFoto.setImageResource(personas.get(i).getFotoID());
        }
        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView){
            super.onAttachedToRecyclerView(recyclerView);

        }

    }

