package facturacion.formulario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import facturacion.dominio.Cliente;
import facturacion.dominio.Producto;

public class formularioMenuPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JDesktopPane desktopPane;
    private HashMap<String, JInternalFrame> internalFrames;
    private formularioInternoMostrarClientes mostrarClientesFrame;
    private formularioInternoCrearProducto crearProductoFrame;
    private formularioInternoFacturar facturarFrame;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Producto> listaProductos;

    public formularioMenuPrincipal() {
        internalFrames = new HashMap<>();
        listaClientes = new ArrayList<>();
        listaProductos = new ArrayList<>();
        setTitle("Sistema de Facturacion Electronica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnArchivo = new JMenu("Archivo");
        menuBar.add(mnArchivo);

        JMenuItem mntmSalir = new JMenuItem("Salir");
        mntmSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnArchivo.add(mntmSalir);

        JMenu mnClientes = new JMenu("Clientes");
        menuBar.add(mnClientes);

        JMenuItem mntmCrearNuevo = new JMenuItem("Crear nuevo");
        mntmCrearNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirFormulario("formularioInternoCrearCliente");
            }
        });
        mnClientes.add(mntmCrearNuevo);

        JMenuItem mntmMostrarListaClientes = new JMenuItem("Mostrar lista de clientes");
        mntmMostrarListaClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirFormulario("formularioInternoMostrarClientes");
            }
        });
        mnClientes.add(mntmMostrarListaClientes);

        JMenu mnProductos = new JMenu("Productos");
        menuBar.add(mnProductos);

        JMenuItem mntmCrearProducto = new JMenuItem("Crear Producto");
        mntmCrearProducto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirFormulario("formularioInternoCrearProducto");
            }
        });
        mnProductos.add(mntmCrearProducto);

        JMenuItem mntmMostrarListaProductos = new JMenuItem("Mostrar lista de productos");
        mntmMostrarListaProductos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirFormulario("formularioInternoMostrarProductos");
            }
        });
        mnProductos.add(mntmMostrarListaProductos);

        JMenu mnFacturacion = new JMenu("Facturación");
        menuBar.add(mnFacturacion);

        JMenuItem mntmFacturar = new JMenuItem("Facturar");
        mntmFacturar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirFormulario("formularioInternoFacturar");
            }
        });
        mnFacturacion.add(mntmFacturar);

        JMenu mnRedes = new JMenu("Redes");
        menuBar.add(mnRedes);

        JMenuItem mntmFacebook = new JMenuItem("Facebook");
        mntmFacebook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirFormulario("formularioInternoFacebook");
            }
        });
        mnRedes.add(mntmFacebook);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        desktopPane = new JDesktopPane();
        contentPane.add(desktopPane, BorderLayout.CENTER);
    }

    private void abrirFormulario(String nombreFormulario) {
        JInternalFrame frame = internalFrames.get(nombreFormulario);
        if (frame == null || frame.isClosed()) {
            if ("formularioInternoCrearCliente".equals(nombreFormulario)) {
                frame = new formularioInternoCrearCliente(this);
            } else if ("formularioInternoFacebook".equals(nombreFormulario)) {
                frame = new formularioInternoFacebook();
            } else if ("formularioInternoMostrarClientes".equals(nombreFormulario)) {
                mostrarClientesFrame = new formularioInternoMostrarClientes(getListaClientes());
                frame = mostrarClientesFrame;
            } else if ("formularioInternoCrearProducto".equals(nombreFormulario)) {
                crearProductoFrame = new formularioInternoCrearProducto(this);
                frame = crearProductoFrame;
            } else if ("formularioInternoMostrarProductos".equals(nombreFormulario)) {
                // Logica todavia aun no agregada
            } else if ("formularioInternoFacturar".equals(nombreFormulario)) {
                facturarFrame = new formularioInternoFacturar(this);
                frame = facturarFrame;
            }
            if (frame != null) {
                desktopPane.add(frame);
                internalFrames.put(nombreFormulario, frame);
                frame.setVisible(true);
            }
        } else {
            try {
                frame.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {
                e.printStackTrace();
            }
        }
    }

    public void actualizarListaClientes() {
        if (mostrarClientesFrame != null) {
            mostrarClientesFrame.actualizarTabla();
        }
    }

    public void actualizarListaProductos() {
        if (crearProductoFrame != null) {
            crearProductoFrame.actualizarTabla();
        }
    }

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    public static void main(String[] args) {
        formularioMenuPrincipal formulario = new formularioMenuPrincipal();
        formulario.setVisible(true);
    }
}