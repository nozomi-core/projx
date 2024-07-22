package app.projx

import app.projx.core.AppContext
import app.projx.programs.ProgramRegister

fun main(args: Array<String>) {
    val context = AppContext()
    val programs = ProgramRegister()

    val programName = args.getOrElse(0) { "" }

    val toRun = programs.get(programName)
    toRun?.execute()

}