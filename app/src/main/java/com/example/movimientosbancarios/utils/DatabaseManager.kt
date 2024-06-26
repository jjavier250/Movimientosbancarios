package com.example.agenda.activities.utils

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.movimientosbancarios.provider.Movimientos

class DatabaseManager (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "movimientos.db"
        const val DATABASE_VERSION = 2
        const val COLUMN_NAME_ID = "id"

        private const val SQL_CREATE_TABLE =
            "CREATE TABLE ${Movimientos.TABLE_NAME} (" +
                    "$COLUMN_NAME_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${Movimientos.COLUMN_NAME_CANTIDAD} TEXT," +
                    "${Movimientos.COLUMN_NAME_FECHA} TEXT," +
                    "${Movimientos.COLUMN_NAME_DESC} TEXT)"

        private const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${Movimientos.TABLE_NAME}"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion == 1) {
            db.execSQL("DROP TABLE IF EXISTS Task")
        }
        destroyDatabase(db)
        onCreate(db)
    }

    private fun destroyDatabase (db: SQLiteDatabase) {
        db.execSQL(SQL_DELETE_TABLE)
    }
}