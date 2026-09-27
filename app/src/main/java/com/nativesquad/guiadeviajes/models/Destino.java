package com.nativesquad.guiadeviajes.models;

import java.io.Serializable;

/**
 * Modelo representativo de un Destino Turístico para la aplicación NativeSquad.
 */
public class Destino implements Serializable {
    private int id;
    private String titulo;
    private String subtitulo;
    private String region;
    private int imagenResId;
    private String descripcionHistorica;
    private String descripcionGeografica;
    private String actividadesRecomendadas;
    private String clima;
    private boolean esFavorito;

    public Destino(int id, String titulo, String subtitulo, String region, int imagenResId,
                   String descripcionHistorica, String descripcionGeografica,
                   String actividadesRecomendadas, String clima) {
        this.id = id;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.region = region;
        this.imagenResId = imagenResId;
        this.descripcionHistorica = descripcionHistorica;
        this.descripcionGeografica = descripcionGeografica;
        this.actividadesRecomendadas = actividadesRecomendadas;
        this.clima = clima;
        this.esFavorito = false;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public String getRegion() {
        return region;
    }

    public int getImagenResId() {
        return imagenResId;
    }

    public String getDescripcionHistorica() {
        return descripcionHistorica;
    }

    public String getDescripcionGeografica() {
        return descripcionGeografica;
    }

    public String getActividadesRecomendadas() {
        return actividadesRecomendadas;
    }

    public String getClima() {
        return clima;
    }

    public boolean isEsFavorito() {
        return esFavorito;
    }

    public void setEsFavorito(boolean esFavorito) {
        this.esFavorito = esFavorito;
    }
}
