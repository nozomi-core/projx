package app.projx.core.database.repo

import app.projx.core.database.Database
import app.projx.core.database.VarKeys
import app.projx.core.database.VarValues
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

object VarsRepository {

    fun setVar(key: String, value: String) {
        val future = CompletableFuture<Unit>()
        Database.useStatement("insert into vars(key, value) values(?, ?);") { smt ->
            //TODO this is duplicated in open project, create a key file
            smt.setString(1, key)
            smt.setString(2, value)

            smt.execute()
            future.complete(Unit)
        }
        return future.get(10, TimeUnit.SECONDS)
    }

    fun queryKey(key: String): String {
        val future = CompletableFuture<String>()
        Database.useStatement("select * from vars where key = ?") { smt ->
            smt.setString(1, key)

            val result = smt.executeQuery()
            if(result.next()) {
                val value = result.getString(VarValues.KEY_VALUE)
                future.complete(value)
            } else {
                future.completeExceptionally(Exception("Not variable with key: $key"))
            }
        }
        return future.get(10, TimeUnit.SECONDS)
    }
}