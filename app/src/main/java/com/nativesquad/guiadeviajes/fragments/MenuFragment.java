package com.nativesquad.guiadeviajes.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.nativesquad.guiadeviajes.R;

/**
 * MenuFragment: Controla el panel lateral izquierdo permanente de navegación.
 * Gestiona los eventos táctiles de los 5 submódulos y notifica a MainActivity.
 */
public class MenuFragment extends Fragment {

    public static final int OPTION_BITACORA = 0;
    public static final int OPTION_DESTINOS = 1;
    public static final int OPTION_EXPERIENCIAS = 2;
    public static final int OPTION_PORTAL_WEB = 3;
    public static final int OPTION_ACCIONES = 4;

    public interface OnMenuOptionSelectedListener {
        void onOptionSelected(int optionId);
    }

    private OnMenuOptionSelectedListener callback;
    private int currentSelectedOption = OPTION_BITACORA;

    // Vistas de los botones del menú
    private LinearLayout btnBitacora;
    private LinearLayout btnDestinos;
    private LinearLayout btnExperiencias;
    private LinearLayout btnPortalWeb;
    private LinearLayout btnAcciones;

    // Iconos
    private ImageView iconBitacora;
    private ImageView iconDestinos;
    private ImageView iconExperiencias;
    private ImageView iconPortalWeb;
    private ImageView iconAcciones;

    // Textos de título
    private TextView txtBitacora;
    private TextView txtDestinos;
    private TextView txtExperiencias;
    private TextView txtPortalWeb;
    private TextView txtAcciones;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnMenuOptionSelectedListener) {
            callback = (OnMenuOptionSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " debe implementar OnMenuOptionSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_menu, container, false);
        initViews(root);
        setupClickListeners();
        updateMenuSelection(currentSelectedOption);
        return root;
    }

    /**
     * Vinculación de las variables con los identificadores XML (findViewById)
     */
    private void initViews(View root) {
        btnBitacora = root.findViewById(R.id.btn_menu_bitacora);
        btnDestinos = root.findViewById(R.id.btn_menu_destinos);
        btnExperiencias = root.findViewById(R.id.btn_menu_experiencias);
        btnPortalWeb = root.findViewById(R.id.btn_menu_portal_web);
        btnAcciones = root.findViewById(R.id.btn_menu_acciones);

        iconBitacora = root.findViewById(R.id.icon_menu_bitacora);
        iconDestinos = root.findViewById(R.id.icon_menu_destinos);
        iconExperiencias = root.findViewById(R.id.icon_menu_experiencias);
        iconPortalWeb = root.findViewById(R.id.icon_menu_portal_web);
        iconAcciones = root.findViewById(R.id.icon_menu_acciones);

        txtBitacora = root.findViewById(R.id.txt_menu_bitacora);
        txtDestinos = root.findViewById(R.id.txt_menu_destinos);
        txtExperiencias = root.findViewById(R.id.txt_menu_experiencias);
        txtPortalWeb = root.findViewById(R.id.txt_menu_portal_web);
        txtAcciones = root.findViewById(R.id.txt_menu_acciones);
    }

    /**
     * Declaración y asignación de eventos OnClick
     */
    private void setupClickListeners() {
        btnBitacora.setOnClickListener(v -> selectOption(OPTION_BITACORA));
        btnDestinos.setOnClickListener(v -> selectOption(OPTION_DESTINOS));
        btnExperiencias.setOnClickListener(v -> selectOption(OPTION_EXPERIENCIAS));
        btnPortalWeb.setOnClickListener(v -> selectOption(OPTION_PORTAL_WEB));
        btnAcciones.setOnClickListener(v -> selectOption(OPTION_ACCIONES));
    }

    private void selectOption(int optionId) {
        currentSelectedOption = optionId;
        updateMenuSelection(optionId);
        if (callback != null) {
            callback.onOptionSelected(optionId);
        }
    }

    /**
     * Actualiza el aspecto visual del menú resaltando la opción activa
     */
    public void updateMenuSelection(int optionId) {
        Context ctx = getContext();
        if (ctx == null) return;

        int colorPrimary = ContextCompat.getColor(ctx, R.color.primary_teal);
        int colorSecondary = ContextCompat.getColor(ctx, R.color.text_secondary);
        int colorPrimaryText = ContextCompat.getColor(ctx, R.color.text_primary);

        // Resetear todos los items a normal
        resetItem(btnBitacora, iconBitacora, txtBitacora, colorSecondary);
        resetItem(btnDestinos, iconDestinos, txtDestinos, colorSecondary);
        resetItem(btnExperiencias, iconExperiencias, txtExperiencias, colorSecondary);
        resetItem(btnPortalWeb, iconPortalWeb, txtPortalWeb, colorSecondary);
        resetItem(btnAcciones, iconAcciones, txtAcciones, colorSecondary);

        // Resaltar seleccionado
        switch (optionId) {
            case OPTION_BITACORA:
                highlightItem(btnBitacora, iconBitacora, txtBitacora, colorPrimary, colorPrimaryText);
                break;
            case OPTION_DESTINOS:
                highlightItem(btnDestinos, iconDestinos, txtDestinos, colorPrimary, colorPrimaryText);
                break;
            case OPTION_EXPERIENCIAS:
                highlightItem(btnExperiencias, iconExperiencias, txtExperiencias, colorPrimary, colorPrimaryText);
                break;
            case OPTION_PORTAL_WEB:
                highlightItem(btnPortalWeb, iconPortalWeb, txtPortalWeb, colorPrimary, colorPrimaryText);
                break;
            case OPTION_ACCIONES:
                highlightItem(btnAcciones, iconAcciones, txtAcciones, colorPrimary, colorPrimaryText);
                break;
        }
    }

    private void resetItem(LinearLayout btn, ImageView icon, TextView txt, int defaultColor) {
        btn.setBackgroundResource(R.drawable.bg_menu_item_normal);
        icon.setColorFilter(defaultColor);
        txt.setTextColor(defaultColor);
    }

    private void highlightItem(LinearLayout btn, ImageView icon, TextView txt, int activeColor, int textColor) {
        btn.setBackgroundResource(R.drawable.bg_menu_item_selected);
        icon.setColorFilter(activeColor);
        txt.setTextColor(textColor);
    }
}
