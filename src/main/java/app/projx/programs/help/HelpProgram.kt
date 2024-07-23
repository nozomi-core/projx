package app.projx.programs.help

import app.projx.core.AppContext
import app.projx.core.SubProgram

class HelpProgram: SubProgram {
    override fun execute(context: AppContext) {
        val input = javaClass.getResourceAsStream("/help.txt")

        input?.bufferedReader()?.use {
            it.forEachLine { line ->
                println(line)
            }
        }
    }
}