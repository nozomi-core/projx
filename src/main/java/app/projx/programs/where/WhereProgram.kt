package app.projx.programs.where

import app.projx.core.AppContext
import app.projx.core.SubProgram

class WhereProgram: SubProgram {
    override fun execute(context: AppContext) {
        println(context.srcDir.dir)
    }
}