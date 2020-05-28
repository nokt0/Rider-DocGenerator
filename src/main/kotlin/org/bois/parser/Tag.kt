package org.bois.parser

class Tag {
    var content: String? = null
    var cref: String? = null
    var file: String? = null
    var path: String? = null
    var type: String? = null
    var name: String? = null

    override fun toString(): String {
        if (content == null && cref == null && file == null && path == null && type == null && name == null)
            return ""

        return "content: [$content] \t" +
                "cref: [$cref] \t" +
                "file: [$file] \t" +
                "path: [$path] \t" +
                "type: [$type] \t" +
                "name: [$name]"
    }
}