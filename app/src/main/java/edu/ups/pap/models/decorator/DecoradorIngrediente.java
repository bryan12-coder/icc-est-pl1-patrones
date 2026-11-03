package edu.ups.pap.models.decorator;

import java.math.BigDecimal;

import edu.ups.pap.models.Pedido;
import edu.ups.pap.models.TipoProducto;

public abstract class DecoradorIngrediente implements Pedido{
    protected Pedido pedido;

    public DecoradorIngrediente(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override 
    public String descripcion(){
        return pedido.descripcion();
    }
    @Override
    public BigDecimal precio() {
        return pedido.precio();
    }
    @Override
    public String id() {
        return pedido.id();
    }
    @Override
    public TipoProducto tipo() {
        return pedido.tipo();
    }



    

    
    
}
