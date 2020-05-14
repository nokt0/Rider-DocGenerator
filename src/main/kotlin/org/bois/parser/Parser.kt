package org.bois.parser

import java.io.BufferedReader
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Files


class Parser {
    private val blocksParser = BlocksParser()
    private val treeParser = TreeParser()

    fun parseFile(input : File){
        val list: List<String> =
            Files.readAllLines(input.toPath(), Charset.defaultCharset())
        val blocks= blocksParser.createBlocks(list)
        val tree = treeParser.createTree(list)
    }
}