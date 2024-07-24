package app.projx.core.database.repo

import app.projx.core.database.Database
import java.sql.ResultSet
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

object ProjectRepository {
    object Columns {
        const val PROJECT_ID = "codename"
        const val TITLE = "title"
        const val DESCRIPTION = "description"
        const val PATH = "path"
        const val STATUS = "status"
    }

    fun insert(codename: String, title: String, description: String, path: String, status: ProjectStatus) {
        Database.useStatement(
            "insert into projects(${Columns.PROJECT_ID}, ${Columns.TITLE}, ${Columns.DESCRIPTION}, ${Columns.PATH}, ${Columns.STATUS}) values(?, ?, ?, ?, ?)"
        ) { stm ->
            stm.setString(1, codename)
            stm.setString(2, title)
            stm.setString(3, description)
            stm.setString(4, path)
            stm.setString(5, status.status)
            stm.execute()
        }
    }

    fun updateStatus(id: String, status: ProjectStatus) {
        val future = CompletableFuture<Unit>()
        Database.useStatement("update or fail projects set status = ? where codename = ?;") { smt ->
            smt.setString(1, status.status)
            smt.setString(2, id)
            smt.execute()
            future.complete(Unit)
        }

        future.get()
    }

    fun getPathByProjectId(id: String): String {
        val future = CompletableFuture<String>()

        Database.useStatement("select * from projects where ${Columns.PROJECT_ID} = ?") { stm ->
            stm.setString(1, id)
            val result = stm.executeQuery()
            if(result.next()) {
                val path = result.getString(Columns.PATH)
                future.complete(path)
            } else {
                future.completeExceptionally(Exception("Not project id: $id"))
            }
        }
        return future.get(10, TimeUnit.SECONDS)
    }

    fun geProjectByProjectId(projectPath: String): String {
        val future = CompletableFuture<String>()

        Database.useStatement("select * from projects where ${Columns.PATH} = ?") { stm ->
            stm.setString(1, projectPath)
            val result = stm.executeQuery()
            if(result.next()) {
                val path = result.getString(Columns.PROJECT_ID)
                future.complete(path)
            } else {
                future.completeExceptionally(Exception("Not project path: $projectPath"))
            }
        }
        return future.get(10, TimeUnit.SECONDS)
    }

    fun getAllActive(callback: (ResultSet) -> Unit) {
        Database.useStatement("select * from projects where status = '${ProjectStatus.ACTIVE.status}'") { stm ->
            callback(stm.executeQuery())
        }
    }

    fun getAll(callback: (ResultSet) -> Unit) {
        Database.useStatement("select * from projects") { stm ->
            callback(stm.executeQuery())
        }
    }
}

enum class ProjectStatus(val status: String) {
    ACTIVE("active"),
    DROPPED("dropped")
}