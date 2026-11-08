# Evidencias APP1 — PedidoLab

**Repositorio:** [enlace Git por completar]  
**Integrante:** Bryam Collaguazo  
**Fecha:** 

## Casos de aceptación - PedidoLab

Se realizaron 11 pruebas de aceptación para verificar el funcionamiento de los patrones de diseño Prototype y Decorator.

| Caso | Entrada | Resultado esperado | Resultado real | Evidencia | Estado |
|------|---------|--------------------|-----------------|-----------|--------|
| CA01 | Clonar Café dos veces | ID diferente, ambos cuestan $2.20 | C001 y C002, ambos $2.20 | app/src/assents/image copy 2.png | APROBADO |
| CA02 | Comparar clon y prototipo | Referencias distintas, original sin cambios | Referencias distintas: true, original $2.20 | app/src/assents/image copy 3.png | APROBADO |
| CA03 | Café + Azúcar + Crema | Descripción correcta, total $3.90 | Descripción correcta, total $3.90| app/src/assents/image copy 4.png | APROBADO |
| CA04 | Segundo Café sin ingredientes | Mantiene $2.20 sin ingredientes | Café sin ingredientes, $2.20 | app/src/assents/image copy 5.png | APROBADO |
| CA05 | Ensalada + Tomate + Pollo | Total $5.60 | Total $5.60 | app/src/assents/image copy 6.png | APROBADO |
| CA06 | Pizza + Queso + Pepperoni | Total $7.10 | Total $7.10 | app/src/assents/image copy 7.png | APROBADO |
| CA07 | Agregar Queso a Café | Operación rechazada, sin cambios | No permmitio agregar queso | app/src/assents/image copy 8.png | APROBADO |
| CA08 | Agregar ingrediente sin pedido | Advertencia y sin cambios | "El pedido no puede ser nulo" | app/src/assents/image copy 9.png| APROBADO |
| CA09 | Clonar clave inexistente | No se crea pedido | Resultado: null | app/src/assents/image copy 10.png | APROBADO |
| CA10 | Agregar Azúcar dos veces a Café | Total $3.20, dos decoradores | Total $3.20, dos decoradores | app/src/assents/image copy 11.png |  APROBADO |
| CA11 | Reiniciar y clonar Café | Nuevo Café con precio $2.20 | Nuevo Café con precio $2.20 | app/src/assents/image copy 12.png| APROBADO |



## Identificación de patrones

- **Prototype:** Esta en la parte de RegistrarPrototipos el metodo crearCopia() nos va permitiendo que creemos copias sin llegar a modificar los originales
- **Decorator:** Este esta en el DecoradorIngredente el cual nos va permitiendo agragar diferentes ingreidentes y aumentar el precio de las cosas que se aumentan
- **Integración:** En ServiciosPedidos se llegan a clonar los productos y se va agrangandio ingredientes envase a los decoradores

## Decisiones de diseño

1. Se llego a crear el patron Prototype porque necesitabs hacer que los pedidos sean independientes
2. Se llego a utilizar el Patron Decorator para agregar ingredientes sin llegar a tocar o modificar los productos originales.

## Conclusiones

1. En conclusion podemos decir que el patron prototype es muy eficiente en este tipo de practicas ya que nose permite crear copiasque sean independientes de los diferentes productos
2. En conclusiones puedo decir que el Decorator nos permite y nos facilita agregar ingredientes en los productos concretos y nos ayuda a calcular los precios de cada uno
3. Las pruebas nos llegaron a permitir que comprobemos los estados de cada caso para comprobar si el precio es igual cuyando se clona un producto o cuando se intenta agregar un producto no compatible cin otro.
