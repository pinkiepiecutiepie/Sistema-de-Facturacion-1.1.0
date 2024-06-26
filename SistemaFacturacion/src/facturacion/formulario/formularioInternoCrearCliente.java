package facturacion.formulario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import facturacion.dominio.Cliente;

public class formularioInternoCrearCliente extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldCedula;
    private JTextField textFieldNombres;
    private JTextField textFieldApellidos;
    private JTextField textFieldDireccion;
    private JTextField textFieldTelefono;
    private JTextField textFieldEmail;
    private JTable tablaClientes;
    private DefaultTableModel model;
    private ArrayList<Cliente> listaClientes;
    private formularioMenuPrincipal menuPrincipal;

    public formularioInternoCrearCliente(formularioMenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        this.listaClientes = menuPrincipal.getListaClientes();
        setBounds(100, 100, 749, 591);
        getContentPane().setLayout(null);

        JLabel lblCedula = new JLabel("Cedula");
        lblCedula.setBounds(42, 42, 61, 13);
        getContentPane().add(lblCedula);

        textFieldCedula = new JTextField();
        textFieldCedula.setBounds(124, 42, 279, 19);
        getContentPane().add(textFieldCedula);
        textFieldCedula.setColumns(10);

        JLabel lblNombres = new JLabel("Nombres");
        lblNombres.setBounds(42, 82, 61, 13);
        getContentPane().add(lblNombres);

        textFieldNombres = new JTextField();
        textFieldNombres.setBounds(124, 82, 279, 19);
        getContentPane().add(textFieldNombres);
        textFieldNombres.setColumns(10);

        JLabel lblApellidos = new JLabel("Apellidos");
        lblApellidos.setBounds(42, 121, 61, 13);
        getContentPane().add(lblApellidos);

        textFieldApellidos = new JTextField();
        textFieldApellidos.setBounds(124, 121, 279, 19);
        getContentPane().add(textFieldApellidos);
        textFieldApellidos.setColumns(10);

        JLabel lblDireccion = new JLabel("Direccion");
        lblDireccion.setBounds(42, 160, 61, 13);
        getContentPane().add(lblDireccion);

        textFieldDireccion = new JTextField();
        textFieldDireccion.setBounds(124, 160, 279, 19);
        getContentPane().add(textFieldDireccion);
        textFieldDireccion.setColumns(10);

        JLabel lblTelefono = new JLabel("Telefono");
        lblTelefono.setBounds(42, 195, 61, 13);
        getContentPane().add(lblTelefono);

        textFieldTelefono = new JTextField();
        textFieldTelefono.setBounds(124, 195, 279, 19);
        getContentPane().add(textFieldTelefono);
        textFieldTelefono.setColumns(10);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(42, 232, 61, 13);
        getContentPane().add(lblEmail);

        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(124, 232, 279, 19);
        getContentPane().add(textFieldEmail);
        textFieldEmail.setColumns(10);

        JButton btnNuevo = new JButton("Nuevo");
        btnNuevo.setBounds(42, 298, 85, 21);
        btnNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        getContentPane().add(btnNuevo);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(156, 298, 85, 21);
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarCliente();
            }
        });
        getContentPane().add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(306, 298, 85, 21);
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        getContentPane().add(btnCancelar);

        tablaClientes = new JTable();
        model = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Cédula", "Nombres", "Apellidos", "Dirección", "Teléfono", "Email"
            }
        );
        tablaClientes.setModel(model);

        JScrollPane scrollPane = new JScrollPane(tablaClientes);
        scrollPane.setBounds(42, 343, 658, 178);
        getContentPane().add(scrollPane);
    }

    private void limpiarCampos() {
        textFieldCedula.setText("");
        textFieldNombres.setText("");
        textFieldApellidos.setText("");
        textFieldDireccion.setText("");
        textFieldTelefono.setText("");
        textFieldEmail.setText("");
    }

    private void guardarCliente() {
        String cedula = textFieldCedula.getText();
        String nombres = textFieldNombres.getText();
        String apellidos = textFieldApellidos.getText();
        String direccion = textFieldDireccion.getText();
        String telefono = textFieldTelefono.getText();
        String email = textFieldEmail.getText();

        if (cedula.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() ||
            direccion.isEmpty() || telefono.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente();
        cliente.setCedula(cedula);
        cliente.setNombres(nombres);
        cliente.setApellidos(apellidos);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setEmail(email);

        listaClientes.add(cliente);

        model.addRow(new Object[]{
                cliente.getCedula(),
                cliente.getNombres(),
                cliente.getApellidos(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getEmail()
        });

        menuPrincipal.actualizarListaClientes();
        limpiarCampos();
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }
}