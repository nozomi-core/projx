package app.projx

import app.projx.core.AppContext
import app.projx.core.Cwd
import app.projx.core.SourceDir
import app.projx.programs.ProgramRegister
import java.io.File

fun main(args: Array<String>) {
    var isRunning = true

    if(args.getOrNull(0) == "shell") {
        while(isRunning) {
            print("pro >> ")
            val shellArgs = readLine()!!.split("\\s+").toMutableList().toTypedArray()

            val doExit = shellArgs.getOrNull(0) == "exit"
            if(doExit) {
                isRunning = false
            } else {
                runCommand(shellArgs)
            }
        }
    } else {
        runCommand(args)
    }
}

fun runCommand(args: Array<String>) {
    val programs = ProgramRegister()

    val programName = args.getOrElse(0) { "" }
    programs.get(programName)?.apply {
        execute(createAppContext())
    }
}

fun createAppContext(): AppContext {
    val currentDir =  System.getProperty("user.dir")
    val programDir = object {}.javaClass.protectionDomain.codeSource.location.toURI()

    val jarFile = File(programDir)

    val cwd = Cwd(currentDir)
    val sourceDir = SourceDir(jarFile.parent)

    return AppContext(
        cwd = cwd,
        srcDir = sourceDir
    )
}