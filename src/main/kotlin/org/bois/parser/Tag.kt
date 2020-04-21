package org.bois.parser

class Tag(name: String, var type: TagType) {
    var text: String? = null
    var innerTags: ArrayList<Tag> = ArrayList<Tag>()
    var properties: HashMap<String, String> = HashMap<String, String>()
    var begin: String = "<$name>"
    var enclosing: String = "</$name>"
}