package com.example.ventaexpresse.data.cart

import com.example.ventaexpresse.data.Product

object Cart {
    // Usamos imagenAsset como ID (único por producto)
    private val items = linkedMapOf<String, Pair<Product, Int>>()

    fun isInCart(p: Product) = items.containsKey(p.imagenAsset)
    fun qty(p: Product) = items[p.imagenAsset]?.second ?: 0

    fun addOne(p: Product) {
        val current = items[p.imagenAsset]
        if (current == null) items[p.imagenAsset] = p to 1
        else items[p.imagenAsset] = p to (current.second + 1)
    }

    fun removeOne(p: Product) {
        val current = items[p.imagenAsset] ?: return
        val q = current.second - 1
        if (q <= 0) items.remove(p.imagenAsset) else items[p.imagenAsset] = p to q
    }

    fun removeAll(p: Product) { items.remove(p.imagenAsset) }

    fun totalItems(): Int = items.values.sumOf { it.second }
    fun totalPrice(): Double = items.values.sumOf { it.first.precio * it.second }

    fun clear() = items.clear()
}
