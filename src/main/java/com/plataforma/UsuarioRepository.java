package com.plataforma;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    private static final String FILE_PATH = "usuarios.json";
    private Gson gson = new Gson();

    // Guardar usuario
    public void guardar(Usuario usuario) {
        List<Usuario> usuarios = listar();
        usuarios.add(usuario);

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(usuarios, writer);  // Guardar la lista actualizada en el archivo
        } catch (IOException e) {
            System.out.println("Error al guardar usuario: " + e.getMessage());
        }
    }

    // Listar usuarios
    public List<Usuario> listar() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Usuario>>() {}.getType();
            return gson.fromJson(reader, listType);  // Leer la lista de usuarios desde el archivo
        } catch (IOException e) {
            return new ArrayList<>();  // devuelve lista si no hay error
        }
    }

    public void actualizar(Usuario usuarioActualizado) {
        List<Usuario> usuarios = listar();
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(usuarioActualizado.getNombre())) {
                usuario.setEdad(usuarioActualizado.getEdad());
                usuario.setSuscripcion(usuarioActualizado.getSuscripcion());
                break;
            }
        }
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    public void eliminar(String nombre) {
        List<Usuario> usuarios = listar();
        usuarios.removeIf(usuario -> usuario.getNombre().equals(nombre));
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }
    }


}
