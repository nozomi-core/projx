package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram

class WhichProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        //TODO: lookup and print which project is selected, else 'unamed causal project' if none is selected
    }
}