package com.nativesquad.guiadeviajes.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nativesquad.guiadeviajes.R;
import com.nativesquad.guiadeviajes.models.Destino;

import java.util.List;

/**
 * Adaptador para la lista/galería de destinos turísticos con soporte para eventos de selección.
 */
public class DestinosAdapter extends RecyclerView.Adapter<DestinosAdapter.DestinoViewHolder> {

    public interface OnDestinoClickListener {
        void onDestinoClick(Destino destino);
    }

    private final List<Destino> destinos;
    private final OnDestinoClickListener listener;

    public DestinosAdapter(List<Destino> destinos, OnDestinoClickListener listener) {
        this.destinos = destinos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DestinoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_destino_card, parent, false);
        return new DestinoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinoViewHolder holder, int position) {
        Destino destino = destinos.get(position);
        holder.bind(destino, listener);
    }

    @Override
    public int getItemCount() {
        return destinos != null ? destinos.size() : 0;
    }

    public static class DestinoViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgDestino;
        private final TextView tvTitulo;
        private final TextView tvSubtitulo;
        private final TextView tvRegion;
        private final ImageView btnFav;

        public DestinoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDestino = itemView.findViewById(R.id.img_destino_item);
            tvTitulo = itemView.findViewById(R.id.tv_destino_item_titulo);
            tvSubtitulo = itemView.findViewById(R.id.tv_destino_item_subtitulo);
            tvRegion = itemView.findViewById(R.id.tv_destino_item_region);
            btnFav = itemView.findViewById(R.id.btn_destino_fav_indicator);
        }

        public void bind(final Destino destino, final OnDestinoClickListener listener) {
            imgDestino.setImageResource(destino.getImagenResId());
            tvTitulo.setText(destino.getTitulo());
            tvSubtitulo.setText(destino.getSubtitulo());
            tvRegion.setText(destino.getRegion());

            btnFav.setImageResource(destino.isEsFavorito() ? R.drawable.ic_favorite : R.drawable.ic_favorite_border);

            btnFav.setOnClickListener(v -> {
                destino.setEsFavorito(!destino.isEsFavorito());
                btnFav.setImageResource(destino.isEsFavorito() ? R.drawable.ic_favorite : R.drawable.ic_favorite_border);
                String msg = destino.isEsFavorito()
                        ? destino.getTitulo() + " añadido a Favoritos ❤️"
                        : destino.getTitulo() + " removido de Favoritos";
                Toast.makeText(v.getContext(), msg, Toast.LENGTH_SHORT).show();
            });

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onDestinoClick(destino);
                }
            });
        }
    }
}
