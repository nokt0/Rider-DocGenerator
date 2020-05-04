package org.bois.parser

data class ParsedBlockData(val docs: ArrayList<String>, val header: String, val name: String?, val namespace: String?, val type: HeaderType?) {
    override fun toString(): String {
        val string = StringBuffer()
        docs.forEach { string.append(it) }
        return header.toString() + System.lineSeparator() + string.toString()
    }
}