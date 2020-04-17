package org.bois.parser

data class TagsTextConstants {
    val c = "<c>"
    val code = "<code>"
    val example = "<example>"
    val exception = "<exception>"
    val include = "<include>"
    val list = "<list>"
    val para = "<para>"
    val param = "<param>"
    val paramref = "<paramref>"
    val permission = "<permission>"
    val inheritdoc = "<inheritdoc>"
    val see = "<see>"
    val seealso = "<seealso>"
    val summary = "<summary>"
    val typeparam = "<typeparam>"
    val typeparamref = "<typeparamref>"
    val returns = "<returns>"
    val value = "<value>"

    val tags = arrayListOf<String>(
        c,
        code,
        example,
        exception,
        include,
        list,
        para,
        param,
        paramref,
        permission,
        inheritdoc,
        see,
        seealso,
        summary,
        typeparam,
        typeparamref,
        returns,
        value
    )
}