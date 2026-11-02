package edu.ups.pap.models.prototype;

import java.math.BigDecimal;

import edu.ups.pap.models.TipoProducto;

public class Ensalada extends ProductoBase{

    public Ensalada(String id) {
        super(id, "Ensalada", new BigDecimal("3.00"), TipoProducto.Ensalada);
        
    }

    @Override
    public ProductoBase copiar(String id) {
       return new Ensalada(id);
    }
    
    
}
