package app.projx.programs

import app.projx.core.SubProgram
import app.projx.programs.create.CreateProgram
import app.projx.programs.dir.DirProgram
import app.projx.programs.help.HelpProgram
import app.projx.programs.where.WhereProgram

class ProgramRegister {
    private val programs = HashMap<String, SubProgram>()

    init {
        add("help", HelpProgram())
        add("create", CreateProgram())
        add("dir", DirProgram())
        add("where", WhereProgram())
    }

    private fun add(name: String, program: SubProgram) {
        programs[name] = program
    }

    fun get(name: String): SubProgram? {
        return programs[name]
    }
}