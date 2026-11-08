package edu.ups.pap;

import java.math.BigDecimal;


import edu.ups.pap.models.Pedido;
import edu.ups.pap.models.prototype.ProductoBase;
import edu.ups.pap.models.prototype.RegistroPrototipos;
import edu.ups.pap.models.servicio.ServicioPedidos;

public class pruebasAceptacion {

    public static void main(String[] args) {

        RegistroPrototipos registro = new RegistroPrototipos();
        ServicioPedidos servicio = new ServicioPedidos(registro);

        // CA01: Clonar dos cafes
        System.out.println("\n===== CA01 =====");

        Pedido cafe1 = servicio.crearPedido("cafe", "C001");
        Pedido cafe2 = servicio.crearPedido("cafe", "C002");

        if (cafe1 != null && cafe2 != null
                && !cafe1.id().equals(cafe2.id())
                && cafe1.precio().compareTo(new BigDecimal("2.20")) == 0
                && cafe2.precio().compareTo(new BigDecimal("2.20")) == 0) {
            System.out.println("APROBADO");
        } else {
            System.out.println("FALLIDO");
        }

        // CA02: Verificar que el clon sea independiente
        System.out.println("\n===== CA02 =====");

        ProductoBase original = registro.obtener("cafe");

        if (cafe1 != null && original != null
                && cafe1 != original
                && original.precio().compareTo(new BigDecimal("2.20")) == 0) {
            System.out.println("APROBADO");
        } else {
            System.out.println("FALLIDO");
        }

        // CA03: Cafe con azucar y crema
        System.out.println("\n===== CA03 =====");

        if (cafe1 != null) {
            cafe1 = servicio.agregarAzucar(cafe1);
            cafe1 = servicio.agregarCrema(cafe1);

            System.out.println(cafe1.descripcion());
            System.out.println("Total: $" + cafe1.precio());

            if (cafe1.precio().compareTo(new BigDecimal("3.90")) == 0
                    && cafe1.descripcion().contains("Azucar")
                    && cafe1.descripcion().contains("Crema")) {
                System.out.println("APROBADO");
            } else {
                System.out.println("FALLIDO");
            }
        } else {
            System.out.println("FALLIDO");
        }

        // CA04: El segundo cafe no debe cambiar
        System.out.println("\n===== CA04 =====");

        if (cafe2 != null) {
            System.out.println(cafe2.descripcion());
            System.out.println("Total: $" + cafe2.precio());
        }

        if (cafe2 != null
                && cafe2.precio().compareTo(new BigDecimal("2.20")) == 0
                && !cafe2.descripcion().contains("Azucar")
                && !cafe2.descripcion().contains("Crema")) {
            System.out.println("APROBADO");
        } else {
            System.out.println("FALLIDO");
        }

        // CA05: Ensalada con tomate y pollo
        System.out.println("\n===== CA05 =====");

        Pedido ensalada = servicio.crearPedido("ensalada", "E001");

        if (ensalada != null) {
            ensalada = servicio.agregarTomate(ensalada);
            ensalada = servicio.agregarPollo(ensalada);

            System.out.println(ensalada.descripcion());
            System.out.println("Total: $" + ensalada.precio());

            if (ensalada.precio().compareTo(new BigDecimal("5.60")) == 0
                    && ensalada.descripcion().contains("Tomate")
                    && ensalada.descripcion().contains("Pollo")) {
                System.out.println("APROBADO");
            } else {
                System.out.println("FALLIDO");
            }
        } else {
            System.out.println("FALLIDO");
        }

        // CA06: Pizza con queso y pepperoni
        System.out.println("\n===== CA06 =====");

        Pedido pizza = servicio.crearPedido("pizza", "P001");

        if (pizza != null) {
            pizza = servicio.agregarQueso(pizza);
            pizza = servicio.agregarPepperoni(pizza);

            System.out.println(pizza.descripcion());
            System.out.println("Total: $" + pizza.precio());

            if (pizza.precio().compareTo(new BigDecimal("7.10")) == 0
                    && pizza.descripcion().contains("Queso")
                    && pizza.descripcion().contains("Pepperoni")) {
                System.out.println("APROBADO");
            } else {
                System.out.println("FALLIDO");
            }
        } else {
            System.out.println("FALLIDO");
        }

        // CA07: No permitir queso en cafe
        System.out.println("\n===== CA07 =====");

        Pedido cafe3 = servicio.crearPedido("cafe", "C003");

        if (cafe3 != null) {
            BigDecimal precioAntes = cafe3.precio();
            String descripcionAntes = cafe3.descripcion();
            boolean rechazado = false;

            try {
                cafe3 = servicio.agregarQueso(cafe3);
            } catch (IllegalArgumentException e) {
                rechazado = true;
                System.out.println("Mensaje: " + e.getMessage());
            }

            if (rechazado
                    && cafe3.precio().compareTo(precioAntes) == 0
                    && cafe3.descripcion().equals(descripcionAntes)) {
                System.out.println("APROBADO");
            } else {
                System.out.println("FALLIDO");
            }
        } else {
            System.out.println("FALLIDO");
        }

        // CA08: Agregar ingrediente sin pedido
        System.out.println("\n===== CA08 =====");

        boolean advertencia = false;

        try {
            servicio.agregarAzucar(null);
        } catch (IllegalArgumentException e) {
            advertencia = true;
            System.out.println("Mensaje: " + e.getMessage());
        }

        if (advertencia) {
            System.out.println("APROBADO");
        } else {
            System.out.println("FALLIDO");
        }

        // CA09: Clonar producto inexistente
        System.out.println("\n===== CA09 =====");

        Pedido inexistente =
                servicio.crearPedido("no-existe", "X001");

        if (inexistente == null) {
            System.out.println("APROBADO");
        } else {
            System.out.println("FALLIDO");
        }

        // CA10: Agregar azucar dos veces
        System.out.println("\n===== CA10 =====");

        Pedido cafe4 = servicio.crearPedido("cafe", "C004");

        if (cafe4 != null) {
            cafe4 = servicio.agregarAzucar(cafe4);
            cafe4 = servicio.agregarAzucar(cafe4);

            System.out.println(cafe4.descripcion());
            System.out.println("Total: $" + cafe4.precio());

            if (cafe4.precio().compareTo(new BigDecimal("3.20")) == 0
                    && cafe4.descripcion().contains("Azucar + Azucar")) {
                System.out.println("APROBADO");
            } else {
                System.out.println("FALLIDO");
            }
        } else {
            System.out.println("FALLIDO");
        }

        // CA11: Reiniciar y crear otro cafe
        System.out.println("\n===== CA11 =====");

        Pedido anterior = servicio.crearPedido("cafe", "C005");

        if (anterior != null) {
            anterior = servicio.agregarAzucar(anterior);
        }

        // Simulamos que se limpia el pedido seleccionado
        anterior = null;

        Pedido nuevo = servicio.crearPedido("cafe", "C006");

        if (nuevo != null) {
            System.out.println(nuevo.descripcion());
            System.out.println("Total: $" + nuevo.precio());
        }

        if (nuevo != null
                && nuevo.precio().compareTo(new BigDecimal("2.20")) == 0
                && !nuevo.descripcion().contains("Azucar")
                && !nuevo.descripcion().contains("Crema")) {
            System.out.println("APROBADO");
        } else {
            System.out.println("FALLIDO");
        }
    }

}
