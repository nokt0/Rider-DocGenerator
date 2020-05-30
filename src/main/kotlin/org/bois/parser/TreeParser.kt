package org.bois.parser

import java.util.ArrayList

class TreeParser {
    private val tree = InheritTree()

    fun createTree(lines: List<String>): InheritTree {
        for (line in lines) {
            val parents = ArrayList<String>()
            var className: String? = null
            if (line.indexOf("class") != -1) {
                var endIndex = 0
                if (line.indexOf(":") != -1)
                    endIndex = line.indexOf(":") - 1
                else
                    endIndex = line.length
                className = line.subSequence(line.indexOf("class") + 6, endIndex).trim().toString()

                if (line.indexOf(":") != -1) {
                    val parentPart = line.substring(line.indexOf(":") + 1)
                    parents.addAll(parentPart.trim().split(","))
                }

                if (parents.isNotEmpty()) {
                    if (tree.parents.containsKey(className)) {
                        tree.parents[className]!!.addAll(parents)
                    } else
                        tree.parents[className] = parents

                    parents.forEach {

                        if (tree.children.containsKey(it))
                            tree.children[it]!!.add(className)
                        else {
                            val newChildrenArray = ArrayList<String>()
                            newChildrenArray.add(className)
                            tree.children[it] = newChildrenArray
                        }
                    }
                }
            }
        }
        return tree
    }
}