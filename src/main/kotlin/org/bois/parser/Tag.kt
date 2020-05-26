package org.bois.parser

class Tag {
    var content: String? = null
    var cref: String? = null
    var file: String? = null
    var path: String? = null
    var type: String? = null
    var name: String? = null

    override fun toString(): String {
        return "content: [$content] " +
                "cref: [$cref] " +
                "file: [$file] " +
                "path: [$path] " +
                "type: [$type] " +
                "name: [$name] "
    }
}