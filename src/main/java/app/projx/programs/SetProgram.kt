package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram
import app.projx.core.database.Database
import app.projx.core.database.VarKeys

class SetProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {

        when(args.getOrNull(1)) {
            "project" -> doProjectSet(context)
            "home" -> doHomeSet(context)
        }
    }

    private fun doProjectSet(context: CliContext) {
        Database.useStatement("insert into vars(key, value) values(?, ?);") { smt ->
            val dir = context.cwd.workingDir
            //TODO this is duplicated in open project, create a key file
            smt.setString(1, VarKeys.CURRENT_PROJECT_PATH)
            smt.setString(2, context.cwd.workingDir)

            smt.execute()
            println("Current project directory set to: $dir")
        }
    }

    private fun doHomeSet(context: CliContext) {
        Database.useStatement("insert into vars(key, value) values(?, ?);") { smt ->
            val dir = context.cwd.workingDir

            smt.setString(1, VarKeys.HOME_PATH)
            smt.setString(2, dir)

            smt.execute()
            println("Project home directory set to: $dir")
        }
    }
}