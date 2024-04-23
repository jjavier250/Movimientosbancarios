package com.example.movimientosbancarios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.movimientosbancarios.databinding.ActivityMainModificarBinding
import com.example.movimientosbancarios.provider.Movimientos
import com.example.movimientosbancarios.provider.MovimientosDao

class MainActivityModificar : AppCompatActivity() {
    lateinit var movimientos: Movimientos
    private lateinit var binding:ActivityMainModificarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movimientosDao = MovimientosDao(this)

        binding= ActivityMainModificarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var id: Int = intent.getIntExtra("ID_TABLA", -1)
        Log.i("buscar","recojo valor de la pantalla anterior")
        movimientos = movimientosDao.find(id)!!

        //pintamos lo que nos ha devuelto LA BD en los edittext
        binding.txtcantidad.setText(movimientos.cantidad.toString())
        binding.txtfecha.setText(movimientos.fecha)
        binding.txtdescripcion.setText(movimientos.desc)



        binding.btngrabar.setOnClickListener(){
            var pasa:Boolean=false


            if(!binding.txtcantidad.text.toString().isEmpty()) {

                var cantidad:Int
                val cantidadTexto = binding.txtcantidad.text.toString()
                cantidad = cantidadTexto.toInt()

                movimientos.cantidad = cantidad
                movimientos.fecha=binding.txtfecha.text.toString()
                movimientos.desc=binding.txtdescripcion.text.toString()

                movimientosDao.update(movimientos)

                finish()

            }
            else{
                Toast.makeText(this, "Indique un valor para la cantidad", Toast.LENGTH_SHORT).show()
            }



            }





        binding.btncancelar.setOnClickListener(){
            finish()
        }

    }



}