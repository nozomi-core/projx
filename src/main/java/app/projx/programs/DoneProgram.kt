package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram
import app.projx.core.database.Database

class DoneProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        val taskId = args[1]

        Database.useStatement("update or fail todo set status = ? where id = ?;") { smt ->
            smt.setString(1, "done")
            smt.setLong(2, taskId.toLong())
            smt.executeUpdate()
        }
    }
}