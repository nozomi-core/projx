package app.projx

import app.projx.core.CliContext
import app.projx.core.database.Database
import java.io.File

fun projxInit(context: CliContext) {
    val initFile = File("${context.srcDir.dir}/data")
    if(!initFile.exists()) {
        initFile.mkdir()
    }
    Database.init(context)
}