package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram

class HereProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        //TODO: support for linux
        Runtime.getRuntime().exec("cmd /c code .")
    }
}