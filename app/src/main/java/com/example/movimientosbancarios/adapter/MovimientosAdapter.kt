package com.example.movimientosbancarios.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movimientosbancarios.R
import com.example.movimientosbancarios.provider.Movimientos


class MovimientosAdapter(private var dataSet: List<Movimientos> =listOf(), val onClickListener:(Int)->Unit) :
        RecyclerView.Adapter<MovimientosAdapter.MiViewHolder>() {


        class MiViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val cantidad: TextView
            val fecha: TextView



            init {
                // hace referencia el textview que esta en item_agenda saco 3 columnas pero mas abajo pinto 2 de la BD
                cantidad = view.findViewById(R.id.txtcantidad)
                fecha=view.findViewById(R.id.txtfecha)


            }
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MiViewHolder {

            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.item_movimientos, viewGroup, false) // item_tarea hace referencia al xml de item_tarea.xml

            return MiViewHolder(view)
        }

        override fun onBindViewHolder(viewHolder: MiViewHolder, position: Int) {

            // En el MainActivity recogemos las llamdas así :   adapter = AgendaAdapter(listaAgenda, { llamarPantallaClick(it) }, {llamarPapeleraClick(it)})
            viewHolder.itemView.setOnClickListener{onClickListener(position)} // capturamos el click del control



            //si es el de la sesion el favorito le cambio la linea de color
            //if(dataSet[position].nombre.toString()==sesion.leerDatosSesion())
           // {
                viewHolder.itemView.setBackgroundColor(Color.CYAN)
           // }

            if (dataSet[position].cantidad<0){
                viewHolder.itemView.setBackgroundColor(Color.RED)
            }
            else
            {
                viewHolder.itemView.setBackgroundColor(Color.GREEN)
            }


            val movimientos:Movimientos = dataSet[position]

            // los 2 textview del layaut item_agenda le asigno los valores

            viewHolder.cantidad.text =movimientos.cantidad.toString()
            viewHolder.fecha.text=movimientos.fecha


        }


        override fun getItemCount() = dataSet.size

        fun updateData(list:List<Movimientos>){
            dataSet=list
            notifyDataSetChanged()
        }

    }
