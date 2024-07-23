package app.projx.programs.readme

import app.projx.core.AppContext
import app.projx.core.SubProgram


class ReadmeProgram: SubProgram {
    override fun execute(context: AppContext) {
        val input = javaClass.getResourceAsStream("/readme.txt")

        input?.bufferedReader()?.use {
            it.forEachLine { line ->
                println(line)
            }
        }
    }
}