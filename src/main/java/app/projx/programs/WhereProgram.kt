package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram

class WhereProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        println(context.srcDir.dir)
    }
}