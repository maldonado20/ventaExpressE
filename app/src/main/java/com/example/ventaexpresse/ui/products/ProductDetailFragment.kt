package com.example.ventaexpresse.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.ventaexpresse.R
import com.example.ventaexpresse.data.Product
import com.example.ventaexpresse.data.cart.Cart
import com.google.android.material.snackbar.Snackbar

class ProductDetailFragment : Fragment() {

    companion object {
        private const val ARG_NAME = "name"
        private const val ARG_DESC = "desc"
        private const val ARG_PRICE = "price"
        private const val ARG_IMG = "img"

        fun from(p: Product) = ProductDetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_NAME, p.nombre)
                putString(ARG_DESC, p.descripcion)
                putDouble(ARG_PRICE, p.precio)
                putString(ARG_IMG, p.imagenAsset)
            }
        }
    }

    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = Product(
            nombre = requireArguments().getString(ARG_NAME, ""),
            descripcion = requireArguments().getString(ARG_DESC, ""),
            precio = requireArguments().getDouble(ARG_PRICE, 0.0),
            imagenAsset = requireArguments().getString(ARG_IMG, "")
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_product_detail, container, false)

        val iv = v.findViewById<ImageView>(R.id.ivImg)
        val tvTitle = v.findViewById<TextView>(R.id.tvTitle)
        val tvPrice = v.findViewById<TextView>(R.id.tvPrice)
        val tvDesc  = v.findViewById<TextView>(R.id.tvDesc)
        val btnMinus = v.findViewById<Button>(R.id.btnMinus)
        val btnPlus  = v.findViewById<Button>(R.id.btnPlus)
        val tvQty    = v.findViewById<TextView>(R.id.tvQty)
        val btnCart  = v.findViewById<Button>(R.id.btnCart)
        val btnBuy   = v.findViewById<Button>(R.id.btnBuy)

        val ctx = requireContext()
        val resId = resources.getIdentifier(product.imagenAsset.substringBeforeLast("."), "drawable", ctx.packageName)
        if (resId != 0) iv.setImageResource(resId)

        tvTitle.text = product.nombre
        tvPrice.text = "$ " + String.format("%.2f", product.precio)
        tvDesc.text  = product.descripcion

        fun refreshCartUI() {
            val q = Cart.qty(product)
            tvQty.text = q.toString()
            btnCart.text = if (q > 0) "Quitar del carrito" else "Añadir al carrito"
        }
        refreshCartUI()

        btnPlus.setOnClickListener { Cart.addOne(product); refreshCartUI() }
        btnMinus.setOnClickListener { Cart.removeOne(product); refreshCartUI() }
        btnCart.setOnClickListener {
            if (Cart.isInCart(product)) Cart.removeAll(product) else Cart.addOne(product)
            refreshCartUI()
            Snackbar.make(v, "Carrito: ${Cart.totalItems()} — $ ${String.format("%.2f", Cart.totalPrice())}", Snackbar.LENGTH_SHORT).show()
        }
        btnBuy.setOnClickListener {
            val q = Cart.qty(product).coerceAtLeast(1)
            Cart.removeAll(product)
            refreshCartUI()
            Snackbar.make(v, "¡Compra realizada! $q × ${product.nombre}", Snackbar.LENGTH_LONG).show()
        }

        return v
    }
}
