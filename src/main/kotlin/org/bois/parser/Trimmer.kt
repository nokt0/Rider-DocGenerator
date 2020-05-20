package org.bois.parser

class Trimmer {
    companion object {
        fun trimLine(doc: ArrayList<String>): TagsStruct {
            var tagsStruct: TagsStruct = TagsStruct()

            doc.forEach {
                println(it)
                when {
                    Regex(""".*<c>.\n*""").matches(it) ->
                        tagsStruct.c = it.substring(it.indexOf("<c>") + 3, it.indexOf("</c>"))
                    Regex(""".*<code>.\n*""").matches(it) ->
                        tagsStruct.code = it.substring(it.indexOf("<code>") + 6, it.indexOf("</code>"))
                    Regex(""".*<example>.*\n""").matches(it) ->
                        tagsStruct.example =
                            it.substring(it.indexOf("<example>") + 9, it.indexOf("</example>"))
                    Regex(""".*<exception.*>.*\n""").matches(it) ->
                        tagsStruct.exception =
                            it.substring(it.indexOf("<exception") + 11, it.indexOf("</excpetion>"))
                    Regex(""".*<include.*/>.*\n""").matches(it) ->
                        tagsStruct.include =
                            it.substring(it.indexOf("<include") + 8, it.indexOf("/>"))
                    Regex(""".*<list.*>.*\n""").matches(it) ->
                        tagsStruct.list = it.substring(it.indexOf("<list") + 6, it.indexOf("</list>"))
                    Regex(""".*<para>.*\n""").matches(it) ->
                        tagsStruct.para = it.substring(it.indexOf("<para>") + 6, it.indexOf("</para>"))
                    Regex(""".*<param.*>.*\n""").matches(it) ->
                        tagsStruct.param = it.substring(it.indexOf("<param") + 7, it.indexOf("</param>"))
                    Regex(""".*<paramref.*/>.*\n""").matches(it) ->
                        tagsStruct.paramref = it.substring(it.indexOf("<paramref") + 9, it.indexOf("/>"))
                    Regex(""".*<permission.*>.*\n""").matches(it) ->
                        tagsStruct.permission =
                            it.substring(it.indexOf("<permission") + 12, it.indexOf("</permission>"))
                    Regex(""".*<remarks>.*\n""").matches(it) ->
                        tagsStruct.remarks = it.substring(it.indexOf("<remarks>") + 9, it.indexOf("</remarks>"))
                    Regex(""".*<returns>.*\n""").matches(it) ->
                        tagsStruct.returns = it.substring(it.indexOf("<returns>") + 9, it.indexOf("</returns>"))
                    Regex(""".*<inheritdoc.*/>.*\n""").matches(it) ->
                        tagsStruct.inheritdoc = it.substring(it.indexOf("<inheritdoc") + 11, it.indexOf("/>"))
                    Regex(""".*<see.*/>.*\n""").matches(it) ->
                        tagsStruct.see = it.substring(it.indexOf("<see") + 4, it.indexOf("/>"))
                    Regex(""".*<seealso.*>.*\n""").matches(it) ->
                        tagsStruct.seealso = it.substring(it.indexOf("<seealso") + 8, it.indexOf("</seealso>"))
                    Regex(""".*<summary>.*\n""").matches(it) ->
                        tagsStruct.summary = it.substring(it.indexOf("<summary>") + 9, it.indexOf("</summary>"))
                    Regex(""".*<typeparam.*>.*\n""").matches(it) ->
                        tagsStruct.typeparam = it.substring(it.indexOf("<typeparam") + 11, it.indexOf("</typeparam>"))
                    Regex(""".*<typeparamref.*/>.*\n""").matches(it) ->
                        tagsStruct.typeparamref = it.substring(it.indexOf("<typeparamref") + 11, it.indexOf("/>"))
                    Regex(""".*<value>.*\n""").matches(it) ->
                        tagsStruct.value = it.substring(it.indexOf("<value>") + 7, it.indexOf("</value>"))
                }
            }
            return tagsStruct
        }
    }
}