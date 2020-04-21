package org.bois.parser

data class ParsedBlockData(val docs: ArrayList<String>, val header: String) {
    var type: HeaderType? = null
    override fun toString(): String {
        val string = StringBuffer()
        docs.forEach { string.append(it) }
        return header.toString() + System.lineSeparator() + string.toString()
    }
}