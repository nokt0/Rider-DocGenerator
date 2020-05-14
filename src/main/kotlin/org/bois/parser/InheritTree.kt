package org.bois.parser

class InheritTree {
    var parents = HashMap<String, ArrayList<String>>()
    var children = HashMap<String, ArrayList<String>>()

    override fun toString(): String {
        var result: String = ""
        result += "(((Parents hashMap)))" + System.lineSeparator()
        parents.forEach {
            result += "Child: " + it.key + " Parents: " + it.value.toString() + System.lineSeparator()
        }
        result += "(((Children hashMap)))" + System.lineSeparator()
        children.forEach {
            result += "Parent: " + it.key + " Children: " + it.value.toString() + System.lineSeparator()
        }
        return result
    }
}