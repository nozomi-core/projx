package app.projx.use

import app.projx.core.database.VarKeys
import app.projx.core.database.repo.ProjectRepository
import app.projx.core.database.repo.VarsRepository

fun useGetCurrentProjectId(): String {
    val currentProjectPath = VarsRepository.queryKey(VarKeys.CURRENT_PROJECT_PATH)
    return ProjectRepository.geProjectIdByPath(currentProjectPath)
}