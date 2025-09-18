
package com.example.ventaexpresse.data.repo

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.example.ventaexpresse.data.model.Client
import com.example.ventaexpresse.data.model.Product
import com.example.ventaexpresse.data.model.Sale

class FirebaseRepo {
    private val db = Firebase.database.reference
    private val uid get() = FirebaseAuth.getInstance().currentUser?.uid ?: "nouid"
    fun productosRef() = db.child("users").child(uid).child("productos")
    fun clientesRef()  = db.child("users").child(uid).child("clientes")
    fun ventasRef()    = db.child("users").child(uid).child("ventas")
}
