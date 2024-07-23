package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram
import app.projx.core.database.Database

class CodeProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        Database.useStatement(context, "select * from vars where key = 'current_project_path'") { smt ->

            val result = smt.executeQuery()
            if(result.next()) {
                val currentPath = result.getString("value")
                Runtime.getRuntime().exec("cmd /c code $currentPath")
            }
        }
    }
}