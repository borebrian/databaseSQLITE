package com.livestream.mysqlitedatabase

import android.app.DownloadManager
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQuery
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast

class dDManager{
    var dbName ="MyNotes"
    var dbTable="Notes"
    var colID="ID"
    var colTitle="Title"
    var colDes="Description"
    var dbVersion=2



  val sqlcreateTable ="CREATE TABLE IF NOT EXISTS dbName("+colID+" INTEGER PRIMARY KEY,"+colTitle+" TEXT,"+colDes+" TEXT);"
    var sqlDB:SQLiteDatabase?=null
    constructor(context: Context){
        var db=DatabaseHelper(context)
        var sqlDB =db.writableDatabase


    }

    inner class DatabaseHelper:SQLiteOpenHelper{
        override fun onCreate(db: SQLiteDatabase?) {

            db!!.execSQL(sqlcreateTable)
            Toast.makeText(this.context,"Database created successfully...",Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
           db!!.execSQL("DROP TABLE IF EXISTS"+dbName  )
        }

        var context:Context?=null
        constructor(context: Context):super(context,dbName,null,dbVersion){


        }



    }



    fun insert(values: ContentValues):Long{

    val ID =sqlDB!!.insert(dbTable,"",values)
        return ID


    }
    fun Query(projection:Array<String>,selection:String,selectionArgs:Array<String>,sortOrder:String ):Cursor{

        val qb=SQLiteQueryBuilder()
        qb.tables=dbTable
        val cursor = qb.query(sqlDB,projection,selection,selectionArgs,null,null,null)
        return cursor


    }

    fun delete(selection: String,selectionArgs:Array<String>):Int {
        val count=sqlDB!!.delete(dbTable,selection,selectionArgs)
        return  count


    }
    fun update(values:ContentValues,selection:String,selectionArgs:Array<String>):Int{
        val count=sqlDB!!.update(dbTable , values,selection,selectionArgs)

            return count

    }




    }






