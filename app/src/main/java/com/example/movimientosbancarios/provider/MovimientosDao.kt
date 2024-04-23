package com.example.movimientosbancarios.provider

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.example.agenda.activities.utils.DatabaseManager

class MovimientosDao(context: Context) {

    private var databaseManager: DatabaseManager = DatabaseManager(context)

    fun insert(movimientos: Movimientos): Movimientos {
        val db = databaseManager.writableDatabase

        var values = ContentValues()
        values.put(Movimientos.COLUMN_NAME_CANTIDAD, movimientos.cantidad)
        values.put(Movimientos.COLUMN_NAME_FECHA,movimientos.fecha )
        values.put(Movimientos.COLUMN_NAME_DESC,movimientos.desc )


        var newRowId = db.insert(Movimientos.TABLE_NAME, null, values)
        Log.i("DATABASE", "nuevo id: $newRowId")

        db.close()

        movimientos.id= newRowId.toInt()
        return movimientos
    }

    fun update(movimientos: Movimientos) {
        val db = databaseManager.writableDatabase

        var values = ContentValues()
        values.put(Movimientos.COLUMN_NAME_CANTIDAD,movimientos.cantidad)
        values.put(Movimientos.COLUMN_NAME_FECHA,movimientos.fecha)
        values.put(Movimientos.COLUMN_NAME_DESC,movimientos.desc)



        var updatedRows = db.update(Movimientos.TABLE_NAME, values, "${DatabaseManager.COLUMN_NAME_ID} = ${movimientos.id}", null)
        Log.i("DATABASE", "Updated records: $updatedRows")

        db.close()
    }

    fun delete(movimientos: Movimientos) {
        val db = databaseManager.writableDatabase

        val deletedRows = db.delete(Movimientos.TABLE_NAME, "${DatabaseManager.COLUMN_NAME_ID} = ${movimientos.id}", null)
        Log.i("DATABASE", "Delete lineas: $deletedRows")

        db.close()
    }

    fun deleteAll() {
        val db = databaseManager.writableDatabase

        val deletedRows = db.delete(Movimientos.TABLE_NAME, null, null)
        Log.i("DATABASE", "Todo borrado")

        db.close()
    }



    @SuppressLint("Range")
    fun find(id: Int): Movimientos? {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Movimientos.TABLE_NAME,
            Movimientos.COLUMN_NAMES,
            "${DatabaseManager.COLUMN_NAME_ID} = $id",
            null,
            null,
            null,
            null
        )

        var movimientos: Movimientos? = null

        if (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_ID))
            val cantidad = cursor.getInt(cursor.getColumnIndex(Movimientos.COLUMN_NAME_CANTIDAD))
            val fecha = cursor.getString(cursor.getColumnIndex(Movimientos.COLUMN_NAME_FECHA))
            val desc=cursor.getString(cursor.getColumnIndex(Movimientos.COLUMN_NAME_DESC))

            movimientos = Movimientos(id, cantidad,fecha,desc)
        }

        cursor.close()
        db.close()

        return movimientos
    }

    @SuppressLint("Range")
    fun findAll(): List<Movimientos> {
        val db = databaseManager.writableDatabase

        val cursor = db.query(
            Movimientos.TABLE_NAME,
            Movimientos.COLUMN_NAMES,
            null,
            null,
            null,
            null,
            null
        )

        var list: MutableList<Movimientos> = mutableListOf()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_ID))
            val cantidad = cursor.getInt(cursor.getColumnIndex(Movimientos.COLUMN_NAME_CANTIDAD))
            val fecha = cursor.getString(cursor.getColumnIndex(Movimientos.COLUMN_NAME_FECHA))
            val desc=cursor.getString(cursor.getColumnIndex(Movimientos.COLUMN_NAME_DESC))


            val movimientos: Movimientos = Movimientos(id, cantidad, fecha,desc)
            list.add(movimientos)
        }

        cursor.close()
        db.close()

        return list
    }


}