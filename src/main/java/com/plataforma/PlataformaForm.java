package com.plataforma;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PlataformaForm {
    public JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton guardarButton;
    private JButton listarButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private PlataformaRepository repo;
    private PlataformaDeStreamingControlador controlador;

    public PlataformaForm() {
        repo = new PlataformaRepository();
        controlador = new PlataformaDeStreamingControlador();

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPlataforma();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarPlataformas();
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPlataforma();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPlataforma();
            }
        });
    }

    private void guardarPlataforma() {
        try {
            String nombre = textField1.getText();
            String tipoContenido = textField2.getText();
            int numeroUsuarios = Integer.parseInt(textField3.getText());

            if (nombre.isEmpty() || tipoContenido.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios.");
            }

            PlataformaDeStreaming nuevaPlataforma = new PlataformaDeStreaming(nombre, tipoContenido, numeroUsuarios);
            controlador.guardarPlataforma(nombre, tipoContenido, numeroUsuarios);

            JOptionPane.showMessageDialog(null, "Plataforma guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El número de usuarios debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarPlataformas() {
        List<PlataformaDeStreaming> plataformas = repo.listar();
        if (plataformas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay plataformas registradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder();
            for (PlataformaDeStreaming plataforma : plataformas) {
                sb.append("Nombre: ").append(plataforma.getNombre()).append("\n");
                sb.append("Tipo de Contenido: ").append(plataforma.getTipoContenido()).append("\n");
                sb.append("Número de Usuarios: ").append(plataforma.getNumeroUsuarios()).append("\n");
                sb.append("-----------------------------\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Plataformas Registradas", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void actualizarPlataforma() {
        try {
            String nombre = textField1.getText();
            String tipoContenido = textField2.getText();
            int numeroUsuarios = Integer.parseInt(textField3.getText());

            if (nombre.isEmpty() || tipoContenido.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos deben estar llenos.");
            }

            PlataformaDeStreaming plataformaActualizada = new PlataformaDeStreaming(nombre, tipoContenido, numeroUsuarios);
            controlador.actualizarPlataforma(plataformaActualizada);

            JOptionPane.showMessageDialog(null, "Plataforma actualizada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El número de usuarios debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarPlataforma() {
        String nombre = textField1.getText();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar la plataforma: " + nombre + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            controlador.eliminarPlataforma(nombre);
            JOptionPane.showMessageDialog(null, "Plataforma eliminada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestionar Plataformas");
        frame.setContentPane(new PlataformaForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
