
package com.example.ventaexpresse.data.model

data class Product(
    val id: String = "",
    val nombre: String = "",
    val marca: String = "",
    val precio: Double = 0.0,
    val stock: Int = 0,
    val descripcion: String = "",
    val imagenAsset: String = "" // nombre del drawable, ej: "refrigeradora1"
)

data class Client(
    val id: String = "",
    val nombre: String = "",
    val correo: String = "",
    val telefono: String = ""
)

data class SaleProduct(val productId: String = "", val cantidad: Int = 1)
data class Sale(
    val id: String = "",
    val clienteId: String = "",
    val productos: List<SaleProduct> = emptyList(),
    val total: Double = 0.0,
    val fecha: Long = System.currentTimeMillis()
)
