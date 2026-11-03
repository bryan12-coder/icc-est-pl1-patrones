package edu.ups.pap.models.servicio;

import edu.ups.pap.models.Pedido;
import edu.ups.pap.models.decorator.Azucar;
import edu.ups.pap.models.decorator.Crema;
import edu.ups.pap.models.decorator.Pepperoni;
import edu.ups.pap.models.decorator.Pollo;
import edu.ups.pap.models.decorator.Queso;
import edu.ups.pap.models.decorator.Tomate;
import edu.ups.pap.models.prototype.ProductoBase;
import edu.ups.pap.models.prototype.RegistroPrototipos;

public class ServicioPedidos {
    private RegistroPrototipos registro;


    public ServicioPedidos(RegistroPrototipos registro) {
        this.registro = registro;
    }

    public ProductoBase crearPedido(String clave, String nuevoId) {

    return registro.crearCopia(clave, nuevoId);
    }
    public Pedido agregarQueso(Pedido pedido) {

    return new Queso(pedido);
    }

    public Pedido agregarPepperoni(Pedido pedido) {

    return new Pepperoni(pedido);

    }
    public Pedido agregarTomate(Pedido pedido) {
    return new Tomate(pedido);
    }

    public Pedido agregarPollo(Pedido pedido) {
    return new Pollo(pedido);
    }

     public Pedido agregarCrema(Pedido pedido) {
    return new Crema(pedido);
    }

    public Pedido agregarAzucar(Pedido pedido) {
        
    return new Azucar(pedido);
    }

    
    
}
