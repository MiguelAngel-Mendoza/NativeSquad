package com.nativesquad.guiadeviajes;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nativesquad.guiadeviajes.fragments.AccionesFragment;
import com.nativesquad.guiadeviajes.fragments.BitacoraFragment;
import com.nativesquad.guiadeviajes.fragments.DestinosFragment;
import com.nativesquad.guiadeviajes.fragments.ExperienciasFragment;
import com.nativesquad.guiadeviajes.fragments.MenuFragment;
import com.nativesquad.guiadeviajes.fragments.PortalWebFragment;

/**
 * MainActivity: Actividad principal contenedora de la aplicación NativeSquad HPM.
 * Administra la arquitectura de dos fragmentos sincronizados horizontalmente:
 * - Izquierda: MenuFragment (panel de opciones).
 * - Derecha: FrameLayout dinámico donde se instancian los submódulos vía FragmentTransaction.
 * - Barra superior: Botón de torta / hamburguesa para ocultar y mostrar el menú lateral dinámicamente.
 */
public class MainActivity extends AppCompatActivity implements MenuFragment.OnMenuOptionSelectedListener {

    private static final String KEY_CURRENT_OPTION = "key_current_selected_option";
    private static final String KEY_MENU_VISIBLE = "key_menu_is_visible";

    private int currentOptionId = MenuFragment.OPTION_BITACORA;
    private boolean isMenuVisible = true;

    // Vistas de control del menú lateral
    private ImageButton btnToggleMenu;
    private View fragmentMenuContainer;
    private View menuDivider;
    private TextView tvMenuStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupToggleMenu();

        if (savedInstanceState != null) {
            currentOptionId = savedInstanceState.getInt(KEY_CURRENT_OPTION, MenuFragment.OPTION_BITACORA);
            isMenuVisible = savedInstanceState.getBoolean(KEY_MENU_VISIBLE, true);
            actualizarVisibilidadMenu(false);
        } else {
            // Cargar fragmento inicial por defecto: BitacoraFragment
            cargarFragmento(new BitacoraFragment());
        }
    }

    /**
     * Vinculación de variables con identificadores XML
     */
    private void initViews() {
        btnToggleMenu = findViewById(R.id.btn_toggle_menu);
        fragmentMenuContainer = findViewById(R.id.fragment_menu_container);
        menuDivider = findViewById(R.id.menu_divider);
        tvMenuStatus = findViewById(R.id.tv_menu_toggle_status);
    }

    /**
     * Configuración del evento del botón de torta / hamburguesa
     */
    private void setupToggleMenu() {
        btnToggleMenu.setOnClickListener(v -> {
            isMenuVisible = !isMenuVisible;
            actualizarVisibilidadMenu(true);
        });
    }

    /**
     * Oculta o muestra el panel lateral izquierdo
     *
     * @param mostrarMensaje Booleano para emitir notificación Toast
     */
    private void actualizarVisibilidadMenu(boolean mostrarMensaje) {
        if (isMenuVisible) {
            fragmentMenuContainer.setVisibility(View.VISIBLE);
            menuDivider.setVisibility(View.VISIBLE);
            tvMenuStatus.setText("Menú Visible");
            if (mostrarMensaje) {
                Toast.makeText(this, "Menú lateral visible 📱", Toast.LENGTH_SHORT).show();
            }
        } else {
            fragmentMenuContainer.setVisibility(View.GONE);
            menuDivider.setVisibility(View.GONE);
            tvMenuStatus.setText("Pantalla Completa");
            if (mostrarMensaje) {
                Toast.makeText(this, "Menú lateral ocultado (Modo expandido) 🔍", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Manejador del callback de navegación emitido por MenuFragment
     *
     * @param optionId Constante que define el submódulo seleccionado
     */
    @Override
    public void onOptionSelected(int optionId) {
        if (currentOptionId == optionId && getSupportFragmentManager().findFragmentById(R.id.fragment_contenedor) != null) {
            return; // Ya está cargado el fragmento solicitado
        }

        currentOptionId = optionId;
        Fragment fragmentDestino;

        switch (optionId) {
            case MenuFragment.OPTION_BITACORA:
                fragmentDestino = new BitacoraFragment();
                break;
            case MenuFragment.OPTION_DESTINOS:
                fragmentDestino = new DestinosFragment();
                break;
            case MenuFragment.OPTION_EXPERIENCIAS:
                fragmentDestino = new ExperienciasFragment();
                break;
            case MenuFragment.OPTION_PORTAL_WEB:
                fragmentDestino = new PortalWebFragment();
                break;
            case MenuFragment.OPTION_ACCIONES:
                fragmentDestino = new AccionesFragment();
                break;
            default:
                fragmentDestino = new BitacoraFragment();
                break;
        }

        cargarFragmento(fragmentDestino);
    }

    /**
     * Realiza la transacción de reemplazo de fragmento en el panel derecho (FrameLayout)
     * asegurando animaciones suaves.
     *
     * @param fragment Nueva instancia del fragmento a desplegar
     */
    private void cargarFragmento(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out
        );

        transaction.replace(R.id.fragment_contenedor, fragment);
        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_OPTION, currentOptionId);
        outState.putBoolean(KEY_MENU_VISIBLE, isMenuVisible);
    }
}
