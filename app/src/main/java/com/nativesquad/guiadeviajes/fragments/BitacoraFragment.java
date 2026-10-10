package com.nativesquad.guiadeviajes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.nativesquad.guiadeviajes.R;

/**
 * BitacoraFragment / PerfilFragment:
 * Módulo de Perfil del Usuario / Desarrollador (Rúbrica Entrega 3 - RF-02).
 * Requerimiento: Muestra el perfil de una persona (Foto, estudios, experiencia, etc.
 * Para los campos tipo texto utilicen barra de desplazamiento para optimizar la pantalla).
 * Además, permite alternar dinámicamente entre los 4 integrantes del equipo NativeSquad HPM.
 */
public class BitacoraFragment extends Fragment {

    // Vistas del Perfil
    private ImageView imgPerfilFoto;
    private TextView tvPerfilNombre;
    private TextView tvPerfilRol;
    private TextView tvPerfilInstitucion;

    // Chips de selección de integrantes
    private MaterialButton btnChipSandra;
    private MaterialButton btnChipMiguel;

    // Campos de texto extensos con ScrollView interna
    private ScrollView scrollEstudios;
    private ScrollView scrollExperiencia;
    private ScrollView scrollBiografia;

    private TextView tvPerfilEstudios;
    private TextView tvPerfilExperiencia;
    private TextView tvPerfilBiografia;

