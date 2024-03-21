package com.example.movimientosbancarios.provider

import com.example.agenda.activities.utils.DatabaseManager

class Movimientos (var id: Int, var cantidad:Int, var fecha:String) {

    companion object {
        const val TABLE_NAME = "movimientos"
        const val COLUMN_NAME_CANTIDAD = "cantidad"
        const val COLUMN_NAME_FECHA = "fecha"


        val COLUMN_NAMES = arrayOf(
            DatabaseManager.COLUMN_NAME_ID,
            COLUMN_NAME_CANTIDAD,
            COLUMN_NAME_FECHA

        )
    }

    //Para imprimir
    override fun toString(): String {
        return "imprimir"
    }
}