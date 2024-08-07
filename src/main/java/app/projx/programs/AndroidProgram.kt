package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram
import app.projx.core.database.VarKeys
import app.projx.core.database.repo.VarsRepository

class AndroidProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        val path =  VarsRepository.queryKey(VarKeys.CURRENT_PROJECT_PATH)
        Runtime.getRuntime().exec("cmd /c studio64 $path")
    }
}