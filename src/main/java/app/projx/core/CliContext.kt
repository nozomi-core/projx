package app.projx.core

class CliContext(
    val cwd: Cwd,
    val srcDir: SourceDir,
    val args: Array<String>
)