package com.pdm.video.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pdm.video.R;
import com.pdm.video.entities.Categoria;

import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.ViewHolder> {
    private Context contexto;
    private List<Categoria> data;
    public click_listener listener;

    public interface click_listener{
        void onItemClick(Categoria categoria);
    }

    public CategoriaAdapter(Context contexto, List<Categoria> data, click_listener click) {
        this.contexto = contexto;
        this.data = data;
        this.listener = click;
    }

    @NonNull
    @Override
    public CategoriaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriaAdapter.ViewHolder holder, int position) {
        Categoria c = data.get(position);
        holder.txtNombreCategoria.setText(c.nombre);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNombreCategoria;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNombreCategoria = itemView.findViewById(R.id.txtNombreCategoria);
            itemView.setOnClickListener(v -> {
                if(listener != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                    listener.onItemClick(data.get(getAdapterPosition()));
                }
            });
        }
    }
}
