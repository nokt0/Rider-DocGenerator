package org.bois.parser

enum class HeaderType(val type:String) {
    INTERFACE("INTERFACE"),
    CLASS("CLASS"),
    ABSTRACT_CLASS("ABSTRACT_CLASS"),
    VIRTUAL("VIRTUAL"),
    STRUCT("STRUCT"),
    ENUM("ENUM");

    override fun toString(): String {
        return type
    }
}