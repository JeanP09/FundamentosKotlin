package com.jeanp09.fundamentoskotlin.reto1

import kotlin.random.Random

fun main() {
    val numNotificaciones = Random.nextInt(0, 150)

    val mensaje = when {
        numNotificaciones < 1 -> "No existen mensajes disponibles"
        numNotificaciones < 100 -> "Tienes $numNotificaciones notificaciones recibidas"
        else -> "Tienes 99+ notificaciones recibidas"
    }
    println(mensaje)
}