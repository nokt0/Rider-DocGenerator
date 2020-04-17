package org.bois.parser

public class TagsStruct {
    var c: String? = null
    var code: String? = null
    var example: String? = null
    var exception: String? = null
    var include: String? = null
    var list: String? = null
    var para: String? = null
    var param: String? = null
    var paramref: String? = null
    var permission: String? = null
    var inheritdoc: String? = null
    var see: String? = null
    var seealso: String? = null
    var summary: String? = null
    var typeparam: String? = null
    var typeparamref: String? = null
    var returns: String? = null
    var value: String? = null

    override fun toString(): String {
        return "<c>\t[$c]\n" +
                "<code>\t[$code]\n" +
                "<example>\t[$example]\n" +
                "<exception>\t[$exception]\n" +
                "<include>\t[$include]\n" +
                "<list>\t[$list]\n" +
                "<para>\t[$para]\n" +
                "<param>\t[$param]\n" +
                "<paramref>\t[$paramref]\n" +
                "<permission>\t[$permission]\n" +
                "<inheritdoc>\t[$inheritdoc]\n" +
                "<see>\t[$see]\n" +
                "<seealso>\t[$seealso]\n" +
                "<summary>\t[$summary]\n" +
                "<typeparam>\t[$typeparam]\n" +
                "<typeparamref>\t[$typeparamref]\n" +
                "<returns>\t[$returns]\n" +
                "<value>\t[$value]"


    }
}