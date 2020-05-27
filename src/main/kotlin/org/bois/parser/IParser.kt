package org.bois.parser

import java.io.File

interface IParser {
    val parsed : HashMap<String, ArrayList<ParsedClass>>
    fun parseFile(input: File): HashMap<String, ArrayList<ParsedClass>>
}