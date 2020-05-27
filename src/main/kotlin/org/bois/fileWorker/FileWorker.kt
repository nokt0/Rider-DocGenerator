package org.bois.fileWorker

import org.bois.parser.IParser
import java.io.File
import java.nio.file.Files

import java.nio.file.Path
import java.nio.file.Paths

class FileWorker(override val parser: IParser) : IFileWorker {

    override fun generateRecursive(directory: File) {
        Files.walk(Paths.get(directory.path)).use { paths ->
            {
                paths
                    .filter(Files::isDirectory)
                    .map(Path::toFile)
                    .forEach { generateRecursive(it) }

                paths
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .filter { it.extension == "cs" }
                    .forEach { parser.parseFile(it) }
            }
        }
    }

    private fun moveToFolder(
        outPath: File,
        namespaces: ArrayList<String>,
        classes: ArrayList<String>,
        index: ArrayList<String>
    ) {
        val path = outPath.toPath()
        Files.copy(Paths.get("src/main/resources/html_templates/assets"), path)

    }
}