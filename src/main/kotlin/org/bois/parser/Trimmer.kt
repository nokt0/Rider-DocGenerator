package org.bois.parser

import com.intellij.internal.statistic.utils.addPluginInfoTo
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Trimmer {
    companion object {
        fun trimLine(doc: ArrayList<String>): HashMap<String, Tag> {
            var tagsStruct: TagsStruct = TagsStruct()
            var tStruct: HashMap<String, Tag> = HashMap()
//            var tmp = concatStrings(doc)
            doc.forEach {
                println(it)
                when {
                    Regex(""".*<c>.\n*""").matches(it) ->
                        tStruct["c"] = splitForParameters(it)
                    Regex(""".*<code>.\n*""").matches(it) ->
                        tStruct["code"] = splitForParameters(it)
                    Regex(""".*<example>.*\n""").matches(it) ->
                        tStruct["example"] = splitForParameters(it)
                    Regex(""".*<exception.*>.*\n""").matches(it) ->
                        tStruct["exception"] = splitForParameters(it)
                    Regex(""".*<include.*/>.*\n""").matches(it) ->
                        tStruct["include"] = splitForParameters(it)
                    Regex(""".*<list.*>.*\n""").matches(it) ->
                        tStruct["list"] = splitForParameters(it)
                    Regex(""".*<para>.*\n""").matches(it) ->
                        tStruct["para"] = splitForParameters(it)
                    Regex(""".*<param.*>.*\n""").matches(it) ->
                        tStruct["param"] = splitForParameters(it)
                    Regex(""".*<paramref.*/>.*\n""").matches(it) ->
                        tStruct["paramref"] = splitForParameters(it)
                    Regex(""".*<permission.*>.*\n""").matches(it) ->
                        tStruct["permission"] = splitForParameters(it)
                    Regex(""".*<remarks>.*\n""").matches(it) ->
                        tStruct["remarks"] = splitForParameters(it)
                    Regex(""".*<returns>.*\n""").matches(it) ->
                        tStruct["returns"] = splitForParameters(it)
                    Regex(""".*<inheritdoc.*/>.*\n""").matches(it) ->
                        tStruct["inheritdoc"] = splitForParameters(it)
                    Regex(""".*<see.*/>.*\n""").matches(it) ->
                        tStruct["see"] = splitForParameters(it)
                    Regex(""".*<seealso.*>.*\n""").matches(it) ->
                        tStruct["seealso"] = splitForParameters(it)
                    Regex(""".*<summary>.*\n""").matches(it) ->
                        tStruct["summary"] = splitForParameters(it)
                    Regex(""".*<typeparam.*>.*\n""").matches(it) ->
                        tStruct["typeparam"] = splitForParameters(it)
                    Regex(""".*<typeparamref.*/>.*\n""").matches(it) ->
                        tStruct["typeparamref"] = splitForParameters(it)
                    Regex(""".*<value>.*\n""").matches(it) ->
                        tStruct["value"]= splitForParameters(it)
                }
            }
            return tStruct
        }

        fun concatStrings(doc: ArrayList<String>): ArrayList<String> {
            var templateList: ArrayList<String> = ArrayList()
            var stack: Stack<String> = Stack()
            var closure: String = "closure"
            doc.forEach {

                if (it.indexOf("</") != -1)
                    closure = it.substring(it.indexOf("</") + 2, it.lastIndexOf(">") - 1)

                if (it.indexOf("<") != -1)
                    stack.push(it.substring(it.indexOf("<") + 1, it.indexOf(">") - 1))

                if (stack.isEmpty()) {
                    templateList.add(it)
                }
                if (templateList.isEmpty())
                    templateList.add(it)
                else
                    templateList.last().plus(it)

                if (Regex(""".*<[$closure].*\n""").matches(stack.peek())) {
                    stack.pop()
                }


            }

            return templateList
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