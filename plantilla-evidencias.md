# Evidencias APP1 — PedidoLab

**Repositorio:** [enlace Git por completar]  
**Integrante:** Bryam Collaguazo  
**Fecha:** 

## Casos de aceptación - PedidoLab

Se realizaron 11 pruebas de aceptación para verificar el funcionamiento de los patrones de diseño Prototype y Decorator.

| Caso | Entrada | Resultado esperado | Resultado real | Evidencia | Estado |
|------|---------|--------------------|-----------------|-----------|--------|
| CA01 | Clonar Café dos veces | ID diferente, ambos cuestan $2.20 | C001 y C002, ambos $2.20 | Captura 1 | APROBADO |
| CA02 | Comparar clon y prototipo | Referencias distintas, original sin cambios | Referencias distintas: true, original $2.20 | Captura 1 | APROBADO |
| CA03 | Café + Azúcar + Crema | Descripción correcta, total $3.90 | Descripción correcta, total $2.95 | Captura 1 | FALLIDO |
| CA04 | Segundo Café sin ingredientes | Mantiene $2.20 sin ingredientes | Café sin ingredientes, $2.20 | Captura 1 | APROBADO |
| CA05 | Ensalada + Tomate + Pollo | Total $5.60 | Total $4.90 | Captura 1 | FALLIDO |
| CA06 | Pizza + Queso + Pepperoni | Total $7.10 | Total $7.00 | Captura 1 | FALLIDO |
| CA07 | Agregar Queso a Café | Operación rechazada, sin cambios | Se permitió agregar queso | Captura 1 | FALLIDO |
| CA08 | Agregar ingrediente sin pedido | Advertencia y sin cambios | "El pedido no puede ser nulo" | Captura 1 | APROBADO |
| CA09 | Clonar clave inexistente | No se crea pedido | Resultado: null | Captura 1 | APROBADO |
| CA10 | Agregar Azúcar dos veces a Café | Total $3.20, dos decoradores | Total $2.70, dos decoradores | Captura 2 | FALLIDO |
| CA11 | Reiniciar y clonar Café | Nuevo Café con precio $2.20 | Nuevo Café con precio $2.20 | Captura 2 | APROBADO |



## Identificación de patrones

- **Prototype:** clase, método y archivo donde se registra el prototipo y se genera la copia.
- **Decorator:** clase abstracta, referencia al componente y dos decoradores concretos.
- **Integración:** método de servicio donde un clon recibe al menos dos decoradores.

## Decisiones de diseño

1. 
2. 

## Conclusiones

1. 
2. 
3. 
