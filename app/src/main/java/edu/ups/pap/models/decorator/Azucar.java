package edu.ups.pap.models.decorator;

import java.math.BigDecimal;

import edu.ups.pap.models.Pedido;

public class Azucar extends DecoradorIngrediente {

    public Azucar(Pedido pedido) {
        super(pedido);
        
    }
    @Override 
    public String descripcion(){
        return super.descripcion() + " + Azucar";
    }
    @Override 
    public BigDecimal precio(){
        return super.precio().add(new BigDecimal("0.50"));
    }
    
}
