package app.projx.programs.help

import app.projx.core.AppContext
import app.projx.core.SubProgram

class HelpProgram: SubProgram {
    override fun execute(context: AppContext) {
        println("running help")
    }
}