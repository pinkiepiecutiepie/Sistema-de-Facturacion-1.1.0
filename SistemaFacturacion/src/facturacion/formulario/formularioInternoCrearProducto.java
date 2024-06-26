package facturacion.formulario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import facturacion.dominio.Producto;

public class formularioInternoCrearProducto extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldCodigo;
    private JTextField textFieldNombre;
    private JTextField textFieldPrecio;
    private JTable tablaProductos;
    private DefaultTableModel model;
    private ArrayList<Producto> listaProductos;
    private formularioMenuPrincipal menuPrincipal;

    public formularioInternoCrearProducto(formularioMenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
        this.listaProductos = menuPrincipal.getListaProductos();
        setBounds(100, 100, 749, 591);
        getContentPane().setLayout(null);

        JLabel lblCodigo = new JLabel("Código");
        lblCodigo.setBounds(42, 42, 61, 13);
        getContentPane().add(lblCodigo);

        textFieldCodigo = new JTextField();
        textFieldCodigo.setBounds(124, 42, 279, 19);
        getContentPane().add(textFieldCodigo);
        textFieldCodigo.setColumns(10);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(42, 82, 61, 13);
        getContentPane().add(lblNombre);

        textFieldNombre = new JTextField();
        textFieldNombre.setBounds(124, 82, 279, 19);
        getContentPane().add(textFieldNombre);
        textFieldNombre.setColumns(10);

        JLabel lblPrecio = new JLabel("Precio");
        lblPrecio.setBounds(42, 121, 61, 13);
        getContentPane().add(lblPrecio);

        textFieldPrecio = new JTextField();
        textFieldPrecio.setBounds(124, 121, 279, 19);
        getContentPane().add(textFieldPrecio);
        textFieldPrecio.setColumns(10);

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
                guardarProducto();
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

        tablaProductos = new JTable();
        model = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "Código", "Nombre", "Precio"
                }
        );
        tablaProductos.setModel(model);

        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        scrollPane.setBounds(42, 343, 658, 178);
        getContentPane().add(scrollPane);
    }

    private void limpiarCampos() {
        textFieldCodigo.setText("");
        textFieldNombre.setText("");
        textFieldPrecio.setText("");
    }

    private void guardarProducto() {
        String codigo = textFieldCodigo.getText();
        String nombre = textFieldNombre.getText();
        String precioStr = textFieldPrecio.getText();

        if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            Producto producto = new Producto();
            producto.setCodigo(codigo);
            producto.setNombre(nombre);
            producto.setPrecio(precio);

            listaProductos.add(producto);

            model.addRow(new Object[]{
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio()
            });

            menuPrincipal.actualizarListaProductos();
            limpiarCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El precio debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void actualizarTabla() {
        model.setRowCount(0);
        for (Producto producto : listaProductos) {
            model.addRow(new Object[]{
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio()
            });
        }
    }
}