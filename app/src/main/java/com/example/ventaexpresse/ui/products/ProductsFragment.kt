package com.example.ventaexpresse.ui.products

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ventaexpresse.R
import com.example.ventaexpresse.data.Product
import com.example.ventaexpresse.data.ProductsSeed
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.ventaexpresse.LoginActivity

class ProductsFragment : Fragment() {

    private val items = mutableListOf<Product>()
    private lateinit var rv: RecyclerView
    private lateinit var adapter: ProductsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.fragment_products, container, false)

        rv = v.findViewById(R.id.rvProducts)
        rv.layoutManager = GridLayoutManager(requireContext(), 2)
        rv.addItemDecoration(object : RecyclerView.ItemDecoration() {
            private val space = (8 * resources.displayMetrics.density).toInt()
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                outRect.set(space, space, space, space)
            }
        })

        adapter = ProductsAdapter(items) { product -> openDetails(product) }
        rv.adapter = adapter

        // Carga local inmediata
        items.clear()
        items.addAll(ProductsSeed.items)
        adapter.notifyDataSetChanged()

        // FAB: volver al Login (limpia back stack)
        v.findViewById<FloatingActionButton>(R.id.fabBackLogin)?.apply {
            visibility = View.VISIBLE
            setOnClickListener {
                val intent = Intent(requireContext(), LoginActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                }
                startActivity(intent)
            }
        }

        return v
    }

    private fun openDetails(p: Product) {
        val i = Intent(requireContext(), ProductDetailActivity::class.java).apply {
            putExtra(ProductDetailActivity.EXTRA_NOMBRE, p.nombre)
            putExtra(ProductDetailActivity.EXTRA_DESC, p.descripcion)
            putExtra(ProductDetailActivity.EXTRA_PRECIO, p.precio)
            putExtra(ProductDetailActivity.EXTRA_IMAGEN, p.imagenAsset)
        }
        startActivity(i)
    }
}

private class ProductsAdapter(
    private val data: List<Product>,
    private val onView: (Product) -> Unit
) : RecyclerView.Adapter<ProductsVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsVH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductsVH(v, onView)
    }

    override fun onBindViewHolder(holder: ProductsVH, position: Int) = holder.bind(data[position])

    override fun getItemCount(): Int = data.size
}

private class ProductsVH(
    v: View,
    private val onView: (Product) -> Unit
) : RecyclerView.ViewHolder(v) {

    private val tvTitle: TextView = v.findViewById(R.id.tvTitle)
    private val tvDesc: TextView  = v.findViewById(R.id.tvDesc)
    private val tvPrice: TextView = v.findViewById(R.id.tvPrice)
    private val ivImg: ImageView  = v.findViewById(R.id.ivImg)
    private val btnDetails: Button? = v.findViewById(R.id.btnDetails) // si existe en el layout

    fun bind(p: Product) {
        tvTitle.text = p.nombre
        tvDesc.text  = p.descripcion
        tvPrice.text = "$ " + String.format("%.2f", p.precio)

        val ctx = itemView.context
        val nameNoExt = p.imagenAsset.substringBeforeLast(".")
        val resId = ctx.resources.getIdentifier(nameNoExt, "drawable", ctx.packageName)
        if (resId != 0) ivImg.setImageResource(resId)
        else ivImg.setImageResource(android.R.drawable.ic_menu_report_image)

        // Acciones para abrir el detalle
        itemView.setOnClickListener { onView(p) }
        ivImg.setOnClickListener     { onView(p) }
        btnDetails?.setOnClickListener { onView(p) }
    }
}
