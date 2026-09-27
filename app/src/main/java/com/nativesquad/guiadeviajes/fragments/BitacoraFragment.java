package com.nativesquad.guiadeviajes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.nativesquad.guiadeviajes.R;

/**
 * BitacoraFragment: Módulo correspondiente al perfil institucional y métricas de viaje (RF-02).
 * Integra ScrollView para optimizar la lectura de textos extensos.
 */
public class BitacoraFragment extends Fragment {

    private ScrollView scrollBitacora;
    private TextView tvTitle;
    private TextView tvSub;
    private MaterialButton btnDownloadAction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bitacora, container, false);
        initViews(root);
        setupEvents();
        return root;
    }

    /**
     * Vinculación de variables con identificadores de vista XML
     */
    private void initViews(View root) {
        scrollBitacora = root.findViewById(R.id.scroll_bitacora);
        tvTitle = root.findViewById(R.id.tv_bitacora_title);
        tvSub = root.findViewById(R.id.tv_bitacora_sub);
        btnDownloadAction = root.findViewById(R.id.btn_bitacora_action);
    }

    /**
     * Declaración y configuración de eventos táctiles
     */
    private void setupEvents() {
        if (btnDownloadAction != null) {
            btnDownloadAction.setOnClickListener(v -> {
                Toast.makeText(getContext(),
                        "📄 Bitácora NativeSquad HPM generada y lista para consulta offline",
                        Toast.LENGTH_LONG).show();
            });
        }
    }
}
