package com.jeanp09.fundamentoskotlin.reto3

data class Articulo (val nombreArt: String, val precioBase: Double, var precioOfrecidoMaximo: Double?)

fun main() {
    val articulos = listOf(
        Articulo("Espejo antiguo", 250000.00, null),
        Articulo("Cuadro antiguo", 500000.00, null),
        Articulo("Libro Grimorio antiguo", 100000.00, null),
        Articulo("Reloj de pared antiguo", 700000.00, null),
        Articulo("Escrituras de los años 630 a.C", 20000000.00, null)
    )

    for (articulo in articulos) {
        println("El precio base de ${articulo.nombreArt} es ${"%.2f".format(articulo.precioBase)}.\n" +
                "Ingrese el precio oferta para ${articulo.nombreArt}: ")
        val precioOferta = readLine()?.toDoubleOrNull()

        if (precioOferta != null && (articulo.precioOfrecidoMaximo == null || precioOferta > articulo.precioOfrecidoMaximo!!)) {
            articulo.precioOfrecidoMaximo = precioOferta
        }
    }

    for (articulo in articulos) {
        if (articulo.precioOfrecidoMaximo != null && articulo.precioOfrecidoMaximo!! >= articulo.precioBase){
            println("El artículo ${articulo.nombreArt} se vende al ofertante con el precio más alto: \n${"%.2f".format(articulo.precioOfrecidoMaximo)}")
        } else {
            println("No se ofrecieron ofertas para el artículo: ${articulo.nombreArt}\n" +
                    "El artículo ${articulo.nombreArt} se vende a la casa de subastas por " +
                    "el precio mínimo de: ${"%.2f".format(articulo.precioBase)}")
        }
    }
}