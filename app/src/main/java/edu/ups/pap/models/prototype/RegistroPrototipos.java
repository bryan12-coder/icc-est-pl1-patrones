package edu.ups.pap.models.prototype;

import java.util.HashMap;

public class RegistroPrototipos {

    private HashMap<String,ProductoBase> prototipo;

    public RegistroPrototipos() {
        prototipo = new HashMap<>();
    
        registrar("cafe", new Cafe("BASE-CAFE"));
        registrar("pizza", new Pizza("BASE-PIZZA"));
        registrar("ensalada", new Ensalada("BASE-ENSALADA"));
    }
    public void registrar(String clave, ProductoBase producto) {
        prototipo.put(clave, producto);

    }
    public ProductoBase obtener(String clave){
        return prototipo.get(clave);

    }
    public ProductoBase crearCopia(String clave, String nuevoId) {
        ProductoBase producto = prototipo.get(clave);
        if(producto == null){
            return null;
        }
        return producto.copiar(nuevoId);
    }
}
