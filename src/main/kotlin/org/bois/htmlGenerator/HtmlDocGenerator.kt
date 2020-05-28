package org.bois.htmlGenerator

import org.bois.parser.ParsedClass
import java.io.File
import org.bois.Namespace
import java.nio.file.StandardCopyOption


class HtmlDocGenerator(override val outPath: File) : IHtmlGenerator {

    private val indexPageCreator = IndexPageCreator()
    private val namespaces = ArrayList<String>()
    private val classes = ArrayList<String>()

    override fun createDocumentation(parsedClasses: HashMap<Namespace, ArrayList<ParsedClass>>) {
        val indexPage = indexPageCreator.htmlPage()
        indexPageCreator.create(parsedClasses)

        for ((key, value) in parsedClasses) {
            val namespacePageCreator = NamespacePageCreator(key)
            namespacePageCreator.create(value)
            namespaces.add(namespacePageCreator.htmlPage())
            for (parsedCls in value) {
                val classPageCreator = ClassPageCreator()
                classPageCreator.create(parsedCls)
                classes.add(classPageCreator.htmlPage())
            }
        }
    }



}