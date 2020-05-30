package org.bois.parser

class TagsStruct {
    var c: Tag = Tag()
    var code: Tag = Tag()
    var example: Tag = Tag()
    var exception: Tag = Tag()
    var include: Tag = Tag()
    var list: Tag = Tag()
    var para: Tag = Tag()
    var param: Tag = Tag()
    var paramref: Tag = Tag()
    var permission: Tag = Tag()
    var remarks: Tag = Tag()
    var inheritdoc: Tag = Tag()
    var see: Tag = Tag()
    var seealso: Tag = Tag()
    var summary: Tag = Tag()
    var typeparam: Tag = Tag()
    var typeparamref: Tag = Tag()
    var returns: Tag = Tag()
    var value: Tag = Tag()

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
                "<remarks>\t[$remarks]\n" +
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