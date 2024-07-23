package app.projx.core.database.projects

import app.projx.core.CliContext
import app.projx.core.database.Database
import java.sql.ResultSet

object ProjectRepository {

    fun insert(context: CliContext, codename: String, title: String, description: String) {
        Database.useStatement(context, "insert into projects(codename, title, description, path) values(?, ?, ?, ?)") { stm ->
            stm.setString(1, codename)
            stm.setString(2, title)
            stm.setString(3, description)
            stm.setString(4, context.cwd.workingDir)
            stm.execute()
        }
    }

    fun all(context: CliContext, callback: (ResultSet) -> Unit) {
        Database.useStatement(context, "select * from projects") { stm ->
            callback(stm.executeQuery())
        }
    }
}