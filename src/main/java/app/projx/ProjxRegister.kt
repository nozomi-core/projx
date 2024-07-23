package app.projx

import app.projx.core.CliProgram
import app.projx.programs.CodeProgram
import app.projx.programs.CreateProgram
import app.projx.programs.DirProgram
import app.projx.programs.FileProgram
import app.projx.programs.HelpProgram
import app.projx.programs.HereProgram
import app.projx.programs.ReadmeProgram
import app.projx.programs.FindProgram
import app.projx.programs.ListProgram
import app.projx.programs.SetProgram
import app.projx.programs.WhereProgram

class ProjxRegister {
    private val programs = HashMap<String, CliProgram>()

    init {
        add("help", HelpProgram())
        add("create", CreateProgram())
        add("dir", DirProgram())
        add("where", WhereProgram())
        add("readme", ReadmeProgram())
        add("here", HereProgram())
        add("find", FindProgram())
        add("file", FileProgram())
        add("set", SetProgram())
        add("code", CodeProgram())
        add("list", ListProgram())
    }

    private fun add(name: String, program: CliProgram) {
        programs[name] = program
    }

    fun get(name: String): CliProgram? {
        return programs[name]
    }
}