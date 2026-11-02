package edu.ups.pap.models.prototype;

import java.math.BigDecimal;

import edu.ups.pap.models.Pedido;
import edu.ups.pap.models.TipoProducto;

public abstract class ProductoBase implements Pedido,Prototipo<ProductoBase> {
    private   String id;
    private   String descripcion;
    private   BigDecimal precio;
    private TipoProducto producto;

    public ProductoBase(String id, String descripcion, BigDecimal precio,TipoProducto producto) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
        this.producto=producto;
    }

    @Override
    public String id() {
        return  id ;
    }

    @Override
    public String descripcion() {
        return  descripcion ;
        
    }

    @Override
    public BigDecimal precio() {
        return  precio;
       
    }
    @Override
    public TipoProducto tipo(){
        return producto;
    }
    @Override 
    public abstract ProductoBase copiar(String id);


    

    
    

    



    
}
