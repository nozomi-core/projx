package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram


class ReadmeProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        val input = javaClass.getResourceAsStream("/readme.txt")

        input?.bufferedReader()?.use {
            it.forEachLine { line ->
                println(line)
            }
        }
    }
}