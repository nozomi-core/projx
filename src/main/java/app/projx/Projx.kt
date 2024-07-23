package app.projx

import app.projx.core.AppContext
import app.projx.core.Cwd
import app.projx.core.SourceDir
import app.projx.programs.ProgramRegister
import java.io.File

fun main(args: Array<String>) {
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