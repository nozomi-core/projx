package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram
import app.projx.core.database.VarKeys
import app.projx.core.database.repo.ProjectRepository
import app.projx.core.database.repo.VarsRepository

class WhatProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        val currentProjectPath = VarsRepository.queryKey(VarKeys.CURRENT_PROJECT_PATH)
        val projectId = ProjectRepository.geProjectByProjectId(currentProjectPath)
        println(projectId)
    }
}