package com.jeanp09.fundamentoskotlin.reto2

data class Cancion(val titulo: String, val artista: String, val publicacion: String, val reproducciones: Int, val tipoMusica: String)

fun main() {
    val listaAlbum: MutableList<Cancion> = mutableListOf()

    verCanciones(listaAlbum, Cancion("How", "The Neighbourhood", "22/04/2013", 1870, "Alternative Rock"))
    verCanciones(listaAlbum, Cancion("Afraid", "The Neighbourhood", "23/07/2013", 15886, "Dark Pop"))
    verCanciones(listaAlbum, Cancion("Everybody's Watching Me", "The Neighbourhood", "22/04/2013", 2315, "Rock"))
    verCanciones(listaAlbum, Cancion("Sweater Weather", "The Neighbourhood", "28/03/2013", 62952, "Dream Pop, Neo-Psychedelia"))
    verCanciones(listaAlbum, Cancion("Let It Go", "The Neighbourhood", "22/11/2012", 2527, "Rock"))
    verCanciones(listaAlbum, Cancion("Alleyways", "The Neighbourhood", "22/04/2013", 3343, "Rock"))
    verCanciones(listaAlbum, Cancion("W.D.Y.W.F.M.?", "The Neighbourhood", "22/04/2013", 6740,"Alternative Rock"))
    verCanciones(listaAlbum, Cancion("Flawless", "The Neighbourhood", "22/04/2013", 10364, "Rock"))
    verCanciones(listaAlbum, Cancion("Female Robbery", "The Neighbourhood", "08/10/2013", 4187, "Rock"))
    verCanciones(listaAlbum, Cancion("Staying Up", "The Neighbourhood", "22/04/2013", 1669, "Dark Pop"))
    verCanciones(listaAlbum, Cancion("A Little Death", "The Neighbourhood", "17/12/2013", 8167, "R&B"))
    verCanciones(listaAlbum, Cancion("Float", "The Neighbourhood", "22/04/2013", 1529, "Alternative Rock"))

    while (true) {
        println("Ecoge una opción:\n" +
                "1. Cuantas canciones hay\n" +
                "2. Tipo de música\n" +
                "3. Mostrar título, artista, publicación y reproducciones\n" +
                "4. Mostrar la canción más popular del álbum por mayor reproducciones\n" +
                "5. Mostrar la cantidad de reproducciones y evaluar las canciones como más popular a poco popular del álbum\n" +
                "0. Salir")

        val opcion = readLine()?.toInt() ?: 0

        when (opcion) {
            0 -> break
            1 -> println("El total de las canciones es: ${listaAlbum.size}")
            2 -> {
                println("El tipo de música de cada canción es:")
                for (cancion in listaAlbum) {
                    println("${cancion.titulo} - ${cancion.tipoMusica}")
                }
            }
            3 -> {
                println("Los detalles de las canciones son:")
                for (cancion in listaAlbum) {
                    println("Título: ${cancion.titulo}")
                    println("Artista: ${cancion.artista}")
                    println("Fecha de publicación: ${cancion.publicacion}")
                    println("Número de reproducciones: ${cancion.reproducciones}")
                    println("Tipo de música: ${cancion.tipoMusica}\n")
                }
            }
            4 -> {
                val cancionMasPopular = listaAlbum.maxByOrNull { it.reproducciones }
                if (cancionMasPopular != null) {
                    println("La canción más popular es:")
                    println("Título: ${cancionMasPopular.titulo}")
                    println("Artista: ${cancionMasPopular.artista}")
                    println("Publicación: ${cancionMasPopular.publicacion}")
                    println("Reproducciones: ${cancionMasPopular.reproducciones}")
                    println("Tipo de música: ${cancionMasPopular.tipoMusica}")
                }
            }
            5 -> {
                println("Cantidad de reproducciones y popularidad de cada canción:")
                for (cancion in listaAlbum) {
                    println("Título: ${cancion.titulo}")
                    println("Artista: ${cancion.artista}")
                    println("Publicación: ${cancion.publicacion}")
                    println("Reproducciones: ${cancion.reproducciones}")
                    val popularidad = if (cancion.reproducciones >= 2000) "Más popular" else "Poco popular"
                    println("Popularidad: $popularidad\n")
                }
            }
            else -> println("Opción no válida")
        }
    }
}

fun verCanciones(lista: MutableList<Cancion>, cancion: Cancion) {
    lista.add(cancion)
}