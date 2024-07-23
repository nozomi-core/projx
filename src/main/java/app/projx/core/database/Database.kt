package app.projx.core.database

import app.projx.core.AppContext
import java.sql.DriverManager
import java.sql.Statement

object Database {

    fun init(context: AppContext) {
        val dbFile = context.srcDir.getFile("/data/projx.db")
        if(!dbFile.exists()) {
            val conn = DriverManager.getConnection("jdbc:sqlite:${dbFile.absolutePath}")
            val smt = conn.createStatement()
            initCreateTables(smt)
            conn.close()
            smt.close()
        }
    }

    private fun initCreateTables(smt: Statement) {
        smt.execute("CREATE TABLE projects(id INTEGER PRIMARY KEY AUTOINCREMENT, codename TEXT, title TEXT, description TEXT, path TEXT);")
    }
}