package com.example.ventaexpresse.data

data class Product(
    val id: String = "",
    val nombre: String = "",
    val marca: String = "",
    val precio: Double = 0.0,
    val stock: Int = 0,
    val descripcion: String = "",
    val imagenAsset: String = "" // path or asset name
)

object ProductsSeed {
    val items = listOf(
        Product(id = "seed-001", nombre = "refrigeradoraLG", marca = "LG", precio = 899.99, stock = 10, descripcion = "r", imagenAsset = "refrigeradora1"),
        Product(id = "seed-002", nombre = "refrigeradoraSAMSUNG", marca = "samsung", precio = 1299.99, stock = 10, descripcion = "r", imagenAsset = "refrigeradora2"),
        Product(id = "seed-003", nombre = "refrigeradoraMABE", marca = "mabe", precio = 999.99, stock = 10, descripcion = "r", imagenAsset = "refrigeradora3"),
        Product(id = "seed-004", nombre = "cocinaMABE", marca = "mabe", precio = 599.99, stock = 10, descripcion = "c", imagenAsset = "cocina1"),
        Product(id = "seed-005", nombre = "cocinaMABE", marca = "mabe", precio = 399.99, stock = 10, descripcion = "c", imagenAsset = "cocina2"),
        Product(id = "seed-006", nombre = "cocinaOSTER", marca = "oster", precio = 499.99, stock = 10, descripcion = "c", imagenAsset = "cocina3"),
        Product(id = "seed-007", nombre = "hornotostadorAXEL", marca = "axel", precio = 39.99, stock = 10, descripcion = "horno tostador elé", imagenAsset = "hornotostador1"),
        Product(id = "seed-008", nombre = "hornotostadorSTAR", marca = "star", precio = 49.99, stock = 10, descripcion = "horno tostador elé", imagenAsset = "hornotostador2"),
        Product(id = "seed-009", nombre = "hornotostadorOSTER", marca = "oster", precio = 44.99, stock = 10, descripcion = "horno tostador elé", imagenAsset = "hornotostador2"),
        Product(id = "seed-010", nombre = "licuadoraOSTER", marca = "oster", precio = 59.99, stock = 10, descripcion = "licuadora de 1.", imagenAsset = "licuadora1"),
        Product(id = "seed-011", nombre = "licuadora", marca = "black & decke", precio = 38.99, stock = 10, descripcion = "l", imagenAsset = "licuadora2"),
        Product(id = "seed-012", nombre = "licuadora", marca = "westinghouse", precio = 29.99, stock = 10, descripcion = "l", imagenAsset = "licuadora3"),
        Product(id = "seed-013", nombre = "hornomicroondas", marca = "samsung", precio = 125.0, stock = 10, descripcion = "h", imagenAsset = "hornomicroondas1"),
        Product(id = "seed-014", nombre = "hornomicroondas", marca = "LG", precio = 99.99, stock = 10, descripcion = "h", imagenAsset = "hornomicroondas2"),
        Product(id = "seed-015", nombre = "hornomicroondas", marca = "panasonic", precio = 89.99, stock = 10, descripcion = "h", imagenAsset = "hornomicroondas3"),
        Product(id = "seed-016", nombre = "lavadora", marca = "samsung", precio = 899.99, stock = 10, descripcion = "lavadora incluye lavado +", imagenAsset = "lavadora1"),
        Product(id = "seed-017", nombre = "lavadora", marca = "hisense", precio = 699.99, stock = 10, descripcion = "lavadora secadora 10/", imagenAsset = "lavadora2"),
        Product(id = "seed-018", nombre = "lavadora", marca = "haier", precio = 499.99, stock = 10, descripcion = "l", imagenAsset = "lavadora3"),
        Product(id = "seed-019", nombre = "extractordejugo", marca = "moulinex", precio = 37.99, stock = 10, descripcion = "e", imagenAsset = "extractordejugo2"),
        Product(id = "seed-020", nombre = "extractordegrasa", marca = "midea", precio = 99.99, stock = 10, descripcion = "extractordegrasa Sistema de extracció", imagenAsset = "extractordegrasa1"),
        Product(id = "seed-021", nombre = "extractordegrasa", marca = "teka", precio = 89.99, stock = 10, descripcion = "e", imagenAsset = "extractordegrasa2"),
        Product(id = "seed-022", nombre = "extractordegrasa", marca = "drija", precio = 119.99, stock = 10, descripcion = "extractordegrasa 3 Velocidades con botó", imagenAsset = "extractordegrasa2"),
        Product(id = "seed-023", nombre = "ventilador", marca = "Basic Living", precio = 36.5, stock = 10, descripcion = "ventilador de pedestal 45.", imagenAsset = "ventilador1"),
        Product(id = "seed-024", nombre = "ventilador", marca = "basic living", precio = 69.0, stock = 10, descripcion = "v", imagenAsset = "ventilador2"),
        Product(id = "seed-025", nombre = "ventilador", marca = "westinchouse", precio = 49.99, stock = 10, descripcion = "ventilador de piso 50.8cm 20 pulgadas color negro", imagenAsset = "ventilador3")
    )
}