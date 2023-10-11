package com.example.u1t1_tarea;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import org.jetbrains.annotations.NotNull;


import java.util.List;


public class PersonasAdapter extends RecyclerView.Adapter<PersonasAdapter.PersonasViewHolder> {

    public static class PersonasViewHolder extends RecyclerView.ViewHolder {
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

        public void bind (Persona persona, final OnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(persona);
                }
            });
        }
    }

    private final OnItemClickListener listener;
    private List<Persona> personas;

    PersonasAdapter(List<Persona> personas, OnItemClickListener listener) {
        this.personas = personas;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

    @Override
    public PersonasViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        PersonasViewHolder pvh = new PersonasViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonasViewHolder holder, int position) {
        holder.tvNombre.setText(personas.get(position).getNombre());
        holder.tvEdad.setText(personas.get(position).getEdad());
        holder.ivFoto.setImageResource(personas.get(position).getFotoID());
        holder.bind(personas.get(position), listener);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

    }

}

