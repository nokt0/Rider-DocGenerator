package org.bois.parser

class Tag {
    var content: String? = null
    var cref: String? = null
    var file: String? = null
    var path: String? = null
    var type: String? = null
    var name: String? = null

    override fun toString(): String {
        return "content: [$content]\n" +
                "cref: [$cref]\n" +
                "file: [$file]\n" +
                "path: [$path]\n" +
                "type: [$type]\n" +
                "name: [$name]\n"
    }
}