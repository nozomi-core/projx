package app.projx.core.database

import app.projx.core.CliContext
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.Statement

object Database {
    private lateinit var dbContext: CliContext

    fun init(context: CliContext) {
        dbContext = context
        val dbFile = context.srcDir.getFile("/data/projx.db")
        if(!dbFile.exists()) {
            useConnection { conn ->
                val smt = conn.createStatement()
                initCreateTables(smt)
                smt.close()
            }
        }
    }

    fun useStatement(sql: String, callback: (PreparedStatement) -> Unit) {
        useConnection {
            val preparedStatement = it.prepareStatement(sql)
            callback(preparedStatement)
            preparedStatement.close()
        }
    }

    private fun useConnection(callback: (Connection) -> Unit) {
        val dbFile = dbContext.srcDir.getFile("/data/projx.db")
        val conn = DriverManager.getConnection("jdbc:sqlite:${dbFile.absolutePath}")
        callback(conn)
        conn.close()
    }

    private fun initCreateTables(smt: Statement) {
        smt.execute("CREATE TABLE projects(id INTEGER PRIMARY KEY AUTOINCREMENT, codename TEXT, title TEXT, description TEXT, path TEXT UNIQUE ON CONFLICT IGNORE, status TEXT);")
        smt.execute("CREATE TABLE vars(key TEXT PRIMARY KEY ON CONFLICT REPLACE, value TEXT);")
    }
}