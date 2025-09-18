package com.example.ventaexpresse.ui.products

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ventaexpresse.R
import com.google.android.material.appbar.MaterialToolbar

class ProductDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NOMBRE = "extra_nombre"
        const val EXTRA_DESC   = "extra_desc"
        const val EXTRA_PRECIO = "extra_precio"
        const val EXTRA_IMAGEN = "extra_imagen"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        // 👉 Toolbar con “atrás”
        val toolbar = findViewById<MaterialToolbar?>(R.id.topAppBar)
        toolbar?.let {
            setSupportActionBar(it)
            it.setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed() // vuelve a la lista
            }
        } ?: run {
            // Si no tienes MaterialToolbar en el layout, al menos muestra el "up" de la action bar
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val nombre = intent.getStringExtra(EXTRA_NOMBRE) ?: ""
        val desc   = intent.getStringExtra(EXTRA_DESC) ?: ""
        val precio = intent.getDoubleExtra(EXTRA_PRECIO, 0.0)
        val imagen = intent.getStringExtra(EXTRA_IMAGEN) ?: ""

        val tvTitle: TextView = findViewById(R.id.tvDetailTitle)
        val tvDesc: TextView  = findViewById(R.id.tvDetailDesc)
        val tvPrice: TextView = findViewById(R.id.tvDetailPrice)
        val ivImg: ImageView  = findViewById(R.id.ivDetailImg)

        tvTitle.text = nombre
        tvDesc.text  = desc
        tvPrice.text = "$ " + String.format("%.2f", precio)

        val resId = resources.getIdentifier(imagen.substringBeforeLast("."), "drawable", packageName)
        if (resId != 0) ivImg.setImageResource(resId)
        else ivImg.setImageResource(android.R.drawable.ic_menu_report_image)
    }

    // Para que el botón “up” de la ActionBar (si se usa) también funcione
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
