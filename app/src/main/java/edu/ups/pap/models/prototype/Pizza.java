package edu.ups.pap.models.prototype;

import java.math.BigDecimal;

import edu.ups.pap.models.TipoProducto;

public class Pizza extends ProductoBase {

    public Pizza(String id) {
        super(id, "Pizza sencilla ", new BigDecimal("5.00"), TipoProducto.Pizza);
        
    }

    @Override
    public ProductoBase copiar(String id) {
        return new Pizza(id);
    }
    
}
