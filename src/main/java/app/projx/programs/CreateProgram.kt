package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram
import app.projx.core.database.projects.ProjectRepository

class CreateProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        print("Title:")

        val title = readLine() ?: ""

        print("Description:")
        val description = readLine() ?: ""

        ProjectRepository.insert(context, args[1], title, description)
    }
}