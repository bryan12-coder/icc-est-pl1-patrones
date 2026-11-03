package edu.ups.pap.models.decorator;

import java.math.BigDecimal;

import edu.ups.pap.models.Pedido;
import edu.ups.pap.models.prototype.Cafe;

public class Crema extends DecoradorIngrediente{

    public Crema(Pedido pedido) {
        super(pedido);
        
    }
    @Override 
    public String descripcion(){
        return super.descripcion() + " + crema";
    }
    @Override 
    public BigDecimal precio(){
        return super.precio().add(new BigDecimal("0.50"));
    }
    

    
}
