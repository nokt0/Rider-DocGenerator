package org.bois.htmlGenerator

import org.bois.parser.ParsedClass
import java.io.File
import org.bois.Namespace
import org.bois.FileName


class HtmlDocGenerator : IHtmlGenerator {

    private val indexPageCreator = IndexPageCreator()
    val namespaces = HashMap<String, String>()
    val classes = HashMap<String, String>()
    var indexPage: String = ""

    override fun createDocumentation(parsedClasses: HashMap<Namespace, ArrayList<ParsedClass>>) {
        indexPageCreator.create(parsedClasses)
        indexPage = indexPageCreator.htmlPage()

        for ((key, value) in parsedClasses) {
            val namespacePageCreator = NamespacePageCreator(key)
            namespacePageCreator.create(value)
            namespaces[key] = namespacePageCreator.htmlPage()

            for (parsedCls in value) {
                val classPageCreator = ClassPageCreator()
                classPageCreator.create(parsedCls)
                classes[parsedCls.name] = classPageCreator.htmlPage()
            }
        }
    }


}