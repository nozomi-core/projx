package app.projx.programs

import app.projx.core.SubProgram
import app.projx.programs.create.CreateProgram
import app.projx.programs.help.HelpProgram

class ProgramRegister {
    private val programs = HashMap<String, SubProgram>()

    init {
        add("help", HelpProgram())
        add("create", CreateProgram())
    }

    private fun add(name: String, program: SubProgram) {
        programs[name] = program
    }

    fun get(name: String): SubProgram? {
        return programs[name]
    }
}