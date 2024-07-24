package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram
import app.projx.core.database.repo.ProjectRepository
import java.sql.ResultSet

class ListProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        if(args.size == 1) {
            listAllActive()
        } else if(args.size == 2 && args[1] == "-a") {
            listAll()
        }
    }

    private fun handleResult(results: ResultSet) {
        while(results.next()) {
            val intId = results.getInt("id")
            val codename = results.getString("codename")
            val title = results.getString("title")

            println(String.format("%d|%s|%s",intId, codename.padEnd(20, ' '), title))
        }
    }

    private fun listAllActive() {
        ProjectRepository.getAllActive { results ->
            handleResult(results)
        }
    }

    private fun listAll() {
        ProjectRepository.getAll { results ->
            handleResult(results)
        }
    }
}