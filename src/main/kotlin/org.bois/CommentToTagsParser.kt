package org.bois

class CommentToTagsParser {
    val tags = TagsStruct()

    fun parse (comment: java.lang.StringBuffer){
        tags.c = comment.substring(comment.indexOf("<c>"), comment.lastIndexOf("<c>"));
    }
}