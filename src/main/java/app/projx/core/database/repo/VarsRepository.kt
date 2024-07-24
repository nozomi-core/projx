package app.projx.core.database.repo

import app.projx.core.database.Database
import app.projx.core.database.VarValues
import java.util.concurrent.CompletableFuture

object VarsRepository {

    fun queryKey(key: String): String {
        val future = CompletableFuture<String>()
        Database.useStatement("select * from vars where key = ?") { smt ->
            smt.setString(1, key)

            val result = smt.executeQuery()
            if(result.next()) {
                val value = result.getString(VarValues.KEY_VALUE)
                future.complete(value)
            }
        }
        return future.get()
    }
}