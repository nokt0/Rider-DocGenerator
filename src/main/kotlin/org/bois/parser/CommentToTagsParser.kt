package org.bois.parser

import com.jetbrains.rd.util.string.print
import java.io.BufferedReader
import java.io.LineNumberReader
import java.util.*
import kotlin.collections.ArrayList

class CommentToTagsParser(inputReader: BufferedReader) {
    val tags = TagsStruct()
    var reader: LineNumberReader = LineNumberReader(inputReader)
    var tree: InheritTree = InheritTree()

    fun createTree(line: String, parents: ArrayList<String>) {
        var className: String? = null
        if (line.indexOf("class") != -1) {
            var endIndex = 0
            if (line.indexOf(":") != -1)
                endIndex = line.indexOf(":") - 1
            else
                endIndex = line.length
            className = line.subSequence(line.indexOf("class") + 6, endIndex).trim().toString()

            if (line.indexOf(":") != -1) {
                var parentPart = line.substring(line.indexOf(":") + 1)
                parents.addAll(parentPart.trim().split("[,]+[/s]*"))
            }

            if (className != null) {
                if (tree.parents.containsKey(className)) {
                    parents.addAll(tree.parents.get(className)!!)
                    tree.parents.remove(className)
                    tree.parents[className] = parents
                } else
                    tree.parents[className] = parents

                parents.forEach {

                    if (tree.children.containsKey(it))
                        tree.children.get(it)?.add(className)
                    else {
                        var newChildrenArray = ArrayList<String>()
                        newChildrenArray.add(className)
                        tree.children[it] = newChildrenArray
                    }
                }
            }
        }
    }

    fun createBlocks(): ArrayList<ParsedBlockData> {
        var startDocComment = false
        val commentBlocks = ArrayList<ParsedBlockData>()
        var block = ArrayList<String>()
        var parents = ArrayList<String>()

        do {
            val line = reader.readLine()
            parents.clear()
            if (line != null && line.indexOf("///") != -1) {
                if (!startDocComment) {
                    startDocComment = true
                }
                block.add(line.trim() + System.lineSeparator())
            } else if (startDocComment) {
                val parsedBlock = ParsedBlockData(block, line.trim())
                createTree(line, parents)
                commentBlocks.add(parsedBlock)
                block = ArrayList()
                startDocComment = false
            }
        } while (line != null)

        return commentBlocks
    }

    fun treePrint() {
        println("(((Parents hashMap)))")
        tree.parents.forEach {
            println("Child: " + it.key + " Parents: " + it.value.toString())
        }
        println("(((Children hashMap)))")
        tree.children.forEach {
            println("Parent: " + it.key + " Children: " + it.value.toString())
        }
    }

//    fun createTree(inputReader: BufferedReader) {
//        var startDocComment = false
//        val reader = LineNumberReader(inputReader)
//        val commentBlocks = ArrayList<ParsedBlockData>()
//        do {
//            val line = reader.readLine()
//            var bracketsCount = 0
//            var type: HeaderType
//            if (line.indexOf("{") != -1) {
//                bracketsCount++
//            }
//            if (line.indexOf("}") != -1) {
//                bracketsCount--
//            }
//            HeaderType.values().forEach { it ->
//                    if (line.indexOf(it.toString()) != -1) {
//                        type = it
//                    }
//                }
//
//
//            if (line != null && line.indexOf("///") != -1) {
//                if (!startDocComment) {
//                    startDocComment = true
//                }
//                block.add(line.trim() + System.lineSeparator())
//            } else if (startDocComment) {
//                val parsedBlock = ParsedBlockData(block, line.trim())
//                commentBlocks.add(parsedBlock)
//                block = ArrayList()
//                startDocComment = false
//            }
//        } while (line != null)
//
//
//    }

