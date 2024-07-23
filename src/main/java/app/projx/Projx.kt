package app.projx

import app.projx.core.CliContext
import app.projx.core.Cwd
import app.projx.core.SourceDir
import java.io.File

fun main(args: Array<String>) {
    //init the cli application, which checks the file system to ensure it's in a correct state
    val context = createAppContext(args)

    projxInit(context)

    var isRunning = true

    if(args.getOrNull(0) == "shell" || args.isEmpty()) {
        while(isRunning) {
            print("pro >> ")
            val shellArgs = readLine()!!.split("\\s+".toRegex()).toMutableList().toTypedArray()

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

fun runCommand(context: CliContext, args: Array<String>) {
    val programs = ProjxRegister()

    val programName = args.getOrElse(0) { "" }
    programs.get(programName)?.apply {
        execute(context, args)
    }
}

fun createAppContext(args: Array<String>): CliContext {
    val currentDir =  System.getProperty("user.dir")
    val programDir = object {}.javaClass.protectionDomain.codeSource.location.toURI()

    val jarFile = File(programDir)

    val cwd = Cwd(currentDir)
    val sourceDir = SourceDir(jarFile.parent)

    return CliContext(
        cwd = cwd,
        srcDir = sourceDir,
        args = args
    )
}