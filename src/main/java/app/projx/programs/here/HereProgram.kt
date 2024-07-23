package app.projx.programs.here

import app.projx.core.AppContext
import app.projx.core.SubProgram

class HereProgram: SubProgram {
    override fun execute(context: AppContext) {
        //TODO: support for linux
        Runtime.getRuntime().exec("cmd /c code")
    }
}