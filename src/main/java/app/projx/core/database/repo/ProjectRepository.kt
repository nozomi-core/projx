package app.projx.core.database.repo

import app.projx.core.database.Database
import java.sql.ResultSet

object ProjectRepository {

    fun insert(codename: String, title: String, description: String, path: String) {
        Database.useStatement(
            "insert into projects(codename, title, description, path) values(?, ?, ?, ?)"
        ) { stm ->
            stm.setString(1, codename)
            stm.setString(2, title)
            stm.setString(3, description)
            stm.setString(4, path)
            stm.execute()
        }
    }

    fun all(callback: (ResultSet) -> Unit) {
        Database.useStatement("select * from projects") { stm ->
            callback(stm.executeQuery())
        }
    }
}