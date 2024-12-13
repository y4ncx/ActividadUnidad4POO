package com.plataforma;

import java.util.List;

public class UsuarioControlador {
    private UsuarioRepository repo;

    public UsuarioControlador() {
        repo = new UsuarioRepository();
    }

    public void guardarUsuario(String nombre, int edad, String suscripcion) {
        Usuario usuario = new Usuario(nombre, edad, suscripcion);
        repo.guardar(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return repo.listar();
    }

    public void actualizarUsuario(Usuario usuarioActualizado) {
        repo.actualizar(usuarioActualizado);
    }

    public void eliminarUsuario(String nombre) {
        repo.eliminar(nombre);
    }



}
