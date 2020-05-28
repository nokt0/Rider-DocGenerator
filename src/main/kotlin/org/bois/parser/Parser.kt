package org.bois.parser

import java.io.BufferedReader
import java.io.File
import java.nio.charset.Charset
import java.nio.file.Files

class Parser : IParser {
    private val blocksParser = BlocksParser()
    private val treeParser = TreeParser()
    override val parsed = HashMap<String, ArrayList<ParsedClass>>()
    private var tree: InheritTree = InheritTree()


    override fun parseFile(input: File): HashMap<String, ArrayList<ParsedClass>> {
        val list: List<String> =
            Files.readAllLines(input.toPath(), Charset.defaultCharset())

        blocksParser.createBlocks(list)
        val blocks = blocksParser.blocksByNamespace()
        parsed.putAll(blocks)
        val tree = treeParser.createTree(list)
        this.tree = tree
        for ((key, value) in blocks) {
            value.forEach { it -> it.createParentsAndChildren(tree) }
        }

        return blocks
    }
}