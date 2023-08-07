package com.jeanp09.fundamentoskotlin.reto5

class Nequi {
    private var celular: String? = null
    private var clave: String? = null
    private var saldoDisponible: Double = 45000.0
    private var intentosRestantes: Int = 3
    private var loggedIn: Boolean = false
    private val SALDO_MINIMO = 2000.00
    private val SALDO_MAXIMO = 8927726.00
    private val MINIMO_RETIRAR = 5000.00
    private val MAXIMO_RETIRAR = 2700000.0

    fun ingresar() {
        while (!loggedIn && intentosRestantes > 0) {
            println("Escriba el número de celular:")
            val celularInput = readLine()
            println("Escriba la clave de 4 dígitos:")
            val claveInput = readLine()

            if (validarCredenciales(celularInput, claveInput)) {
                loggedIn = true
                println("Bienvenido a Nequi, puedes continuar")
                mostrarSaldoDisponible()
            } else {
                intentosRestantes--
                if (intentosRestantes > 0) {
                    println("Tus datos de acceso no son correctos")
                    println("Tienes $intentosRestantes intentos más")
                } else {
                    println("Ya alcanzaste los intentos máximos de inicio de sesión.\n" +
                            "Adiós.")
                }
            }
        }
    }

    private fun validarCredenciales(celularInput: String?, claveInput: String?): Boolean {
        celular = celularInput
        clave = claveInput
        return true
    }

    fun mostrarSaldoDisponible() {
        println("Tu saldo disponible es: $saldoDisponible")
    }

    fun sacar() {
        if (!loggedIn) {
            println("Debes iniciar sesión primero")
            return
        }

        if (saldoDisponible < SALDO_MINIMO) {
            println("No te alcanza para realizar el retiro")
            return
        }

        println("¿Desea hacer el retiro? Escribe 'si' o 'no'")
        val respuesta = readLine()?.toLowerCase()

        if (respuesta == "si") {
            println("Escriba la cantidad que desea retirar:")
            val cantidad = readLine()?.toDoubleOrNull()

            if (cantidad != null && cantidad <= MAXIMO_RETIRAR && cantidad >= MINIMO_RETIRAR) {
                if (cantidad <= saldoDisponible) {
                    val codigoRetiro = generarCodigoRetiro()
                    saldoDisponible -= cantidad
                    println("El retiro ha sido exitoso.\n" +
                            "El código de retiro es: $codigoRetiro")
                    mostrarSaldoDisponible()
                } else {
                    println("El saldo es insuficiente para hacer el retiro")
                }
            } else {
                println("La cantidad a retirar no está dentro del rango permitido.\n" +
                        "El monto mínimo a retirar es: $MINIMO_RETIRAR\n" +
                        "El monto máximo a retirar es: $MAXIMO_RETIRAR")
            }
        } else {
            println("El retiro ha sido cancelado")
        }
    }

    private fun generarCodigoRetiro(): Int {
        return (100000..999999).random()
    }

    fun enviar() {
        if (!loggedIn) {
            println("Primero debes iniciar sesión")
            return
        }

        println("Escriba el número de teléfono al que desea enviar dinero:")
        val numeroDestino = readLine()

        println("Escriba el valor a enviar:")
        val valorEnviar = readLine()?.toDoubleOrNull()

        if (valorEnviar != null && valorEnviar <= saldoDisponible) {
            saldoDisponible -= valorEnviar
            println("¡Envío exitoso! Has enviado $valorEnviar al número $numeroDestino.")
            mostrarSaldoDisponible()
        } else {
            println("Este valor es inválido o no es suficiente para enviar")
        }
    }

    fun recargar() {
        if (!loggedIn) {
            println("Debes ingresar primero la sesión")
            return
        }

        println("Escriba el valor a recargar:")
        val valorRecarga = readLine()?.toDoubleOrNull()

        if (valorRecarga != null) {
            if (saldoDisponible + valorRecarga <= SALDO_MAXIMO) {
                println("¿Está seguro que desea realizar la recarga de $valorRecarga? (si/no)")
                val respuesta = readLine()?.toLowerCase()
                if (respuesta == "si") {
                    saldoDisponible += valorRecarga
                    println("Recarga exitosa. Nuevo saldo: $saldoDisponible")
                } else {
                    println("Recarga cancelada.")
                }
            } else {
                println("La recarga excede el saldo máximo permitido.")
                println("Saldo máximo permitido: $SALDO_MAXIMO")
            }
        } else {
            println("Valor inválido para la recarga.")
        }
    }

    fun salir() {
        loggedIn = false
        println("Gracias por confiar en nosotros. ¡Nos vemos luego!")
    }
}

fun main() {
    val nequi = Nequi()

    while (true) {
        println("1. Ingresar\n" +
                "2. Saldo Disponible\n" +
                "3. Saca\n" +
                "4. Envía\n" +
                "5. Recarga\n" +
                "6. Salir\n" +
                "Escribe una opción:")

        when (readLine()?.toIntOrNull()) {
            1 -> nequi.ingresar()
            2 -> nequi.mostrarSaldoDisponible()
            3 -> nequi.sacar()
            4 -> nequi.enviar()
            5 -> nequi.recargar()
            6 -> nequi.salir()
            else -> println("Esta opción no es válida")
        }
    }
}