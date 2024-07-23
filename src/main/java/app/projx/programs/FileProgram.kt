package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram

class FileProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        Runtime.getRuntime().exec("cmd /c explorer .")
    }
}