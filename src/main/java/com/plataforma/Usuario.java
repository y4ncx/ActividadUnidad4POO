package com.plataforma;

public class Usuario {
    private String nombre;
    private int edad;
    private String suscripcion;

    public Usuario(String nombre, int edad, String suscripcion) {
        setNombre(nombre);
        setEdad(edad);
        setSuscripcion(suscripcion);
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        if (edad <= 0) {
            throw new IllegalArgumentException("La edad debe ser mayor a 0.");
        }
        this.edad = edad;
    }

    public void setSuscripcion(String suscripcion) {
        if (!"Básica".equals(suscripcion) && !"Premium".equals(suscripcion)) {
            throw new IllegalArgumentException("La suscripción debe ser 'Básica' o 'Premium'.");
        }
        this.suscripcion = suscripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getSuscripcion() {
        return suscripcion;
    }
}