    fun parse(comment: StringBuffer) {
        if (comment.indexOf("<c>") != -1 && comment.lastIndexOf("<c>") != -1)
            tags.c = comment.substring(comment.indexOf("<c>") + "<c>".length, comment.lastIndexOf("<c>"))
        if (comment.indexOf("<code>") != -1 && comment.lastIndexOf("<code>") != -1)
            tags.code = comment.substring(comment.indexOf("<code>" + "<code>".length), comment.lastIndexOf("<code>"))
        if (comment.indexOf("<exception>") != -1 && comment.lastIndexOf("<exception>") != -1)
            tags.exception = comment.substring(
                comment.indexOf("<exception>" + "<exception>".length),
                comment.lastIndexOf("<exception>")
            )
        if (comment.indexOf("<example>") != -1 && comment.lastIndexOf("<example>") != -1)
            tags.example =
                comment.substring(comment.indexOf("<example>") + "<example>".length, comment.lastIndexOf("<example>"))
        if (comment.indexOf("<include>") != -1 && comment.lastIndexOf("<include>") != -1)
            tags.include =
                comment.substring(comment.indexOf("<include>") + "<include>".length, comment.lastIndexOf("<include>"))
        if (comment.indexOf("<list>") != -1 && comment.lastIndexOf("<list>") != -1)
            tags.list = comment.substring(comment.indexOf("<list>") + "<list>".length, comment.lastIndexOf("<list>"))
        if (comment.indexOf("<para>") != -1 && comment.lastIndexOf("<para>") != -1)
            tags.para = comment.substring(comment.indexOf("<para>") + "<para>".length, comment.lastIndexOf("<para>"))
        if (comment.indexOf("<param>") != -1 && comment.lastIndexOf("<param>") != -1)
            tags.param =
                comment.substring(comment.indexOf("<param>") + "<param>".length, comment.lastIndexOf("<param>"))
        if (comment.indexOf("<paramref>") != -1 && comment.lastIndexOf("<paramref>") != -1)
            tags.paramref = comment.substring(
                comment.indexOf("<paramref>") + "<paramref>".length,
                comment.lastIndexOf("<paramref>")
            )
        if (comment.indexOf("<permission>") != -1 && comment.lastIndexOf("<permission>") != -1)
            tags.permission = comment.substring(
                comment.indexOf("<permission>") + "<permission>".length,
                comment.lastIndexOf("<permission>")
            )
        if (comment.indexOf("<inheritdoc>") != -1 && comment.lastIndexOf("<inheritdoc>") != -1)
            tags.inheritdoc = comment.substring(
                comment.indexOf("<inheritdoc>") + "<inheritdoc>".length,
                comment.lastIndexOf("<inheritdoc>")
            )
        if (comment.indexOf("<see>") != -1 && comment.lastIndexOf("<see>") != -1)
            tags.see = comment.substring(comment.indexOf("<see>") + "<see>".length, comment.lastIndexOf("<see>"))
        if (comment.indexOf("<seealso>") != -1 && comment.lastIndexOf("<seealso>") != -1)
            tags.seealso =
                comment.substring(comment.indexOf("<seealso>") + "<seealso>".length, comment.lastIndexOf("<seealso>"))
        if (comment.indexOf("<summary>") != -1 && comment.lastIndexOf("<summary>") != -1)
            tags.summary =
                comment.substring(comment.indexOf("<summary>") + "<summary>".length, comment.lastIndexOf("<summary>"))
        if (comment.indexOf("<typeparam>") != -1 && comment.lastIndexOf("<typeparam>") != -1)
            tags.typeparam = comment.substring(
                comment.indexOf("<typeparam>") + "<typeparam>".length,
                comment.lastIndexOf("<typeparam>")
            )
        if (comment.indexOf("<typeparamref>") != -1 && comment.lastIndexOf("<typeparamref>") != -1)
            tags.typeparamref =
                comment.substring(
                    comment.indexOf("<typeparamref>") + "<typeparamref>".length,
                    comment.lastIndexOf("<typeparamref>")
                )
        if (comment.indexOf("<returns>") != -1 && comment.lastIndexOf("<returns>") != -1)
            tags.returns =
                comment.substring(comment.indexOf("<returns>") + "<returns>".length, comment.lastIndexOf("<returns>"))
        if (comment.indexOf("<value>") != -1 && comment.lastIndexOf("<value>") != -1)
            tags.value =
                comment.substring(comment.indexOf("<value>") + "<value>".length, comment.lastIndexOf("<value>"))
    }

    fun createBlocks(sourceCode: StringBuffer) {
        val regex = Regex("///.*$");

    }
}