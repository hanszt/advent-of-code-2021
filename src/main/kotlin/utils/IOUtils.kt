package utils

import java.io.File

fun File.createFilesIfNotPresent(amount: Int, name: String, fileExtension: String): Array<out File> {
    return if (isDirectory) {
        (1..amount).map { toPath().resolve("$name$it$fileExtension") }
            .forEach { path -> path.toFile().createNewFile() }
        listFiles() ?: emptyArray()
    } else throw IllegalStateException("$this is not a directory")
}
