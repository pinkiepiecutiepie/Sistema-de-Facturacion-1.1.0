package facturacion.formulario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import facturacion.dominio.Cliente;

public class formularioInternoMostrarClientes extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTable tablaClientes;
    private DefaultTableModel model;
    private ArrayList<Cliente> listaClientes;

    public formularioInternoMostrarClientes(ArrayList<Cliente> clientes) {
        this.listaClientes = clientes;
        setTitle("Lista de Clientes");
        setBounds(100, 100, 600, 400);

        String[] columnas = {"Cédula", "Nombres", "Apellidos", "Dirección", "Teléfono", "Email"};
        model = new DefaultTableModel(columnas, 0);
        tablaClientes = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(tablaClientes);
        getContentPane().add(scrollPane);

        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarTabla();
            }
        });
        getContentPane().add(btnActualizar, BorderLayout.SOUTH);

        actualizarTabla();
    }

    public void actualizarTabla() {
        model.setRowCount(0);
        for (Cliente cliente : listaClientes) {
            model.addRow(new Object[] {
                cliente.getCedula(),
                cliente.getNombres(),
                cliente.getApellidos(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getEmail()
            });
        }
    }
}