package com.nativesquad.guiadeviajes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.nativesquad.guiadeviajes.R;
import com.nativesquad.guiadeviajes.adapters.DestinosAdapter;
import com.nativesquad.guiadeviajes.models.Destino;
import com.nativesquad.guiadeviajes.models.DestinosData;

import java.util.List;

/**
 * DestinosFragment: Módulo de galería de imágenes con desplazamiento (scroll) (RF-03).
 * Al pulsar sobre cualquier destino, el panel dinámico muestra la descripción histórica y geográfica.
 */
public class DestinosFragment extends Fragment implements DestinosAdapter.OnDestinoClickListener {

    private RecyclerView recyclerDestinos;
    private DestinosAdapter adapter;
    private List<Destino> destinosList;

    // Panel de Detalle Dinámico
    private CardView cardDetalle;
    private ImageView imgDetallePreview;
    private TextView tvDetalleTitulo;
    private TextView tvDetalleClima;
    private TextView tvDetalleHistoria;
    private TextView tvDetalleGeografia;
    private TextView tvDetalleActividades;
    private MaterialButton btnCerrarDetalle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_destinos, container, false);
        initViews(root);
        setupRecyclerView();
        setupEvents();

        // Cargar por defecto el primer destino en el panel de detalle
        if (!destinosList.isEmpty()) {
            mostrarDetalleDestino(destinosList.get(0));
        }

        return root;
    }

    /**
     * Vinculación de variables con identificadores de vista XML (findViewById)
     */
    private void initViews(View root) {
        recyclerDestinos = root.findViewById(R.id.recycler_destinos);
        cardDetalle = root.findViewById(R.id.card_destino_detalle);
        imgDetallePreview = root.findViewById(R.id.img_detalle_preview);
        tvDetalleTitulo = root.findViewById(R.id.tv_detalle_titulo);
        tvDetalleClima = root.findViewById(R.id.tv_detalle_clima);
        tvDetalleHistoria = root.findViewById(R.id.tv_detalle_historia);
        tvDetalleGeografia = root.findViewById(R.id.tv_detalle_geografia);
        tvDetalleActividades = root.findViewById(R.id.tv_detalle_actividades);
        btnCerrarDetalle = root.findViewById(R.id.btn_detalle_cerrar);
    }

    /**
     * Inicialización del RecyclerView con su LayoutManager y Adaptador
     */
    private void setupRecyclerView() {
        destinosList = DestinosData.getDestinos();
        adapter = new DestinosAdapter(destinosList, this);
        recyclerDestinos.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerDestinos.setAdapter(adapter);
    }

    /**
     * Configuración de eventos táctiles
     */
    private void setupEvents() {
        btnCerrarDetalle.setOnClickListener(v -> {
            if (cardDetalle.getVisibility() == View.VISIBLE) {
                cardDetalle.setVisibility(View.GONE);
            } else {
                cardDetalle.setVisibility(View.VISIBLE);
            }
        });
    }

    /**
     * Manejador del evento de selección al tocar una tarjeta de la galería (RF-03)
     */
    @Override
    public void onDestinoClick(Destino destino) {
        mostrarDetalleDestino(destino);
        Toast.makeText(getContext(), "Cargando reseña de " + destino.getTitulo(), Toast.LENGTH_SHORT).show();
    }

    private void mostrarDetalleDestino(Destino destino) {
        if (cardDetalle == null) return;

        cardDetalle.setVisibility(View.VISIBLE);
        imgDetallePreview.setImageResource(destino.getImagenResId());
        tvDetalleTitulo.setText(destino.getTitulo());
        tvDetalleClima.setText(destino.getClima());
        tvDetalleHistoria.setText(destino.getDescripcionHistorica());
        tvDetalleGeografia.setText(destino.getDescripcionGeografica());
        tvDetalleActividades.setText(destino.getActividadesRecomendadas());
    }
}
