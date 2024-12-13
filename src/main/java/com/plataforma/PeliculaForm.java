package com.plataforma;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PeliculaForm {
    public JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton guardarButton;
    private JButton listarButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private PeliculaRepository repo;
    private PeliculaControlador controlador;

    public PeliculaForm() {
        repo = new PeliculaRepository();
        controlador = new PeliculaControlador();

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPelicula();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarPeliculas();
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPelicula();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPelicula();
            }
        });
    }

    private void guardarPelicula() {
        try {
            String nombre = textField1.getText();
            String genero = textField2.getText();
            int duracion = Integer.parseInt(textField3.getText());

            if (nombre.isEmpty() || genero.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios.");
            }

            Pelicula nuevaPelicula = new Pelicula(nombre, genero, duracion);
            controlador.guardarPelicula(nombre, genero, duracion);

            JOptionPane.showMessageDialog(null, "Película guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "La duración debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarPeliculas() {
        List<Pelicula> peliculas = repo.listar();
        if (peliculas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay películas registradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder();
            for (Pelicula pelicula : peliculas) {
                sb.append("Nombre: ").append(pelicula.getNombre()).append("\n");
                sb.append("Género: ").append(pelicula.getGenero()).append("\n");
                sb.append("Duración: ").append(pelicula.getDuracion()).append(" minutos\n");
                sb.append("-----------------------------\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Películas Registradas", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void actualizarPelicula() {
        try {
            String nombre = textField1.getText();
            String genero = textField2.getText();
            int duracion = Integer.parseInt(textField3.getText());

            if (nombre.isEmpty() || genero.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos deben estar llenos.");
            }

            Pelicula peliculaActualizada = new Pelicula(nombre, genero, duracion);
            controlador.actualizarPelicula(peliculaActualizada);

            JOptionPane.showMessageDialog(null, "Película actualizada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "La duración debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPelicula() {
        String nombre = textField1.getText();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar la película: " + nombre + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            controlador.eliminarPelicula(nombre);
            JOptionPane.showMessageDialog(null, "Película eliminada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestionar Películas");
        frame.setContentPane(new PeliculaForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
