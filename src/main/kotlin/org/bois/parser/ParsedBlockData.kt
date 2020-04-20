package org.bois.parser

data class ParsedBlockData(val docs: ArrayList<String>, val header: String) {
    override fun toString(): String {
        val string = StringBuffer()
        docs.forEach { string.append(it) }
        return header + System.lineSeparator() + string.toString()
    }
}