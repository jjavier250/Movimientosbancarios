package com.example.movimientosbancarios.provider

import com.example.agenda.activities.utils.DatabaseManager

class Movimientos (var id: Int, var cantidad:Int, var fecha:String,var desc:String) {

    companion object {
        const val TABLE_NAME = "movimientos"
        const val COLUMN_NAME_CANTIDAD = "cantidad"
        const val COLUMN_NAME_FECHA = "fecha"
        const val COLUMN_NAME_DESC = "descripcion"


        val COLUMN_NAMES = arrayOf(
            DatabaseManager.COLUMN_NAME_ID,
            COLUMN_NAME_CANTIDAD,
            COLUMN_NAME_FECHA,
            COLUMN_NAME_DESC

        )
    }

    //Para imprimir
    override fun toString(): String {
        return "imprimir"
    }
}