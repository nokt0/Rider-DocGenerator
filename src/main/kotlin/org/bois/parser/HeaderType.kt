package org.bois.parser

enum class HeaderType(val type:String) {
    INTERFACE("interface"),
    CLASS("class"),
    ABSTRACT_CLASS("abstract class"),
    VIRTUAL("virtual"),
    STRUCT("struct"),
    ENUM("enum");

    override fun toString(): String {
        return type
    }
}