package org.bois.htmlGenerator

import org.bois.parser.ParsedClass
import java.io.File

interface IHtmlGenerator {
    val outPath: File
    fun createDocumentation(parsedClasses: HashMap<String, ArrayList<ParsedClass>>)
}