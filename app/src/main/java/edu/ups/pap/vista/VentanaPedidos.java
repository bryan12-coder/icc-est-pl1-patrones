package edu.ups.pap.vista;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import edu.ups.pap.models.Pedido;
import edu.ups.pap.models.servicio.ServicioPedidos;

public class VentanaPedidos extends JFrame {

    private ServicioPedidos servicio;
    private ArrayList<Pedido> pedidos = new ArrayList<>();

    private JComboBox<String> productos;
    private JComboBox<String> ingredientes;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextArea detalle;

    private int contador = 1;

    public VentanaPedidos(ServicioPedidos servicio) {

        this.servicio = servicio;

        setTitle("PedidoLab");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Colores del diseño
        Color fondo = new Color(135,206, 235);
        Color gris = new Color(125, 125, 125);
        Color blanco = Color.ORANGE;

        JPanel principal = new JPanel(new BorderLayout(10, 10));
        principal.setBackground(fondo);
       // principal.setBorder(
           // BorderFactory.createEmptyBorder(15, 15, 15, 15)
       // );

        setContentPane(principal);

        // TITULO
        JLabel titulo = new JLabel("PEDIDO");
        titulo.setFont(new Font("Times new roman", Font.BOLD, 28));
        principal.add(titulo, BorderLayout.NORTH);

        // CUATRO CUADROS
        JPanel centro = new JPanel(new GridLayout(2, 2, 15, 15));
        
        centro.setBackground(fondo);
        principal.add(centro, BorderLayout.CENTER);

        // CUADRO 1: SELECCIONAR PRODUCTO
        JPanel panelProductos = new JPanel();
        panelProductos.setBackground(gris);
        panelProductos.setLayout(new BoxLayout(
            panelProductos, BoxLayout.Y_AXIS
        ));
        panelProductos.setBorder(
            BorderFactory.createEmptyBorder(20, 15, 20, 15)
        );

        JLabel textoProducto = new JLabel("Seleccionar producto base");
        textoProducto.setForeground(blanco);

        productos = new JComboBox<>(
            new String[]{"Café", "Ensalada", "Pizza"}
        );

        JButton clonar = new JButton("Clonar producto");

        panelProductos.add(textoProducto);
        panelProductos.add(Box.createVerticalStrut(20));
        panelProductos.add(productos);
        panelProductos.add(Box.createVerticalStrut(20));
        panelProductos.add(clonar);

        centro.add(panelProductos);

        // CUADRO 2: PEDIDOS CREADOS
        JPanel panelPedidos = new JPanel(new BorderLayout(10, 10));
        panelPedidos.setBackground(gris);
        panelPedidos.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        JLabel textoPedidos = new JLabel("Pedidos creados");
        textoPedidos.setForeground(blanco);

        modelo = new DefaultTableModel(
            new String[]{"ID", "Producto", "Composición", "Total"}, 0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        panelPedidos.add(textoPedidos, BorderLayout.NORTH);
        panelPedidos.add(new JScrollPane(tabla), BorderLayout.CENTER);

        centro.add(panelPedidos);

        // CUADRO 3: AGREGAR INGREDIENTES
        JPanel panelIngredientes = new JPanel();
        panelIngredientes.setBackground(gris);
        panelIngredientes.setLayout(new BoxLayout(
            panelIngredientes, BoxLayout.Y_AXIS
        ));
        panelIngredientes.setBorder(
            BorderFactory.createEmptyBorder(20, 15, 20, 15)
        );

        JLabel textoIngredientes = new JLabel("Agregar ingredientes:");
        textoIngredientes.setForeground(blanco);

        ingredientes = new JComboBox<>(
            new String[]{
                "Azúcar", "Crema", "Tomate",
                "Pollo", "Queso", "Pepperoni"
            }
        );

        JButton agregar = new JButton("Agregar ingrediente");
        JButton reiniciar = new JButton("Reiniciar pedido");

        panelIngredientes.add(textoIngredientes);
        panelIngredientes.add(Box.createVerticalStrut(20));
        panelIngredientes.add(ingredientes);
        panelIngredientes.add(Box.createVerticalStrut(20));
        panelIngredientes.add(agregar);
        panelIngredientes.add(Box.createVerticalStrut(10));
        panelIngredientes.add(reiniciar);

        centro.add(panelIngredientes);

        // CUADRO 4: DETALLE DEL PEDIDO
        JPanel panelDetalle = new JPanel(new BorderLayout(10, 10));
        panelDetalle.setBackground(gris);
        panelDetalle.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        JLabel textoDetalle = new JLabel("Detalle de pedido seleccionado");
        textoDetalle.setForeground(blanco);

        detalle = new JTextArea();
        detalle.setEditable(false);
        detalle.setFont(new Font("Arial", Font.PLAIN, 15));

        panelDetalle.add(textoDetalle, BorderLayout.NORTH);
        panelDetalle.add(new JScrollPane(detalle), BorderLayout.CENTER);

        centro.add(panelDetalle);

        // ACCIONES DE LOS BOTONES
        clonar.addActionListener(e -> clonarProducto());
        agregar.addActionListener(e -> agregarIngrediente());
        reiniciar.addActionListener(e -> reiniciarPedidos());

        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarDetalle();
            }
        });
    }

    // CLONAR PRODUCTO
    private void clonarProducto() {

        String producto = productos.getSelectedItem().toString();
        String clave = "";

        switch (producto) {
            case "Café":
                clave = "cafe";
                break;
            case "Ensalada":
                clave = "ensalada";
                break;
            case "Pizza":
                clave = "pizza";
                break;
        }

        String id = String.format("P-%03d", contador);

        Pedido pedido = servicio.crearPedido(clave, id);

        if (pedido == null) {
            JOptionPane.showMessageDialog(this, "Producto no encontrado");
            return;
        }

        pedidos.add(pedido);
        contador++;

        actualizarTabla();

        int fila = pedidos.size() - 1;
        tabla.setRowSelectionInterval(fila, fila);
    }

    // AGREGAR INGREDIENTE
    private void agregarIngrediente() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(
                this, "Seleccione un pedido primero"
            );
            return;
        }

        Pedido pedido = pedidos.get(fila);
        String ingrediente = ingredientes.getSelectedItem().toString();

        try {

            switch (ingrediente) {
                case "Azúcar":
                    pedido = servicio.agregarAzucar(pedido);
                    break;
                case "Crema":
                    pedido = servicio.agregarCrema(pedido);
                    break;
                case "Tomate":
                    pedido = servicio.agregarTomate(pedido);
                    break;
                case "Pollo":
                    pedido = servicio.agregarPollo(pedido);
                    break;
                case "Queso":
                    pedido = servicio.agregarQueso(pedido);
                    break;
                case "Pepperoni":
                    pedido = servicio.agregarPepperoni(pedido);
                    break;
            }

            pedidos.set(fila, pedido);

            actualizarTabla();
            tabla.setRowSelectionInterval(fila, fila);
            mostrarDetalle();

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Ingrediente no permitido",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // ACTUALIZAR TABLA
    private void actualizarTabla() {

        modelo.setRowCount(0);

        for (Pedido pedido : pedidos) {

            modelo.addRow(new Object[]{
                pedido.id(),
                pedido.tipo(),
                pedido.descripcion(),
                "$" + pedido.precio().toPlainString()
            });
        }
    }

    // MOSTRAR DETALLE
    private void mostrarDetalle() {

        int fila = tabla.getSelectedRow();

        if (fila == -1) {
            detalle.setText("");
            return;
        }

        Pedido pedido = pedidos.get(fila);

        detalle.setText(
            "ID Pedido: " + pedido.id() +
            "\n\nProducto: " + pedido.tipo() +
            "\n\nIngredientes: " + pedido.descripcion() +
            "\n\nTotal: $" + pedido.precio().toPlainString()
        );
    }

    // REINICIAR PEDIDOS
    private void reiniciarPedidos() {

        pedidos.clear();
        modelo.setRowCount(0);
        detalle.setText("");

        JOptionPane.showMessageDialog(
            this, "Pedidos reiniciados"
        );
    }

    
}

