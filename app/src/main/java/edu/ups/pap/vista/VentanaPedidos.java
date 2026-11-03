package edu.ups.pap.vista;

import edu.ups.pap.controllers.ControladorPedidos;
import edu.ups.pap.models.prototype.RegistroPrototipos;
import edu.ups.pap.models.servicio.ServicioPedidos;

public class VentanaPedidos {

    public static void main(String[] args) {

        RegistroPrototipos registro = new RegistroPrototipos();

        // Aquí registraremos los productos base
        // si todavía no están registrados.

        ServicioPedidos servicio = new ServicioPedidos(registro);

        ControladorPedidos controlador =new ControladorPedidos(servicio);

        //VentanaPedidos ventana = new VentanaPedidos(controlador);

        //ventana.setVisible(true);
    }
    
}
