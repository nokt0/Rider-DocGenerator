package org.bois.htmlGenerator

import org.bois.parser.ParsedClass
import java.io.File

interface IHtmlGenerator {
    fun createDocumentation(parsedClasses: HashMap<String, ArrayList<ParsedClass>>)
}