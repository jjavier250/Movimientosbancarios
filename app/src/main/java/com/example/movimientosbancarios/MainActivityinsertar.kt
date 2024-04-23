package com.example.movimientosbancarios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.movimientosbancarios.databinding.ActivityMainActivityinsertarBinding
import com.example.movimientosbancarios.provider.Movimientos
import com.example.movimientosbancarios.provider.MovimientosDao
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

class MainActivityinsertar : AppCompatActivity() {

    private lateinit var binding:ActivityMainActivityinsertarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var movimientosDao = MovimientosDao(this)

        binding = ActivityMainActivityinsertarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fechaActual = LocalDate.now()
        val formato = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        val fechaFormateada = fechaActual.format(formato)

        binding.txtfecha.setText(fechaFormateada)

        binding.btngrabatarea.setOnClickListener(){

            if(!binding.txtcantidad.text.toString().isEmpty()){

                var cantidad:Int
                val cantidadTexto = binding.txtcantidad.text.toString()
                cantidad = cantidadTexto.toInt()

                var movimientos: Movimientos = Movimientos(-1, cantidad, binding.txtfecha.text.toString(),binding.txtdescripcion.text.toString())
                movimientosDao.insert(movimientos)
                Log.i("insertar","Insertado en base de datos")

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