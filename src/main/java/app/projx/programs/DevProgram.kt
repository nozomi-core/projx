package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram
import app.projx.core.database.VarKeys
import app.projx.core.database.repo.VarsRepository
import java.io.File

class DevProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        val projectPath = VarsRepository.queryKey(VarKeys.CURRENT_PROJECT_PATH)
        val devBatch = File(projectPath, "dev.bat")

        if(devBatch.exists()) {
            println("running: ${devBatch.absolutePath}")

            val builder = ProcessBuilder("cmd", "/c", "start", devBatch.absolutePath)
            builder.directory(File(projectPath))

            builder.start()
        } else {
            println("'dev.bat' does not exist in project dir: $projectPath")
        }
    }
}