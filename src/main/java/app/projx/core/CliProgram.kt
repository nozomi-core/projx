package app.projx.core

interface CliProgram {
    fun execute(context: CliContext, args: Array<String>)
}