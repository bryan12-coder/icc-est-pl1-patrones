package edu.ups.pap.models;

import java.math.BigDecimal;

public interface Pedido {
    
    String descripcion();
    
    BigDecimal precio();

    String id();

    TipoProducto tipo();
    


    
    
}
