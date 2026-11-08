package edu.ups.pap.models.decorator;

import java.math.BigDecimal;

import edu.ups.pap.models.Pedido;

public class Pepperoni extends DecoradorIngrediente {

    public Pepperoni(Pedido pedido) {
        super(pedido);
       
    }
    @Override
    public String descripcion() {
        return super.descripcion() + " + pepperoni";
    }

    @Override
    public BigDecimal precio() {
        return super.precio().add(new BigDecimal("1.10"));
    }
    
}
