package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram
import app.projx.core.database.Database
import app.projx.core.database.VarKeys
import app.projx.core.database.repo.ProjectRepository
import app.projx.core.database.repo.VarsRepository

class TodoProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        val todoItem = args.sliceArray(1 until args.size).joinToString(" ")

        val currentProjectPath = VarsRepository.queryKey(VarKeys.CURRENT_PROJECT_PATH)
        val projectId = ProjectRepository.geProjectIdByPath(currentProjectPath)

        Database.useStatement("insert into todo(project_id, item, status, type) values (?, ?, ?, ?);") { smt ->
            smt.setString(1, projectId)
            smt.setString(2, todoItem)
            smt.setString(3, "active")
            smt.setString(4, "task")

            smt.executeUpdate()
            val id = smt.generatedKeys.getLong(1)
            println("Generated task id: T-$id")
        }
    }
}