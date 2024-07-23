package app.projx.core.database

import app.projx.core.CliContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.Statement

object Database {

    fun useStatement(context: CliContext, sql: String, callback: (PreparedStatement) -> Unit) {
        useConnection(context) {
            val preparedStatement = it.prepareStatement(sql)
            callback(preparedStatement)
            preparedStatement.close()
        }
    }

    fun init(context: CliContext) {
        val dbFile = context.srcDir.getFile("/data/projx.db")
        if(!dbFile.exists()) {
            useConnection(context) { conn ->
                val smt = conn.createStatement()
                initCreateTables(smt)
                smt.close()
            }
        }
    }

    private fun useConnection(context: CliContext, callback: (Connection) -> Unit) {
        val dbFile = context.srcDir.getFile("/data/projx.db")
        val conn = DriverManager.getConnection("jdbc:sqlite:${dbFile.absolutePath}")
        callback(conn)
        conn.close()
    }

    private fun initCreateTables(smt: Statement) {
        smt.execute("CREATE TABLE projects(id INTEGER PRIMARY KEY AUTOINCREMENT, codename TEXT, title TEXT, description TEXT, path TEXT);")
        smt.execute("CREATE TABLE vars(key TEXT PRIMARY KEY ON CONFLICT REPLACE, value TEXT);")
    }
}