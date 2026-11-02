package edu.ups.pap.models.prototype;

import java.util.HashMap;

public class RegistroPrototipos {

    private HashMap<String,ProductoBase> prototipo;

    public RegistroPrototipos(){
        prototipo =new HashMap<>();

    }
    public void registrar(String clave, ProductoBase producto) {
        prototipo.put(clave, producto);

    }
    public ProductoBase obtener(String clave){
        return prototipo.get(clave);

    }
    
}
