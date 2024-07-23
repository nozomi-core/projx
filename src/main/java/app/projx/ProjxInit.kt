package app.projx

import app.projx.core.AppContext
import app.projx.core.database.Database
import java.io.File

fun projxInit(context: AppContext) {
    val initFile = File("${context.srcDir.dir}/data")
    if(!initFile.exists()) {
        //Init app here
        initFile.mkdir()

        Database.init(context)
    }
}