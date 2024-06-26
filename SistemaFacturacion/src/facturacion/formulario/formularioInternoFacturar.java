package facturacion.formulario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import facturacion.dominio.Cliente;
import facturacion.dominio.Producto;

public class formularioInternoFacturar extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JTextField textFieldCedula;
    private JComboBox<String> comboBoxProductos;
    private JTextField textFieldCantidad;
    private JTable tablaFactura;
    private DefaultTableModel model;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Producto> listaProductos;
    private ArrayList<Producto> productosAgregados; // Lista para productos seleccionados

    public formularioInternoFacturar(formularioMenuPrincipal menuPrincipal) {
        this.listaClientes = menuPrincipal.getListaClientes();
        this.listaProductos = menuPrincipal.getListaProductos();
        this.productosAgregados = new ArrayList<>(); // Inicializar lista de productos agregados

        setBounds(100, 100, 749, 591);
        getContentPane().setLayout(null);

        JLabel lblCedula = new JLabel("Cedula Cliente");
        lblCedula.setBounds(42, 42, 100, 13);
        getContentPane().add(lblCedula);

        textFieldCedula = new JTextField();
        textFieldCedula.setBounds(150, 42, 279, 19);
        getContentPane().add(textFieldCedula);
        textFieldCedula.setColumns(10);

        JLabel lblProducto = new JLabel("Producto");
        lblProducto.setBounds(42, 82, 100, 13);
        getContentPane().add(lblProducto);

        comboBoxProductos = new JComboBox<>();
        comboBoxProductos.setBounds(150, 82, 279, 19);
        getContentPane().add(comboBoxProductos);

        JLabel lblCantidad = new JLabel("Cantidad");
        lblCantidad.setBounds(42, 121, 100, 13);
        getContentPane().add(lblCantidad);

        textFieldCantidad = new JTextField();
        textFieldCantidad.setBounds(150, 121, 279, 19);
        getContentPane().add(textFieldCantidad);
        textFieldCantidad.setColumns(10);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(150, 160, 85, 21);
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarProductoAFactura();
            }
        });
        getContentPane().add(btnAgregar);

        JButton btnFacturar = new JButton("Facturar");
        btnFacturar.setBounds(260, 160, 85, 21);
        btnFacturar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                realizarFactura();
            }
        });
        getContentPane().add(btnFacturar);

        JButton btnCerrarVentana = new JButton("Cerrar ventana"); // Botón para cerrar la ventana
        btnCerrarVentana.setBounds(370, 160, 140, 21);
        btnCerrarVentana.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cerrarVentana();
            }
        });
        getContentPane().add(btnCerrarVentana);

        tablaFactura = new JTable();
        model = new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Producto", "Cantidad", "Precio Unitario", "Total"
            }
        );
        tablaFactura.setModel(model);

        JScrollPane scrollPane = new JScrollPane(tablaFactura);
        scrollPane.setBounds(42, 200, 658, 178);
        getContentPane().add(scrollPane);

        cargarProductos();
    }

    private void cargarProductos() {
        comboBoxProductos.removeAllItems(); // Limpiar antes de cargar
        for (Producto producto : listaProductos) {
            comboBoxProductos.addItem(producto.getNombre());
        }
        for (Producto producto : productosAgregados) {
            comboBoxProductos.addItem(producto.getNombre()); // Agregar productos ya seleccionados
        }
    }

    private void agregarProductoAFactura() {
        String productoNombre = (String) comboBoxProductos.getSelectedItem();
        String cantidadStr = textFieldCantidad.getText();

        if (productoNombre == null || cantidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto y cantidad", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cantidad debe ser numérica", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Producto productoSeleccionado = null;
        for (Producto producto : listaProductos) {
            if (producto.getNombre().equals(productoNombre)) {
                productoSeleccionado = producto;
                break;
            }
        }

        if (productoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double precioUnitario = productoSeleccionado.getPrecio();
        double total = precioUnitario * cantidad;

        // Agregar producto a la lista de productos seleccionados
        productosAgregados.add(productoSeleccionado);

        model.addRow(new Object[]{
                productoNombre,
                cantidad,
                precioUnitario,
                total
        });

        // Actualizar la lista de productos disponibles
        cargarProductos();
    }

    private void realizarFactura() {
        String cedulaCliente = textFieldCedula.getText();

        if (cedulaCliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la cédula del cliente", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = null;
        for (Cliente c : listaClientes) {
            if (c.getCedula().equals(cedulaCliente)) {
                cliente = c;
                break;
            }
        }

        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Factura realizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cerrarVentana() {
        // Cerrar la ventana interna
        this.dispose();
    }
}