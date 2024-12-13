package com.plataforma;

import java.util.List;

public class PeliculaControlador {
    private PeliculaRepository repo;

    public PeliculaControlador() {
        repo = new PeliculaRepository();
    }

    public void guardarPelicula(String nombre, String genero, int duracion) {
        Pelicula pelicula = new Pelicula(nombre, genero, duracion);
        repo.guardar(pelicula);
    }

    public List<Pelicula> listarPeliculas() {
        return repo.listar();
    }

    public void actualizarPelicula(Pelicula peliculaActualizada) {
        repo.actualizar(peliculaActualizada);
    }

    public void eliminarPelicula(String nombre) {
        repo.eliminar(nombre);
    }
}
