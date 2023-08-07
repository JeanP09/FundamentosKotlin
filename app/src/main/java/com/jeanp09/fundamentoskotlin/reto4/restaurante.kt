package com.jeanp09.fundamentoskotlin.reto4

data class Plato(var codigo: Int, val nombre: String, val descripcion: String, val precio: Double)

data class Categoria(val nombre: String, val platos: MutableList<Plato> = mutableListOf())

class MenuRestaurante {
    private val categorias = listOf(
        Categoria("Entradas", mutableListOf(
            Plato(1, "Ensalada César", "Lechuga, croutones, pollo y aderezo.", 8999.9),
            Plato(2, "Bruschetta", "Pan tostado con tomate y albahaca", 6999.9),
            Plato(3, "Carpaccio", "Finas lonchas de carne con aderezo", 10999.9)
        )),
        Categoria("Platos Fuertes", mutableListOf(
            Plato(101, "Pasta Alfredo", "Pasta con salsa Alfredo y pollo", 12999.9),
            Plato(102, "Carne Asada", "Carne asada con guarnición", 15999.9),
            Plato(103, "Salmón a la Parrilla", "Salmón con vegetales asados", 18999.9)
        )),
        Categoria("Postres", mutableListOf(
            Plato(201, "Tarta de Chocolate", "Deliciosa tarta de chocolate", 6999.9),
            Plato(202, "Helado de Vainilla", "Helado de vainilla con toppings", 4999.9),
            Plato(203, "Cheesecake", "Pastel de queso con salsa de frutos rojos", 7999.9)
        )),
        Categoria("Bebidas", mutableListOf(
            Plato(301, "Refresco", "Refresco de cola", 2499.9),
            Plato(302, "Agua Mineral", "Agua mineral sin gas", 1999.9),
            Plato(303, "Café", "Café negro o con leche", 3499.9)
        ))
    )

    fun obtenerNuevoCodigo(): Int {
        return categorias.flatMap { it.platos }.maxByOrNull { it.codigo }?.codigo?.plus(1) ?: 1
    }

    fun agregarPlato(categoria: String, plato: Plato) {
        val categoriaEncontrada = categorias.find { it.nombre == categoria }
        plato.codigo = obtenerNuevoCodigo()
        categoriaEncontrada?.platos?.add(plato)
    }

    fun mostrarPlatos() {
        for (categoria in categorias) {
            println("${categoria.nombre}:")
            for (plato in categoria.platos) {
                println(plato)
            }
        }
    }

    fun buscarPlatoPorCodigo(codigo: Int): Plato? {
        for (categoria in categorias) {
            for (plato in categoria.platos) {
                if (plato.codigo == codigo) {
                    return plato
                }
            }
        }
        return null
    }

    fun modificarPlato(plato: Plato) {
        for (categoria in categorias) {
            for (i in 0 until categoria.platos.size) {
                if (categoria.platos[i].codigo == plato.codigo) {
                    categoria.platos[i] = plato
                    return
                }
            }
        }
        println("Plato no encontrado.")
    }

    fun eliminarPlato(codigo: Int) {
        for (categoria in categorias) {
            categoria.platos.removeAll { it.codigo == codigo }
        }
    }
}

fun main() {
    val menu = MenuRestaurante()

    var continuar = true
    while (continuar) {
        println("Menú del restaurante:\n" +
                "1. Agregar plato\n" +
                "2. Mostrar todos los platos\n" +
                "3. Mostrar plato por código\n" +
                "4. Modificar un plato\n" +
                "5. Eliminar un plato\n" +
                "6. Salir\n" +
                "Escriba el número de lo que desea hacer: ")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                print("Escriba el nombre del plato: ")
                val nombre = readLine() ?: ""
                print("Escriba la descripción del plato: ")
                val descripcion = readLine() ?: ""
                print("Escriba el precio del plato: ")
                val precio = readLine()?.toDoubleOrNull() ?: 0.0

                println("Categorías:")
                menu.mostrarPlatos()
                print("Escriba el nombre de la categoría donde agregar el plato: ")
                val categoria = readLine() ?: ""

                menu.agregarPlato(categoria, Plato(0, nombre, descripcion, precio))
                println("Se ha agregado el plato.")
            }
            2 -> {
                menu.mostrarPlatos()
            }
            3 -> {
                print("Escriba el código del plato: ")
                val codigo = readLine()?.toIntOrNull() ?: 0
                val plato = menu.buscarPlatoPorCodigo(codigo)
                if (plato != null) {
                    println("Este plato no se encontró: $plato")
                } else {
                    println("Este plato no se encontró.")
                }
            }
            4 -> {
                print("Escriba el código del plato que quiere modificar: ")
                val codigo = readLine()?.toIntOrNull() ?: 0
                val plato = menu.buscarPlatoPorCodigo(codigo)
                if (plato != null) {
                    print("Escriba el nuevo nombre del plato: ")
                    val nombre = readLine() ?: ""
                    print("Escriba la nueva descripción del plato: ")
                    val descripcion = readLine() ?: ""
                    print("Escriba el nuevo valor del plato: ")
                    val precio = readLine()?.toDoubleOrNull() ?: 0.0

                    menu.modificarPlato(Plato(codigo, nombre, descripcion, precio))
                    println("Se modificó el plato.")
                } else {
                    println("Este plato no se encontró.")
                }
            }
            5 -> {
                print("Escriba el código del plato que quiere eliminar: ")
                val codigo = readLine()?.toIntOrNull() ?: 0
                menu.eliminarPlato(codigo)
                println("Se eliminó el plato.")
            }
            6 -> {
                continuar = false
            }
            else -> println("Esta opción no es válida.")
        }
        println()
    }
}