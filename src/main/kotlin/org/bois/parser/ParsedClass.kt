package org.bois.parser

class ParsedClass(
    val name: String,
    val type: HeaderType?,
    val namespace: String?,
    val insideBlocks: ArrayList<ParsedBlockData>
) {

    var docs: ArrayList<String> = ArrayList()
    var parents: ArrayList<String> = ArrayList()
    var children: ArrayList<String> = ArrayList()
    var tags: TagsStruct? = null

    fun createParentsAndChildren(tree: InheritTree) {
        if (tree.children[name] != null) {
            children = tree.children[name]!!
        }
        if (tree.parents[name] != null) {
            parents = tree.parents[name]!!
        }
    }
}