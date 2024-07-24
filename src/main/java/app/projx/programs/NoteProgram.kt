package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram
import app.projx.core.database.VarKeys
import app.projx.core.database.repo.VarsRepository
import java.io.File

class NoteProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        val projectPath = VarsRepository.queryKey(VarKeys.CURRENT_PROJECT_PATH)
        val noteFile = File(projectPath, ".projx.txt")
        if(!noteFile.exists()) {
            noteFile.createNewFile()
        }
        Runtime.getRuntime().exec("cmd /c notepad ${noteFile.absolutePath}")
    }
}