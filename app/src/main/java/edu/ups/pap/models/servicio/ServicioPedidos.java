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
    //Vamos a verificar que exista el pedido
    private void validarPedido(Pedido pedido){
        if(pedido == null){
            throw new IllegalArgumentException("El pedido no puede llegar a ser nulo");

        }
    }
    // Verificamos que el ingrediente corresppnda la producto
    private void validarTipo(Pedido pedido, String tipoEsperado){
        validarPedido(pedido);

        if(!pedido.tipo().name().equalsIgnoreCase(tipoEsperado)){
            throw new IllegalArgumentException("Ingrediente incompatible con " + pedido.tipo());
        }

    }
    public Pedido agregarQueso(Pedido pedido) {
        validarTipo(pedido, "PIZZA");
        return new Queso(pedido);
    }

    public Pedido agregarPepperoni(Pedido pedido) {
        validarTipo(pedido, "PIZZA");
        return new Pepperoni(pedido);
    }

    // Ingredientes para Ensalada
    public Pedido agregarTomate(Pedido pedido) {
        validarTipo(pedido, "ENSALADA");
        return new Tomate(pedido);
    }

    public Pedido agregarPollo(Pedido pedido) {
        validarTipo(pedido, "ENSALADA");
        return new Pollo(pedido);
    }

    // Ingredientes para Cafe
    public Pedido agregarCrema(Pedido pedido) {
        validarTipo(pedido, "CAFE");
        return new Crema(pedido);
    }

    public Pedido agregarAzucar(Pedido pedido) {
        validarTipo(pedido, "CAFE");
        return new Azucar(pedido);
    }

    
    
}
