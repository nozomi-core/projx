package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram
import app.projx.core.database.repo.ProjectRepository
import app.projx.core.database.repo.ProjectStatus

class DropProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        val projectId = args[1]
        ProjectRepository.updateStatus(projectId, ProjectStatus.DROPPED)
        println("Dropped project: $projectId")
    }
}