package app.projx

import app.projx.core.CliContext
import app.projx.core.database.Database
import java.io.File

fun projxInit(context: CliContext) {
    createSourceDirIfNotExists(context, "data")
    createSourceDirIfNotExists(context, "data/books")
    Database.init(context)
}

fun createSourceDirIfNotExists(context: CliContext, path: String) {
    val initFile = File("${context.srcDir.dir}/$path")
    if(!initFile.exists()) {
        initFile.mkdir()
    }
}