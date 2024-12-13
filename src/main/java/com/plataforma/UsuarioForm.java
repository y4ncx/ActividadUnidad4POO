package com.plataforma;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import com.plataforma.UsuarioControlador;


public class UsuarioForm {
    public JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton guardarButton;
    private JButton listarButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private UsuarioRepository repo;
    private UsuarioControlador controlador;


    public UsuarioForm() {
        repo = new UsuarioRepository();
        controlador = new UsuarioControlador(); // Inicializar controlador

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarUsuario();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarUsuarios();
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });
    }








    private void guardarUsuario() {
        try {
            String nombre = textField1.getText();
            int edad = Integer.parseInt(textField2.getText());
            String suscripcion = textField3.getText();

            if (nombre.isEmpty() || suscripcion.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos son obligatorios.");
            }

            controlador.guardarUsuario(nombre, edad, suscripcion);

            JOptionPane.showMessageDialog(null, "Usuario guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void listarUsuarios() {
        List<Usuario> usuarios = repo.listar();
        if (usuarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay usuarios registrados.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder();
            for (Usuario usuario : usuarios) {
                sb.append("Nombre: ").append(usuario.getNombre()).append("\n");
                sb.append("Edad: ").append(usuario.getEdad()).append("\n");
                sb.append("Suscripción: ").append(usuario.getSuscripcion()).append("\n");
                sb.append("-----------------------------\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Usuarios Registrados", JOptionPane.INFORMATION_MESSAGE);
        }
    }



    private void actualizarUsuario() {
        try {
            String nombre = textField1.getText();
            String edadStr = textField2.getText();
            String suscripcion = textField3.getText();

            if (nombre.isEmpty() || suscripcion.isEmpty()) {
                throw new IllegalArgumentException("Todos los campos deben estar llenos.");
            }

            int edad = Integer.parseInt(edadStr); // Esto puede lanzar NumberFormatException.
            if (edad <= 0) {
                throw new IllegalArgumentException("La edad debe ser un número positivo.");
            }

            Usuario usuarioActualizado = new Usuario(nombre, edad, suscripcion);
            controlador.actualizarUsuario(usuarioActualizado);

            JOptionPane.showMessageDialog(null, "Usuario actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar los campos después de actualizar
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "La edad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void eliminarUsuario() {
        String nombre = textField1.getText();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar al usuario: " + nombre + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            controlador.eliminarUsuario(nombre);
            JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Crear Usuario");
        frame.setContentPane(new UsuarioForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
