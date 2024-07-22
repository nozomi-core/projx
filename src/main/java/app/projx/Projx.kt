package app.projx

fun main(args: Array<String>) {
    println("Hello projx: ${args.getOrElse(0, {""})}")
}