package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram

class LocalProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        val port = args[1].toInt()
        Runtime.getRuntime().exec("cmd /c start chrome http://localhost:${port}")
    }
}