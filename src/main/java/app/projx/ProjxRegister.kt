package app.projx

import app.projx.core.CliProgram
import app.projx.programs.*

class ProjxRegister {
    private val programs = HashMap<String, CliProgram>()

    init {
        add("help", HelpProgram())
        add("create", CreateProgram())
        add("where", WhereProgram())
        add("readme", ReadmeProgram())
        add("here", HereProgram())
        add("find", FindProgram())
        add("file", FileProgram())
        add("set", SetProgram())
        add("code", CodeProgram())
        add("list", ListProgram())
        add("git", GitProgram())
        add("home", HomeProgram())
        add("goto", GoToProgram())
        add("android", AndroidProgram())
        add("swap", SwapProgram())
        add("what", WhatProgram())
        add("drop", DropProgram())
        add("dev", DevProgram())
        add("local", LocalProgram())
        add("note", NoteProgram())
        add("todo", TodoProgram())
        add("tasks", TasksProgram())
        add("done", DoneProgram())
        add("doing", DoingProgram())
        add("source", SourceProgram())
        add("book", BookProgram())
        add("books", BooksProgram())
    }

    private fun add(name: String, program: CliProgram) {
        programs[name] = program
    }

    fun get(name: String): CliProgram? {
        return programs[name]
    }
}