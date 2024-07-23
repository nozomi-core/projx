package app.projx.programs.create

import app.projx.core.AppContext
import app.projx.core.SubProgram

class CreateProgram: SubProgram {
    override fun execute(context: AppContext) {
        println("Running create")
    }
}