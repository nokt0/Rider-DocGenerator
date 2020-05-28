package org.bois

import org.bois.fileWorker.FileWorker
import org.bois.fileWorker.IFileWorker
import org.bois.htmlGenerator.HtmlDocGenerator
import org.bois.parser.Parser
import java.io.File

class DocGenerator : ICreateDocs {

    val fileWorker: IFileWorker = FileWorker(Parser())

    override fun create(sourceCodePath: String, outDirectory: String) {
        val out = File(outDirectory)
        val sourceCode = File(sourceCodePath)

        fileWorker.generateRecursive(sourceCode)
        val parsed = fileWorker.parser.parsed

        var htmlDocGenerator = HtmlDocGenerator()
        htmlDocGenerator.createDocumentation(parsed)
        fileWorker.moveToFolder(out, htmlDocGenerator.namespaces, htmlDocGenerator.classes, htmlDocGenerator.indexPage)
    }

}