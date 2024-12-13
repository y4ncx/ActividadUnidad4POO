package com.plataforma;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PeliculaRepository {
    private static final String FILE_PATH = "peliculas.json";
    private Gson gson = new Gson();

    // Guardar película
    public void guardar(Pelicula pelicula) {
        List<Pelicula> peliculas = listar();  // Obtener la lista de películas existentes
        peliculas.add(pelicula);  // Añadir la nueva película a la lista

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(peliculas, writer);  // Guardar la lista actualizada en el archivo
        } catch (IOException e) {
            System.out.println("Error al guardar película: " + e.getMessage());
        }
    }

    // Listar películas
    public List<Pelicula> listar() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Pelicula>>() {}.getType();
            return gson.fromJson(reader, listType);  // Leer la lista de películas desde el archivo
        } catch (IOException e) {
            return new ArrayList<>();  // Si no hay archivo o hay un error, devolver lista vacía
        }
    }

    // Actualizar película
    public void actualizar(Pelicula peliculaActualizada) {
        List<Pelicula> peliculas = listar();
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getNombre().equals(peliculaActualizada.getNombre())) {
                pelicula.setGenero(peliculaActualizada.getGenero());
                pelicula.setDuracion(peliculaActualizada.getDuracion());
                break;
            }
        }
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(peliculas, writer);  // Guardar la lista actualizada en el archivo
        } catch (IOException e) {
            System.out.println("Error al actualizar película: " + e.getMessage());
        }
    }

    // Eliminar película
    public void eliminar(String nombre) {
        List<Pelicula> peliculas = listar();
        peliculas.removeIf(pelicula -> pelicula.getNombre().equals(nombre));  // Eliminar película por nombre
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(peliculas, writer);  // Guardar la lista actualizada en el archivo
        } catch (IOException e) {
            System.out.println("Error al eliminar película: " + e.getMessage());
        }
    }
}
