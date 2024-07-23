package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram

class FindProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        val search = args.sliceArray(1 until args.size).joinToString("%20")

        //TODO: support linux
        Runtime.getRuntime().exec("cmd /c start chrome https://www.google.com/search?q=${search}")
    }
}