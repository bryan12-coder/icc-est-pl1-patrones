package edu.ups.pap.controllers;

import edu.ups.pap.models.Pedido;
import edu.ups.pap.models.prototype.ProductoBase;
import edu.ups.pap.models.servicio.ServicioPedidos;

public class ControladorPedidos {

    private ServicioPedidos servicio;

    public ControladorPedidos(ServicioPedidos servicio) {
        this.servicio = servicio;
    }
    public ProductoBase crearPedido(String clave, String nuevoId) {
        return servicio.crearPedido(clave, nuevoId);
    }

    public Pedido agregarQueso(Pedido pedido) {
    return servicio.agregarQueso(pedido);
    }

    public Pedido agregarIngrediente(Pedido pedido, String ingrediente) {
        if (pedido == null) {
            throw new IllegalArgumentException("El pedido no puede ser nulo");
        }

        if (ingrediente == null) {
            throw new IllegalArgumentException("El ingrediente no puede ser nulo");
        }

        switch (ingrediente.trim().toLowerCase()) {

            case "queso":
                return servicio.agregarQueso(pedido);

            case "pepperoni":
                return servicio.agregarPepperoni(pedido);

            case "tomate":
                return servicio.agregarTomate(pedido);

            case "pollo":
                return servicio.agregarPollo(pedido);

            case "crema":
                return servicio.agregarCrema(pedido);

            case "azucar":
            case "azúcar":
                return servicio.agregarAzucar(pedido);

            default:
                throw new IllegalArgumentException(
                    "Ingrediente no válido: " + ingrediente
                );
        }

    }
    
}
