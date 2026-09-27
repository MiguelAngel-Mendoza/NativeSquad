package com.nativesquad.guiadeviajes.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.nativesquad.guiadeviajes.R;

/**
 * ExperienciasFragment: Módulo de reproducción multimedia con VideoView y controles (RF-04).
 * Carga videos locales integrados en res/raw garantizando reproducción offline inmediata.
 */
public class ExperienciasFragment extends Fragment {

    private VideoView videoView;
    private MediaController mediaController;
    private ProgressBar progressLoading;
    private TextView tvStatusOverlay;
    private TextView tvActiveTitle;
    private TextView tvActiveDesc;

    // Botones de control
    private MaterialButton btnPlay;
    private MaterialButton btnPause;
    private MaterialButton btnReplay;

    // Selector de clips
    private MaterialButton btnClipColombia;
    private MaterialButton btnClipCafetero;
    private MaterialButton btnClipCaribe;

    private int currentPosition = 0;
    private int currentRawId = R.raw.travel_colombia;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_experiencias, container, false);
        initViews(root);
        setupVideoPlayer();
        setupClickListeners();

        // Carga inicial del video integrado
        loadLocalVideo(R.raw.travel_colombia,
                "Experiencia 01: Colombia, País de la Belleza",
                "Un recorrido audiovisual que registra la biodiversidad de la Sierra Nevada, las murallas históricas de Cartagena y la cultura de los cafetales.");

        return root;
    }

    /**
     * Vinculación de variables con identificadores XML (findViewById)
     */
    private void initViews(View root) {
        videoView = root.findViewById(R.id.video_view);
        progressLoading = root.findViewById(R.id.progress_video_loading);
        tvStatusOverlay = root.findViewById(R.id.tv_video_status_overlay);
        tvActiveTitle = root.findViewById(R.id.tv_video_active_title);
        tvActiveDesc = root.findViewById(R.id.tv_video_active_desc);

        btnPlay = root.findViewById(R.id.btn_video_play);
        btnPause = root.findViewById(R.id.btn_video_pause);
        btnReplay = root.findViewById(R.id.btn_video_replay);

        btnClipColombia = root.findViewById(R.id.btn_clip_colombia);
        btnClipCafetero = root.findViewById(R.id.btn_clip_cafetero);
        btnClipCaribe = root.findViewById(R.id.btn_clip_caribe);
    }

    /**
     * Configuración del reproductor de video nativo con MediaController y listeners
     */
    private void setupVideoPlayer() {
        if (getContext() != null) {
            mediaController = new MediaController(getContext());
            mediaController.setAnchorView(videoView);
            videoView.setMediaController(mediaController);
        }

        videoView.setOnPreparedListener(mp -> {
            progressLoading.setVisibility(View.GONE);
            tvStatusOverlay.setText("Reproduciendo");
            mp.setLooping(true); // Reproducción en bucle continua
            if (currentPosition > 0) {
                videoView.seekTo(currentPosition);
            }
            videoView.start();
        });

        videoView.setOnCompletionListener(mp -> {
            tvStatusOverlay.setText("Completado");
        });

        videoView.setOnErrorListener((mp, what, extra) -> {
            progressLoading.setVisibility(View.GONE);
            tvStatusOverlay.setText("Error en reproducción");
            return true;
        });
    }

    /**
     * Declaración y configuración de eventos táctiles
     */
    private void setupClickListeners() {
        btnPlay.setOnClickListener(v -> {
            if (!videoView.isPlaying()) {
                videoView.start();
                tvStatusOverlay.setText("Reproduciendo");
                Toast.makeText(getContext(), "▶️ Reproduciendo video", Toast.LENGTH_SHORT).show();
            }
        });

        btnPause.setOnClickListener(v -> {
            if (videoView.isPlaying()) {
                videoView.pause();
                tvStatusOverlay.setText("En Pausa");
                Toast.makeText(getContext(), "⏸️ Video pausado", Toast.LENGTH_SHORT).show();
            }
        });

        btnReplay.setOnClickListener(v -> {
            videoView.seekTo(0);
            videoView.start();
            tvStatusOverlay.setText("Reproduciendo");
            Toast.makeText(getContext(), "🔄 Reiniciando desde el inicio", Toast.LENGTH_SHORT).show();
        });

        btnClipColombia.setOnClickListener(v -> loadLocalVideo(
                R.raw.travel_colombia,
                "Experiencia 01: Colombia, País de la Belleza",
                "Un recorrido audiovisual por la biodiversidad andina, selvas y patrimonio nacional."
        ));

        btnClipCafetero.setOnClickListener(v -> loadLocalVideo(
                R.raw.travel_cafetero,
                "Experiencia 02: Ruta del Café & Tradición",
                "La magia del Paisaje Cultural Cafetero, sus fincas patrimoniales y el Valle del Cocora."
        ));

        btnClipCaribe.setOnClickListener(v -> loadLocalVideo(
                R.raw.travel_caribe,
                "Experiencia 03: Caribe & Playas Legendarias",
                "Las aguas cristalinas de San Andrés Islas y las bahías protegidas del Parque Tayrona."
        ));
    }

    /**
     * Carga y reproduce un video local desde la carpeta res/raw
     */
    private void loadLocalVideo(int rawResId, String title, String desc) {
        if (getContext() == null) return;

        currentRawId = rawResId;
        tvActiveTitle.setText(title);
        tvActiveDesc.setText(desc);
        tvStatusOverlay.setText("Cargando video...");
        progressLoading.setVisibility(View.VISIBLE);

        String path = "android.resource://" + getContext().getPackageName() + "/" + rawResId;
        videoView.stopPlayback();
        videoView.setVideoURI(Uri.parse(path));
    }

    @Override
    public void onPause() {
        super.onPause();
        if (videoView != null && videoView.isPlaying()) {
            currentPosition = videoView.getCurrentPosition();
            videoView.pause();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (videoView != null) {
            videoView.stopPlayback();
        }
    }
}
