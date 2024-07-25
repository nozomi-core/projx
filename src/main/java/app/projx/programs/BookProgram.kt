package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram
import java.io.File

class BookProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        if(args.size == 2) {
            doCreateBook(context, args)
        } else if(args.size == 3) {
            doBookWith3(context, args)
        }
    }

    private fun doCreateBook(context: CliContext, args: Array<String>) {
        val bookName = args[1]

        val bookFile = getBookFile(context, bookName)
        if(!bookFile.exists()) {
            bookFile.createNewFile()
        }

        Runtime.getRuntime().exec("cmd /c notepad ${bookFile.absolutePath}")
    }

    private fun doBookWith3(context: CliContext, args: Array<String>) {
        when(args[2]) {
            "list" -> doBookList(context, args)
        }
    }

    private fun doBookList(context: CliContext, args: Array<String>) {
        val bookFile = getBookFile(context, args[1])
        println(bookFile.readText())
    }

    private fun getBookFile(context: CliContext, name: String): File {
        return context.srcDir.getFile("data/books/${name}.txt")
    }
}