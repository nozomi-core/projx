package app.projx.programs

import app.projx.core.CliContext
import app.projx.core.CliProgram

class BooksProgram: CliProgram {
    override fun execute(context: CliContext, args: Array<String>) {
        val bookDir = context.srcDir.getFile("data/books")
        bookDir.listFiles()?.forEach { bookFile ->
            if(bookFile.isFile) {
                println(bookFile.nameWithoutExtension)
            }
        }
    }
}