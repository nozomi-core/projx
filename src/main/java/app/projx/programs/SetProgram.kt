package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram
import app.projx.core.database.Database

class SetProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {

        when(args.getOrNull(1)) {
            "project" -> doProjectSet(context)
        }
    }

    private fun doProjectSet(context: CliContext) {
        Database.useStatement(context, "insert into vars(key, value) values(?, ?);") { smt ->
            //TODO this is duplicated in open project, create a key file
            smt.setString(1, "current_project_path")
            smt.setString(2, context.cwd.workingDir)

            smt.execute()
        }
    }
}