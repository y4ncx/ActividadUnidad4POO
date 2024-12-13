package com.plataforma;

import java.util.List;

public class PlataformaDeStreamingControlador {
    private PlataformaRepository repo;

    public PlataformaDeStreamingControlador() {
        repo = new PlataformaRepository();
    }


    public void guardarPlataforma(String nombre, String tipoContenido, int numeroUsuarios) {
        PlataformaDeStreaming plataforma = new PlataformaDeStreaming(nombre, tipoContenido, numeroUsuarios);
        repo.guardar(plataforma);
    }


    public List<PlataformaDeStreaming> listarPlataformas() {
        return repo.listar();
    }


    public void actualizarPlataforma(PlataformaDeStreaming plataformaActualizada) {
        repo.actualizar(plataformaActualizada);
    }


    public void eliminarPlataforma(String nombre) {
        repo.eliminar(nombre);
    }
}
