package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram
import app.projx.core.database.repo.ProjectRepository

class ListProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        ProjectRepository.all { results ->
            while(results.next()) {
                val codename = results.getString("codename")
                val title = results.getString("title")


                println(String.format("%s|%s", codename.padEnd(20, ' '), title))
            }
        }
    }
}