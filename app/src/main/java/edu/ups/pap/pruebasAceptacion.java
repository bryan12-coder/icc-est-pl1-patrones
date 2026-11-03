package edu.ups.pap;

import java.math.BigDecimal;

import edu.ups.pap.controllers.ControladorPedidos;
import edu.ups.pap.models.Pedido;
import edu.ups.pap.models.prototype.ProductoBase;
import edu.ups.pap.models.prototype.RegistroPrototipos;
import edu.ups.pap.models.servicio.ServicioPedidos;

public class pruebasAceptacion {

    private static int aprobados = 0;
    private static int fallidos = 0;

    public static void verificar(String caso, boolean condicion) {
        if (condicion) {
            System.out.println(caso + ": APROBADO");
            aprobados++;
        } else {
            System.out.println(caso + ": FALLIDO");
            fallidos++;
        }
    }

    public static boolean precioEs(Pedido pedido, String precio) {
        return pedido != null && pedido.precio().compareTo(new BigDecimal(precio)) == 0;
    }

    public static boolean contiene(Pedido pedido, String ingrediente) {
        return pedido != null
                && pedido.descripcion().toLowerCase()
                    .contains(ingrediente.toLowerCase());
    }

    public static void main(String[] args) {

        RegistroPrototipos registro = new RegistroPrototipos();
        ServicioPedidos servicio = new ServicioPedidos(registro);
        ControladorPedidos controlador =
                new ControladorPedidos(servicio);

        // CA01: Clonar Cafe dos veces
        System.out.println("\nCA01");

        ProductoBase cafe1 = registro.crearCopia("cafe", "C001");
        ProductoBase cafe2 = registro.crearCopia("cafe", "C002");

        if (cafe1 != null && cafe2 != null) {
            System.out.println("Cafe 1: " + cafe1.id()
                    + " - $" + cafe1.precio());
            System.out.println("Cafe 2: " + cafe2.id()
                    + " - $" + cafe2.precio());
        }

        verificar("CA01",
                cafe1 != null
                && cafe2 != null
                && !cafe1.id().equals(cafe2.id())
                && precioEs(cafe1, "2.20")
                && precioEs(cafe2, "2.20"));

        // CA02: Comparar clon y prototipo
        System.out.println("\n===== CA02 =====");

        ProductoBase original = registro.obtener("cafe");

        if (original != null) {
            System.out.println("Original: " + original.id()
                    + " - $" + original.precio());
        }

        System.out.println("Referencias distintas: "
                + (cafe1 != original));

        verificar("CA02",
                cafe1 != null
                && original != null
                && cafe1 != original
                && precioEs(original, "2.20"));

        // CA03: Cafe con Azucar y Crema
        System.out.println("\n===== CA03 =====");

        if (cafe1 != null) {
            Pedido cafeDecorado = servicio.agregarAzucar(cafe1);
            cafeDecorado = servicio.agregarCrema(cafeDecorado);

            System.out.println(cafeDecorado.descripcion());
            System.out.println("Total: $" + cafeDecorado.precio());

            verificar("CA03",
                    (contiene(cafeDecorado, "azucar")
                    || contiene(cafeDecorado, "azúcar"))
                    && contiene(cafeDecorado, "crema")
                    && precioEs(cafeDecorado, "3.90"));
        } else {
            verificar("CA03", false);
        }

        // CA04: Segundo Cafe sin ingredientes
        System.out.println("\n===== CA04 =====");

        if (cafe2 != null) {
            System.out.println(cafe2.descripcion());
            System.out.println("Total: $" + cafe2.precio());
        }

        verificar("CA04",
                precioEs(cafe2, "2.20")
                && !contiene(cafe2, "azucar")
                && !contiene(cafe2, "azúcar")
                && !contiene(cafe2, "crema"));

        // CA05: Ensalada con Tomate y Pollo
        System.out.println("\n===== CA05 =====");

        Pedido ensalada = controlador.crearPedido("ensalada", "E001");

        if (ensalada != null) {
            ensalada = servicio.agregarTomate(ensalada);
            ensalada = servicio.agregarPollo(ensalada);

            System.out.println(ensalada.descripcion());
            System.out.println("Total: $" + ensalada.precio());
        }

        verificar("CA05",
                contiene(ensalada, "tomate")
                && contiene(ensalada, "pollo")
                && precioEs(ensalada, "5.60"));

        // CA06: Pizza con Queso y Pepperoni
        System.out.println("\n===== CA06 =====");

        Pedido pizza = controlador.crearPedido("pizza", "P001");

        if (pizza != null) {
            pizza = servicio.agregarQueso(pizza);
            pizza = servicio.agregarPepperoni(pizza);

            System.out.println(pizza.descripcion());
            System.out.println("Total: $" + pizza.precio());
        }

        verificar("CA06",
                contiene(pizza, "queso")
                && contiene(pizza, "pepperoni")
                && precioEs(pizza, "7.10"));

        // CA07: Rechazar Queso en Cafe
        System.out.println("\n===== CA07 =====");

        Pedido cafeValidacion =
                controlador.crearPedido("cafe", "C003");

        boolean rechazado = false;

        if (cafeValidacion != null) {
            String descripcionAntes = cafeValidacion.descripcion();
            BigDecimal precioAntes = cafeValidacion.precio();

            try {
                Pedido resultado = controlador.agregarIngrediente(
                        cafeValidacion, "queso");

                rechazado =
                        resultado.descripcion().equals(descripcionAntes)
                        && resultado.precio().compareTo(precioAntes) == 0;

                System.out.println("Resultado: "
                        + resultado.descripcion());

            } catch (IllegalArgumentException ex) {
                rechazado = true;
                System.out.println("Operacion rechazada: "
                        + ex.getMessage());
            }
        }

        verificar("CA07", rechazado);

        // CA08: Ingrediente sin pedido seleccionado
        System.out.println("\n===== CA08 =====");

        boolean sinPedidoRechazado = false;

        try {
            controlador.agregarIngrediente(null, "queso");
            System.out.println("No se mostro advertencia");
        } catch (IllegalArgumentException ex) {
            sinPedidoRechazado = true;
            System.out.println("Advertencia: " + ex.getMessage());
        }

        verificar("CA08", sinPedidoRechazado);

        // CA09: Clave inexistente
        System.out.println("\n===== CA09 =====");

        ProductoBase inexistente =
                registro.crearCopia("producto-inexistente", "X001");

        System.out.println("Resultado: " + inexistente);

        verificar("CA09", inexistente == null);

        // CA10: Azucar dos veces en Cafe
        System.out.println("\n===== CA10 =====");

        Pedido cafeDobleAzucar =
                controlador.crearPedido("cafe", "C004");

        if (cafeDobleAzucar != null) {
            cafeDobleAzucar =
                    servicio.agregarAzucar(cafeDobleAzucar);

            cafeDobleAzucar =
                    servicio.agregarAzucar(cafeDobleAzucar);

            System.out.println(cafeDobleAzucar.descripcion());
            System.out.println("Total: $" + cafeDobleAzucar.precio());
        }

        verificar("CA10",
                precioEs(cafeDobleAzucar, "3.20")
                && cafeDobleAzucar != null
                && (cafeDobleAzucar.descripcion()
                    .toLowerCase().split("azucar", -1).length - 1
                    + cafeDobleAzucar.descripcion()
                    .toLowerCase().split("azúcar", -1).length - 1) >= 2);

        // CA11: Reiniciar y volver a clonar Cafe
        System.out.println("\n===== CA11 =====");

        Pedido pedidoAnterior =
                controlador.crearPedido("cafe", "C005");

        if (pedidoAnterior != null) {
            pedidoAnterior =
                    servicio.agregarAzucar(pedidoAnterior);
        }

        // Simular reinicio de la seleccion
        pedidoAnterior = null;

        Pedido nuevoCafe =
                controlador.crearPedido("cafe", "C006");

        if (nuevoCafe != null) {
            System.out.println(nuevoCafe.descripcion());
            System.out.println("Total: $" + nuevoCafe.precio());
        }

        verificar("CA11",
                precioEs(nuevoCafe, "2.20")
                && !contiene(nuevoCafe, "azucar")
                && !contiene(nuevoCafe, "azúcar")
                && !contiene(nuevoCafe, "crema"));

        // RESUMEN
        System.out.println("\n===== RESUMEN DE PRUEBAS =====");
        System.out.println("Aprobadas: " + aprobados);
        System.out.println("Fallidas: " + fallidos);
        System.out.println("Total: " + (aprobados + fallidos));
    }
    
}
