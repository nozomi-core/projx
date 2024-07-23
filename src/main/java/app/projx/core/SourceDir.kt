package app.projx.core

import java.io.File

class SourceDir(val dir: String) {

    fun getFile(name: String): File {
        return File(dir, name)
    }
}