package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram
import app.projx.core.database.VarKeys
import app.projx.core.database.repo.ProjectRepository
import app.projx.core.database.repo.VarsRepository

class SwapProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        val projectId = args.get(1)

        val projectPath = ProjectRepository.getPathByProjectId(projectId)
        VarsRepository.setVar(VarKeys.CURRENT_PROJECT_PATH, projectPath)
        println("Swap project to $projectPath")
    }
}