package com.example.movimientosbancarios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movimientosbancarios.adapter.MovimientosAdapter
import com.example.movimientosbancarios.provider.Movimientos
import com.example.movimientosbancarios.provider.MovimientosDao
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    lateinit var btnpaninsertar: FloatingActionButton
    lateinit var listaAgenda: MutableList<Movimientos>
    lateinit var recyclerView: RecyclerView
    lateinit var adapter:MovimientosAdapter
    lateinit var txtingresos:TextView
    lateinit var txtgastos:TextView
    lateinit var balance:TextView
    lateinit var btnborrar:ImageButton

    //lateinit var adapter:AgendaAdapter

    val movimientosDao = MovimientosDao(this)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        btnpaninsertar=findViewById(R.id.btnpaninsertar)
        txtingresos=findViewById(R.id.txtingresos)
        txtgastos=findViewById(R.id.txtgastos)
        balance=findViewById(R.id.balance)
        btnborrar=findViewById(R.id.btnborrar)

    }

    override fun onResume() {
        super.onResume()

        listaAgenda = movimientosDao.findAll().toMutableList()

        inicio()
    }

    private fun inicio() {

        var ingresos:Int=0
        var gastos:Int=0
        var total:Int=0

        for (elemento in listaAgenda) {
            if(elemento.cantidad>0){
                ingresos=ingresos + elemento.cantidad

            }
            else{
                gastos=gastos + elemento.cantidad

            }
        }
        total=(ingresos)+(gastos)

        txtingresos.text="Total ingresos: " + ingresos.toString()
        txtgastos.text="Total gastos: " + gastos.toString()
        balance.text="Balance: " + total.toString()

        adapter = MovimientosAdapter(listaAgenda, { llamarPantallaClick(it) },{llamarPapeleraClick(it)})

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        btnpaninsertar.setOnClickListener(){
            val intent = Intent(this,MainActivityinsertar::class.java)
            startActivity(intent)
        }

        btnborrar.setOnClickListener(){



            // Crear AlertDialog
            val builder = AlertDialog.Builder(this)

            // Establecer el título y el mensaje
            builder.setTitle("Borrar datos")
            builder.setMessage("¿Esta seguro que desea eliminar todo?")

            builder.setPositiveButton("Aceptar") { dialog, which ->
                // Acción a realizar cuando se presiona el botón Aceptar

                movimientosDao.deleteAll()
                Toast.makeText(this, "todo borrado", Toast.LENGTH_SHORT).show()
                onResume()

                dialog.dismiss()
            }

            // Agregar botón negativo
            builder.setNegativeButton("Cancelar") { dialog, which ->
                // Acción a realizar cuando se presiona el botón Cancelar
                // Por ejemplo, puedes realizar alguna acción o cerrar el diálogo
                dialog.dismiss()
            }

            // Mostrar AlertDialog
            val dialog: AlertDialog = builder.create()
            dialog.show()


        }

    }

    private fun llamarPapeleraClick(it: Int) {
        // Crear AlertDialog
        val builder = AlertDialog.Builder(this)

        // Establecer el título y el mensaje
        builder.setTitle("Eleminar registro")
        builder.setMessage("¿Esta seguro que desea eliminar esta linea?")
        builder.setPositiveButton("Aceptar") { dialog, which ->
            // Acción a realizar cuando se presiona el botón Aceptar

            var movimientos: Movimientos = listaAgenda[it]
            movimientosDao.delete(movimientos)
            listaAgenda.removeAt(it)
            adapter.notifyDataSetChanged()

            Toast.makeText(this, "borrado ok", Toast.LENGTH_SHORT).show()
            onResume()

            dialog.dismiss()
        }

        // Agregar botón negativo
        builder.setNegativeButton("Cancelar") { dialog, which ->
            // Acción a realizar cuando se presiona el botón Cancelar
            // Por ejemplo, puedes realizar alguna acción o cerrar el diálogo
            dialog.dismiss()
        }

        // Mostrar AlertDialog
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    private fun llamarPantallaClick(position: Int) {

        var movimientos: Movimientos = listaAgenda[position]

        val intent = Intent(this, MainActivityModificar::class.java)
        intent.putExtra("ID_TABLA",movimientos.id)
        startActivity(intent)

    }


}