    private MaterialButton btnDownloadAction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bitacora, container, false);
        initViews(root);
        setupEvents();

        // Cargar por defecto el perfil inicial (Sandra Millan)
        cargarPerfil(0);

        return root;
    }

    /**
     * Vinculación de variables con identificadores de vista XML (findViewById)
     */
    private void initViews(View root) {
        imgPerfilFoto = root.findViewById(R.id.img_perfil_foto);
        tvPerfilNombre = root.findViewById(R.id.tv_perfil_nombre);
        tvPerfilRol = root.findViewById(R.id.tv_perfil_rol);
        tvPerfilInstitucion = root.findViewById(R.id.tv_perfil_institucion);

        btnChipSandra = root.findViewById(R.id.btn_chip_sandra);
        btnChipMiguel = root.findViewById(R.id.btn_chip_miguel);

        scrollEstudios = root.findViewById(R.id.scroll_estudios);
        scrollExperiencia = root.findViewById(R.id.scroll_experiencia);
        scrollBiografia = root.findViewById(R.id.scroll_biografia);

        tvPerfilEstudios = root.findViewById(R.id.tv_perfil_estudios);
        tvPerfilExperiencia = root.findViewById(R.id.tv_perfil_experiencia);
        tvPerfilBiografia = root.findViewById(R.id.tv_perfil_biografia);

        btnDownloadAction = root.findViewById(R.id.btn_bitacora_action);
    }

    /**
     * Declaración y configuración de eventos táctiles
     */
    private void setupEvents() {
        btnChipSandra.setOnClickListener(v -> cargarPerfil(0));
        btnChipMiguel.setOnClickListener(v -> cargarPerfil(1));

        if (btnDownloadAction != null) {
            btnDownloadAction.setOnClickListener(v -> {
                String nombreActivo = tvPerfilNombre.getText().toString();
                Toast.makeText(getContext(),
                        "📄 Ficha de Perfil Profesional de " + nombreActivo + " descargada con éxito",
                        Toast.LENGTH_LONG).show();
            });
        }
    }

    /**
     * Carga dinámicamente los datos de perfil de cada integrante del equipo NativeSquad HPM
     * demostrando reactividad de interfaz y optimización con scroll.
     *
     * @param index Índice del integrante (0: Sandra, 1: Miguel)
     */
    private void cargarPerfil(int index) {
        resetChipStyles();

        switch (index) {
            case 0:
                highlightChip(btnChipSandra);
                tvPerfilNombre.setText("Sandra Milena Millan Alvarez");
                tvPerfilRol.setText("Ingeniera de Sistemas • Desarrolladora Android & UI/UX");
                tvPerfilInstitucion.setText("Politécnico Grancolombiano • Bogotá, Colombia");
                tvPerfilEstudios.setText("• Ingeniería de Sistemas - Institución Universitaria Politécnico Grancolombiano (En curso, 2023 - 2026).\n\n" +
                        "• Diplomado en Arquitectura de Software y Desarrollo de Soluciones Móviles (Politécnico Grancolombiano, 2026).\n\n" +
                        "• Certificación Oficial: Google Developers - Android Basics in Java & Kotlin (2025).\n\n" +
                        "• Especialización en Diseño de Interfaces Móviles (UI/UX) y Google Material Design 3 (2025).\n\n" +
                        "• Certificado en Fundamentos de Bases de Datos Relacionales y SQLite/Room para Android (2024).");
                tvPerfilExperiencia.setText("• Desarrolladora Android Líder - NativeSquad HPM (2026):\n" +
                        "Diseño y estructuración de la arquitectura modular de dos fragmentos sincronizados, desarrollo de layouts responsivos con LinearLayout y CardView, e integración de controladores multimedia (VideoView y MediaController).\n\n" +
                        "• Ingeniera de Software Junior - Soluciones Móviles (2025 - Presente):\n" +
                        "Maquetación de interfaces, aplicación de buenas prácticas de ciclo de vida en Android y optimización de rendimiento.\n\n" +
                        "• Desarrolladora Full-Stack en Proyectos Académicos Poli (2024 - 2025):\n" +
                        "Construcción de aplicaciones distribuidas, consumo de APIs RESTful y control de versiones con Git/GitHub.");
                tvPerfilBiografia.setText("Profesional proactiva con sólida formación en ingeniería de sistemas, orientada al desarrollo de software móvil nativo y ecoturismo sostenible. Apasionada por diseñar experiencias de usuario intuitivas y código mantenible.");
                break;

            case 1:
                highlightChip(btnChipMiguel);
                tvPerfilNombre.setText("Miguel Angel Mendoza Niño");
                tvPerfilRol.setText("Ingeniero de Sistemas • Desarrollador Android & Datos");
                tvPerfilInstitucion.setText("Politécnico Grancolombiano • Bogotá, Colombia");
                tvPerfilEstudios.setText("• Ingeniería de Sistemas - Institución Universitaria Politécnico Grancolombiano (En curso, 2023 - 2026).\n\n" +
                        "• Diplomado en Arquitectura y Sistemas Distribuidos (Politécnico Grancolombiano, 2026).\n\n" +
                        "• Certificación en Programación Orientada a Objetos Avanzada en Java (Oracle / 2025).\n\n" +
                        "• Especialización en Integración Continua, Control de Versiones Git y Automatización Gradle (2025).\n\n" +
                        "• Formación en Seguridad en Redes y Protocolos de Comunicación Móvil (2024).");
                tvPerfilExperiencia.setText("• Desarrollador Móvil y Gestión de Datos - NativeSquad HPM (2026):\n" +
                        "Implementación del módulo de navegación web embebida (WebView), integración de adaptadores dinámicos con RecyclerView y arquitectura de eventos táctiles.\n\n" +
                        "• Analista de Sistemas y Soporte Técnico (2024 - 2025):\n" +
                        "Monitoreo de plataformas de datos, resolución de incidencias en producción y despliegue de software.\n\n" +
                        "• Colaborador Open Source y Repositorios Académicos Poli (2024 - 2026):\n" +
                        "Mantenimiento del repositorio oficial de NativeSquad y desarrollo de pruebas unitarias.");
                tvPerfilBiografia.setText("Ingeniero en formación con pasión por los sistemas distribuidos, desarrollo móvil nativo en Java y optimización de datos. Enfocado en la entrega de soluciones de software de alto impacto y calidad.");
                break;
        }

        // Reiniciar el scroll de los textos al inicio al cambiar de perfil
        if (scrollEstudios != null) scrollEstudios.scrollTo(0, 0);
        if (scrollExperiencia != null) scrollExperiencia.scrollTo(0, 0);
        if (scrollBiografia != null) scrollBiografia.scrollTo(0, 0);
    }

    private void resetChipStyles() {
        if (getContext() == null) return;
        setChipStyle(btnChipSandra, false);
        setChipStyle(btnChipMiguel, false);
    }

    private void highlightChip(MaterialButton btn) {
        setChipStyle(btn, true);
    }

    private void setChipStyle(MaterialButton btn, boolean isSelected) {
        if (getContext() == null || btn == null) return;
        if (isSelected) {
            btn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primary_teal));
            btn.setTextColor(ContextCompat.getColor(getContext(), R.color.text_primary));
        } else {
            btn.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.bg_card_elevated));
            btn.setTextColor(ContextCompat.getColor(getContext(), R.color.text_secondary));
        }
    }
}
