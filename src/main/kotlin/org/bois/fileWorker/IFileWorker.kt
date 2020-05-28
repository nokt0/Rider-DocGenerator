package org.bois.fileWorker

import org.bois.FileName
import org.bois.parser.IParser
import java.io.File

interface IFileWorker {
    val parser : IParser
    fun generateRecursive(directory: File)
    fun moveToFolder(
        outPath: File,
        namespaces: HashMap<FileName,String>,
        classes: HashMap<FileName,String>,
        index: String
    )
}