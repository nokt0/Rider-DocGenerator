package org.bois.parser

import com.intellij.internal.statistic.utils.addPluginInfoTo

class Trimmer {
    companion object {
        fun trimLine(doc: ArrayList<String>): TagsStruct {
            var tagsStruct: TagsStruct = TagsStruct()

            doc.forEach {
                println(it)
                when {
                    Regex(""".*<c>.\n*""").matches(it) ->
                        tagsStruct.c = splitForParameters(it)
                    Regex(""".*<code>.\n*""").matches(it) ->
                        tagsStruct.code = splitForParameters(it)
                    Regex(""".*<example>.*\n""").matches(it) ->
                        tagsStruct.example = splitForParameters(it)
                    Regex(""".*<exception.*>.*\n""").matches(it) ->
                        tagsStruct.exception = splitForParameters(it)
                    Regex(""".*<include.*/>.*\n""").matches(it) ->
                        tagsStruct.include = splitForParameters(it)
                    Regex(""".*<list.*>.*\n""").matches(it) ->
                        tagsStruct.list = splitForParameters(it)
                    Regex(""".*<para>.*\n""").matches(it) ->
                        tagsStruct.para = splitForParameters(it)
                    Regex(""".*<param.*>.*\n""").matches(it) ->
                        tagsStruct.param = splitForParameters(it)
                    Regex(""".*<paramref.*/>.*\n""").matches(it) ->
                        tagsStruct.paramref = splitForParameters(it)
                    Regex(""".*<permission.*>.*\n""").matches(it) ->
                        tagsStruct.permission = splitForParameters(it)
                    Regex(""".*<remarks>.*\n""").matches(it) ->
                        tagsStruct.remarks = splitForParameters(it)
                    Regex(""".*<returns>.*\n""").matches(it) ->
                        tagsStruct.returns = splitForParameters(it)
                    Regex(""".*<inheritdoc.*/>.*\n""").matches(it) ->
                        tagsStruct.inheritdoc = splitForParameters(it)
                    Regex(""".*<see.*/>.*\n""").matches(it) ->
                        tagsStruct.see = splitForParameters(it)
                    Regex(""".*<seealso.*>.*\n""").matches(it) ->
                        tagsStruct.seealso = splitForParameters(it)
                    Regex(""".*<summary>.*\n""").matches(it) ->
                        tagsStruct.summary = splitForParameters(it)
                    Regex(""".*<typeparam.*>.*\n""").matches(it) ->
                        tagsStruct.typeparam = splitForParameters(it)
                    Regex(""".*<typeparamref.*/>.*\n""").matches(it) ->
                        tagsStruct.typeparamref = splitForParameters(it)
                    Regex(""".*<value>.*\n""").matches(it) ->
                        tagsStruct.value = splitForParameters(it)
                }
            }
            return tagsStruct
        }


        fun splitForParameters(line: String): Tag {
            var result = Tag()
            if (line.indexOf(">") < line.lastIndexOf("<"))
                result.content = line.substring(line.indexOf(">") + 1, line.lastIndexOf("<") - 1).trim()

            when {
                Regex(""".*cref=.*\n""").matches(line) -> {
                    result.cref = line.substring(line.indexOf("cref=") + 6, line.indexOf(">") - 1)
                }

                Regex(""".*name=.*\n""").matches(line) -> {
                    result.name = line.substring(line.indexOf("name=") + 6, line.indexOf(">") - 1)
                }

                Regex(""".*file=.*\n""").matches(line) -> {
                    result.file = line.substring(line.indexOf("file=") + 6, line.indexOf(">") - 1)
                }

                Regex(""".*path=.*\n""").matches(line) -> {
                    result.path = line.substring(line.indexOf("path=") + 6, line.indexOf(">") - 1)

                }
            }
            return result
        }
    }
}