package com.plataforma;

public class PlataformaDeStreaming {
    private String nombre;
    private String tipoContenido;
    private int numeroUsuarios;

    public PlataformaDeStreaming(String nombre, String tipoContenido, int numeroUsuarios) {
        setNombre(nombre);
        setTipoContenido(tipoContenido);
        setNumeroUsuarios(numeroUsuarios);
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la plataforma no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public void setTipoContenido(String tipoContenido) {
        if (tipoContenido == null || tipoContenido.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de contenido no puede estar vacío.");
        }
        this.tipoContenido = tipoContenido;
    }

    public void setNumeroUsuarios(int numeroUsuarios) {
        if (numeroUsuarios < 0) {
            throw new IllegalArgumentException("El número de usuarios no puede ser negativo.");
        }
        this.numeroUsuarios = numeroUsuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoContenido() {
        return tipoContenido;
    }

    public int getNumeroUsuarios() {
        return numeroUsuarios;
    }

}
