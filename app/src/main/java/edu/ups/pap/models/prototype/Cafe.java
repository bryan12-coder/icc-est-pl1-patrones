package edu.ups.pap.models.prototype;

import java.math.BigDecimal;

import edu.ups.pap.models.TipoProducto;

public class Cafe extends ProductoBase{

    public Cafe(String id) {
        super(id,"Cafe" ,  new BigDecimal("2.20"),TipoProducto.Cafe);
    }

    @Override
    public ProductoBase copiar(String nuevoId) {
        return new Cafe(nuevoId);
    }
    
}
