package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram
import app.projx.core.database.Database
import app.projx.use.useGetCurrentProjectId

class TasksProgram: CliProgram {
    val GREEN = "\u001b[0;32m"
    val RESET = "\u001b[0m"

    override fun execute(context: CliContext, args: Array<String>) {
        Database.useStatement("select * from todo where project_id = ? and status = 'active' or status = 'doing'") { smt ->
            val currentProjectId = useGetCurrentProjectId()
            smt.setString(1, currentProjectId)

            val result = smt.executeQuery()

            if(!result.isBeforeFirst) {
                println("No tasks YAY!")
            }

            while(result.next()) {
                val id = result.getLong("id")
                val item = result.getString("item")
                val status = result.getString("status")

                val ioStr = when(status) {
                    "doing" -> "${GREEN}T-${id} | $item${RESET}"
                    else -> "T-${id} | $item"
                }

                println(ioStr)
            }
        }
    }
}