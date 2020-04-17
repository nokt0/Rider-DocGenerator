package org.bois.parser

enum class TagType(val tag: String) {
    C("c"),
    CODE("code"),
    EXAMPLE("example"),
    EXCEPTION("exception"),
    INCLUDE("include"),
    LIST("list"),
    PARA("para"),
    PARAM("param"),
    PARAMREF("paramref"),
    PERMISSION("permission"),
    INHERITDOC("inheritdoc"),
    SEE("see"),
    SEEALSO("seealso"),
    SUMMARY("summary"),
    TYPEPARAM("typeparam"),
    TYPEPARAMREF("typeparamref"),
    RETURNS("returns"),
    VALUE("value"),
    JUST_TEXT("just_text"),
    INIT_TAG("init_tag")
}