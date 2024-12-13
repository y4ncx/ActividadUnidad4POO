package com.plataforma;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PlataformaRepository {
    private static final String FILE_PATH = "plataformas.json";
    private Gson gson = new Gson();

    // Guardar plataforma
    public void guardar(PlataformaDeStreaming plataforma) {
        List<PlataformaDeStreaming> plataformas = listar();  // Obtener la lista de plataformas existentes
        plataformas.add(plataforma);  // Añadir la nueva plataforma a la lista

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(plataformas, writer);  // Guardar la lista actualizada en el archivo
        } catch (IOException e) {
            System.out.println("Error al guardar plataforma: " + e.getMessage());
        }
    }

    // Listar plataformas
    public List<PlataformaDeStreaming> listar() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<PlataformaDeStreaming>>() {}.getType();
            return gson.fromJson(reader, listType);  // Leer la lista de plataformas desde el archivo
        } catch (IOException e) {
            return new ArrayList<>();  // Si no hay archivo o hay un error, devolver lista vacía
        }
    }

    // Actualizar plataforma
    public void actualizar(PlataformaDeStreaming plataformaActualizada) {
        List<PlataformaDeStreaming> plataformas = listar();
        for (PlataformaDeStreaming plataforma : plataformas) {
            if (plataforma.getNombre().equals(plataformaActualizada.getNombre())) {
                plataforma.setTipoContenido(plataformaActualizada.getTipoContenido());
                plataforma.setNumeroUsuarios(plataformaActualizada.getNumeroUsuarios());
                break;
            }
        }
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(plataformas, writer);
        } catch (IOException e) {
            System.out.println("Error al actualizar plataforma: " + e.getMessage());
        }
    }

    // Eliminar plataforma
    public void eliminar(String nombre) {
        List<PlataformaDeStreaming> plataformas = listar();
        plataformas.removeIf(plataforma -> plataforma.getNombre().equals(nombre));  // Eliminar plataforma por nombre
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(plataformas, writer);  // Guardar la lista actualizada en el archivo
        } catch (IOException e) {
            System.out.println("Error al eliminar plataforma: " + e.getMessage());
        }
    }
}
