package org.bois.parser

import com.intellij.internal.statistic.utils.addPluginInfoTo

class Trimmer {
    companion object {
        fun trimLine(doc: ArrayList<String>): TagsStruct {
            var tagsStruct: TagsStruct = TagsStruct()


            doc.forEach {
                println(it)
                var templateList: ArrayList<String> = ArrayList()
                when {
                    Regex(""".*<c>.*\n""").matches(it) -> {
                        while (!Regex(""".*</c>.*\n""").matches(it))
                            it.zipWithNext()
                    }

                    Regex(""".*<code>.*\n""").matches(it) -> {
                        while (!Regex(""".*</code>.*\n""").matches(it))
                            it.zipWithNext()

                    }

                    Regex(""".*<example>.*\n""").matches(it) -> {
                        while (!Regex(""".*</example>.*""").matches(it))
                            it.zipWithNext()

                    }

                    Regex(""".*<exception.*>.*\n""").matches(it) -> {
                        while (!Regex(""".*</exception>.*\n""").matches(it))
                            it.zipWithNext()
                    }

                    //include is a single tag

                    Regex(""".*<list.*>.*\n""").matches(it) -> {
                        while (!Regex(""".*</list>.*\n""").matches(it))
                            it.zipWithNext()
                    }

                    Regex(""".*<para>.*\n""").matches(it) -> {
                        while (!Regex(""".*</para>.*\n""").matches(it))
                            it.zipWithNext()
                    }

                    Regex(""".*<param.*>.*\n""").matches(it) -> {
                        while (!Regex(""".*</param>.*\n""").matches(it))
                            it.zipWithNext()
                    }

                    //paramref is a single tag

                    Regex(""".*<permission.*>.*\n""").matches(it) -> {
                        while (!Regex(""".*</permission>.*\n""").matches(it))
                            it.zipWithNext()
                    }

                    Regex(""".*<remarks>.*\n""").matches(it) -> {
                        while (!Regex(""".*</remarks>.*\n""").matches(it))
                            it.zipWithNext()
                    }

                    Regex(""".*<returns>.*\n""").matches(it) -> {
                        while (!Regex(""".*</returns>.*\n""").matches(it))
                            it.zipWithNext()
                    }

                    // inheritdoc is a single tag

                    // see is a single tag

                    Regex(""".*<seealso.*>.*\n""").matches(it) -> {
                        while (!Regex(""".*</seealso>.*\n""").matches(it))
                            it.zipWithNext()
                    }

                    Regex(""".*<summary>.*\n""").matches(it) -> {
                        while (!Regex(""".*</summary>.*\n""").matches(it))
                            it.zipWithNext()
                    }

                    Regex(""".*<typeparam.*>.*\n""").matches(it) -> {
                        while (!Regex(""".*</typeparam>.*\n""").matches(it))
                            it.zipWithNext()
                    }

                    // typeparamref is a single tag

                    Regex(""".*<value>.*\n""").matches(it) -> {
                        while (!Regex(""".*</value>.*\n""").matches(it))
                            it.zipWithNext()
                    }
                }
                println(it)
            }

            doc.forEach {
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

            var tmp: String

            when {
                Regex(""".*cref=.*\n""").matches(line) -> {
                    tmp = line.substring(line.indexOf("cref=") + 6)
                    result.cref = tmp.substring(0, tmp.indexOf("\""))
                }

                Regex(""".*name=.*\n""").matches(line) -> {
                    tmp = line.substring(line.indexOf("name=") + 6)
                    result.name = tmp.substring(0, tmp.indexOf("\""))

                }

                Regex(""".*file=.*\n""").matches(line) -> {
                    tmp = line.substring(line.indexOf("file=") + 6)
                    result.file = tmp.substring(0, tmp.indexOf("\""))
                }

                Regex(""".*path=.*\n""").matches(line) -> {
                    tmp = line.substring(line.indexOf("path=") + 6)
                    result.path = tmp.substring(0, tmp.indexOf("\""))
                }
            }
            return result
        }
    }
}