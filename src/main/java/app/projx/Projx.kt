package app.projx

import app.projx.core.AppContext
import app.projx.core.Cwd
import app.projx.core.SourceDir
import app.projx.programs.ProgramRegister
import java.io.File

fun main(args: Array<String>) {
    //init the cli application, which checks the file system to ensure it's in a correct state
    val context = createAppContext()
    projxInit(context)

    var isRunning = true

    if(args.getOrNull(0) == "shell") {
        while(isRunning) {
            print("pro >> ")
            val shellArgs = readLine()!!.split("\\s+").toMutableList().toTypedArray()

            val doExit = shellArgs.getOrNull(0) == "exit"
            if(doExit) {
                isRunning = false
            } else {
                runCommand(context, shellArgs)
            }
        }
    } else {
        runCommand(context, args)
    }
}

fun runCommand(context: AppContext, args: Array<String>) {
    val programs = ProgramRegister()

    val programName = args.getOrElse(0) { "" }
    programs.get(programName)?.apply {
        execute(context)
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