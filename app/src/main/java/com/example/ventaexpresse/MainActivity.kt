
package com.example.ventaexpresse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.example.ventaexpresse.ui.clients.ClientsFragment
import com.example.ventaexpresse.ui.products.ProductsFragment
import com.example.ventaexpresse.ui.sales.SalesFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "VentaExpress - " + (FirebaseAuth.getInstance().currentUser?.email ?: "")

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_products -> show(ProductsFragment())
                R.id.nav_clients -> show(ClientsFragment())
                R.id.nav_sales -> show(SalesFragment())
            }
            true
        }
        bottomNav.selectedItemId = R.id.nav_products
    }

    private fun show(f: androidx.fragment.app.Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, f)
            .commit()
    }
}
