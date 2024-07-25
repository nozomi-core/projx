package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram

class PageProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        BookProgram().execute(context, arrayOf("", args[1], "list"))
    }
}