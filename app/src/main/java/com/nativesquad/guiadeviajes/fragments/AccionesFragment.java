package com.nativesquad.guiadeviajes.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.nativesquad.guiadeviajes.R;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * AccionesFragment: Módulo interactivo de botones avanzados y gestión de eventos (RF-06).
 * Demuestra respuestas táctiles inmediatas, contadores, alertas y cambios de estado.
 */
public class AccionesFragment extends Fragment {

    // Vistas - Botones principales
    private MaterialButton btnItinerario;
    private MaterialButton btnFavorito;
    private TextView tvItinerarioStatus;

    // Vistas - Planificador de Presupuesto
    private Button btnViajerosMinus;
    private Button btnViajerosPlus;
    private TextView tvViajerosCount;

    private Button btnDiasMinus;
    private Button btnDiasPlus;
    private TextView tvDiasCount;
    private TextView tvPresupuestoTotal;

    // Vistas - Modalidad RadioGroup
    private RadioGroup rgModalidad;
    private RadioButton rbAventura;
    private RadioButton rbRelax;
    private RadioButton rbCultural;

    // Vistas - Acciones de apoyo
    private MaterialButton btnSos;
    private MaterialButton btnReset;

    // Variables de estado
    private int contadorItinerario = 0;
    private boolean esFavoritoActivo = false;
    private int numViajeros = 1;
    private int numDias = 3;
    private static final int COSTO_BASE_DIARIO = 250000; // COP por persona/día

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_acciones, container, false);
        initViews(root);
        setupEvents();
        calcularPresupuesto();
        return root;
    }

    /**
     * Vinculación de variables con identificadores de vista XML (findViewById)
     */
    private void initViews(View root) {
        btnItinerario = root.findViewById(R.id.btn_accion_itinerario);
        btnFavorito = root.findViewById(R.id.btn_accion_favorito);
        tvItinerarioStatus = root.findViewById(R.id.tv_itinerario_badge_status);

        btnViajerosMinus = root.findViewById(R.id.btn_viajeros_minus);
        btnViajerosPlus = root.findViewById(R.id.btn_viajeros_plus);
        tvViajerosCount = root.findViewById(R.id.tv_viajeros_count);

        btnDiasMinus = root.findViewById(R.id.btn_dias_minus);
        btnDiasPlus = root.findViewById(R.id.btn_dias_plus);
        tvDiasCount = root.findViewById(R.id.tv_dias_count);
        tvPresupuestoTotal = root.findViewById(R.id.tv_presupuesto_total);

        rgModalidad = root.findViewById(R.id.rg_modalidad_viaje);
        rbAventura = root.findViewById(R.id.rb_aventura);
        rbRelax = root.findViewById(R.id.rb_relax);
        rbCultural = root.findViewById(R.id.rb_cultural);

        btnSos = root.findViewById(R.id.btn_accion_sos);
        btnReset = root.findViewById(R.id.btn_accion_reset);
    }

    /**
     * Declaración y asignación de listeners de eventos táctiles
     */
    private void setupEvents() {
        // 1. Evento: Añadir al itinerario
        btnItinerario.setOnClickListener(v -> {
            contadorItinerario++;
            actualizarEstadoItinerario();
            Toast.makeText(getContext(),
                    "✅ Destino agregado al itinerario (Total: " + contadorItinerario + ")",
                    Toast.LENGTH_SHORT).show();
        });

        // 2. Evento: Marcar como favorito
        btnFavorito.setOnClickListener(v -> {
            esFavoritoActivo = !esFavoritoActivo;
            if (esFavoritoActivo) {
                btnFavorito.setIconResource(R.drawable.ic_favorite);
                btnFavorito.setText("En Favoritos");
                Toast.makeText(getContext(), "❤️ Guardado en tu lista de viajes favoritos", Toast.LENGTH_SHORT).show();
            } else {
                btnFavorito.setIconResource(R.drawable.ic_favorite_border);
                btnFavorito.setText("Marcar Favorito");
                Toast.makeText(getContext(), "Eliminado de favoritos", Toast.LENGTH_SHORT).show();
            }
            actualizarEstadoItinerario();
        });

        // 3. Eventos: Contadores de Viajeros (+ / -)
        btnViajerosPlus.setOnClickListener(v -> {
            if (numViajeros < 15) {
                numViajeros++;
                tvViajerosCount.setText(String.valueOf(numViajeros));
                calcularPresupuesto();
            }
        });

        btnViajerosMinus.setOnClickListener(v -> {
            if (numViajeros > 1) {
                numViajeros--;
                tvViajerosCount.setText(String.valueOf(numViajeros));
                calcularPresupuesto();
            }
        });

        // 4. Eventos: Contadores de Días (+ / -)
        btnDiasPlus.setOnClickListener(v -> {
            if (numDias < 30) {
                numDias++;
                tvDiasCount.setText(String.valueOf(numDias));
                calcularPresupuesto();
            }
        });

        btnDiasMinus.setOnClickListener(v -> {
            if (numDias > 1) {
                numDias--;
                tvDiasCount.setText(String.valueOf(numDias));
                calcularPresupuesto();
            }
        });

        // 5. Evento: Cambio de RadioGroup
        rgModalidad.setOnCheckedChangeListener((group, checkedId) -> {
            String modalidadSeleccionada = "Aventura";
            if (checkedId == R.id.rb_relax) {
                modalidadSeleccionada = "Playa & Relajación";
            } else if (checkedId == R.id.rb_cultural) {
                modalidadSeleccionada = "Historia & Cultura";
            }
            Toast.makeText(getContext(), "Modalidad de viaje: " + modalidadSeleccionada, Toast.LENGTH_SHORT).show();
        });

        // 6. Evento: Diálogo de Emergencia SOS
        btnSos.setOnClickListener(v -> mostrarDialogoEmergencia());

        // 7. Evento: Resetear Estado
        btnReset.setOnClickListener(v -> resetearValores());
    }

    private void calcularPresupuesto() {
        long total = (long) numViajeros * numDias * COSTO_BASE_DIARIO;
        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        tvPresupuestoTotal.setText("Presupuesto Estimado: " + formato.format(total) + " COP");
    }

    private void actualizarEstadoItinerario() {
        String favTexto = esFavoritoActivo ? "⭐ Marcado como Favorito" : "Sin favorito destacado";
        tvItinerarioStatus.setText("Destinos en itinerario: " + contadorItinerario + " | " + favTexto);
    }

    private void mostrarDialogoEmergencia() {
        if (getContext() == null) return;

        new AlertDialog.Builder(getContext())
                .setTitle("🚨 Asistencia y Emergencias Turísticas")
                .setMessage("Contactos oficiales para asistencia en ruta:\n\n" +
                        "• Policía de Turismo: Línea 123 / (601) 518 9000\n" +
                        "• Defensa Civil Colombiana: 144\n" +
                        "• Cruz Roja Nacional: 132\n" +
                        "• Guías NativeSquad HPM: soporte@nativesquad.edu.co")
                .setPositiveButton("Entendido", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void resetearValores() {
        contadorItinerario = 0;
        esFavoritoActivo = false;
        numViajeros = 1;
        numDias = 3;

        tvViajerosCount.setText("1");
        tvDiasCount.setText("3");
        btnFavorito.setIconResource(R.drawable.ic_favorite_border);
        btnFavorito.setText("Marcar Favorito");
        rbAventura.setChecked(true);

        calcularPresupuesto();
        actualizarEstadoItinerario();

        Toast.makeText(getContext(), "🔄 Todos los valores han sido restablecidos", Toast.LENGTH_SHORT).show();
    }
}
