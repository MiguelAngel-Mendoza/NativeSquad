package com.nativesquad.guiadeviajes.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.nativesquad.guiadeviajes.R;

/**
 * PortalWebFragment: Módulo de navegación web embebida con WebView y caja de texto (RF-05).
 * Permite digitar o modificar una URL y navegar dentro de la aplicación.
 */
public class PortalWebFragment extends Fragment {

    private static final String DEFAULT_URL = "https://colombia.travel/es";

    private EditText etUrlInput;
    private MaterialButton btnCargarUrl;
    private ProgressBar progressWeb;
    private WebView webView;
    private TextView tvWebStatus;

    // Controles de navegación
    private ImageButton btnBack;
    private ImageButton btnForward;
    private ImageButton btnRefresh;

    // Chips de accesos rápidos
    private MaterialButton chipColombiaTravel;
    private MaterialButton chipParques;
    private MaterialButton chipWiki;
    private MaterialButton chipPolitecnico;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_portal_web, container, false);
        initViews(root);
        setupWebView();
        setupEvents();

        // Carga inicial
        loadUrl(DEFAULT_URL);

        return root;
    }

    /**
     * Vinculación de variables con identificadores de vista XML (findViewById)
     */
    private void initViews(View root) {
        etUrlInput = root.findViewById(R.id.et_url_input);
        btnCargarUrl = root.findViewById(R.id.btn_cargar_url);
        progressWeb = root.findViewById(R.id.progress_web_loading);
        webView = root.findViewById(R.id.web_view);
        tvWebStatus = root.findViewById(R.id.tv_web_status);

        btnBack = root.findViewById(R.id.btn_web_back);
        btnForward = root.findViewById(R.id.btn_web_forward);
        btnRefresh = root.findViewById(R.id.btn_web_refresh);

        chipColombiaTravel = root.findViewById(R.id.chip_colombia_travel);
        chipParques = root.findViewById(R.id.chip_parques_nacionales);
        chipWiki = root.findViewById(R.id.chip_wiki_turismo);
        chipPolitecnico = root.findViewById(R.id.chip_politecnico);
    }

    /**
     * Configuración avanzada del WebView interno
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        // WebViewClient para mantener la navegación dentro de la app sin abrir navegadores externos
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressWeb.setVisibility(View.VISIBLE);
                tvWebStatus.setText("Cargando: " + url);
                etUrlInput.setText(url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressWeb.setVisibility(View.GONE);
                tvWebStatus.setText("Completado: " + view.getTitle());
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                progressWeb.setVisibility(View.GONE);
                tvWebStatus.setText("Error al cargar la página");
            }
        });

        // WebChromeClient para actualizar barra de progreso
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressWeb.setProgress(newProgress);
                if (newProgress == 100) {
                    progressWeb.setVisibility(View.GONE);
                } else {
                    progressWeb.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * Declaración y asignación de eventos para botones y entradas
     */
    private void setupEvents() {
        btnCargarUrl.setOnClickListener(v -> processEnteredUrl());

        etUrlInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_GO || actionId == EditorInfo.IME_ACTION_DONE) {
                processEnteredUrl();
                return true;
            }
            return false;
        });

        btnBack.setOnClickListener(v -> {
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                Toast.makeText(getContext(), "No hay páginas previas", Toast.LENGTH_SHORT).show();
            }
        });

        btnForward.setOnClickListener(v -> {
            if (webView.canGoForward()) {
                webView.goForward();
            } else {
                Toast.makeText(getContext(), "No hay páginas siguientes", Toast.LENGTH_SHORT).show();
            }
        });

        btnRefresh.setOnClickListener(v -> webView.reload());

        // Accesos rápidos
        chipColombiaTravel.setOnClickListener(v -> loadUrl("https://colombia.travel/es"));
        chipParques.setOnClickListener(v -> loadUrl("https://www.parquesnacionales.gov.co/"));
        chipWiki.setOnClickListener(v -> loadUrl("https://es.wikipedia.org/wiki/Turismo_en_Colombia"));
        chipPolitecnico.setOnClickListener(v -> loadUrl("https://www.poli.edu.co"));
    }

    private void processEnteredUrl() {
        String url = etUrlInput.getText().toString().trim();
        if (url.isEmpty()) {
            Toast.makeText(getContext(), "Por favor ingresa una URL", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }

        hideKeyboard();
        loadUrl(url);
    }

    private void loadUrl(String url) {
        etUrlInput.setText(url);
        webView.loadUrl(url);
    }

    private void hideKeyboard() {
        if (getActivity() != null && getActivity().getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (webView != null) {
            webView.stopLoading();
        }
    }
}
