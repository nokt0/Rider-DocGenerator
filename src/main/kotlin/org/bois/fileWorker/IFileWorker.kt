package org.bois.fileWorker

import org.bois.parser.IParser
import java.io.File

interface IFileWorker {
    val parser : IParser
    fun generateRecursive(directory: File)
}