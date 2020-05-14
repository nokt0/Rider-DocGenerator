package org.bois.parser

class ParsedClass(val type: HeaderType?, val namespace: String?, val insideBlocks: ArrayList<ParsedBlockData>) {

    var docs: ArrayList<String> = ArrayList()
    var parents: List<ParsedClass> = ArrayList()
    var children: List<ParsedClass> = ArrayList()

    fun createParentsAndChildren(tree: InheritTree) {

    }
}