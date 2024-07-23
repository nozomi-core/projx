package app.projx.programs.dir

import app.projx.core.AppContext
import app.projx.core.SubProgram

class DirProgram: SubProgram {
    override fun execute(context: AppContext) {
        println(context.cwd.workingDir)
    }
}