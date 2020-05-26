package org.bois.htmlGenerator

import org.bois.parser.ParsedClass
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.io.File

abstract class AbstractPageCreator(templatePath:String) {

    protected val page: Document
    protected val body: Element
    protected var counter = 1
    protected val deleteId = "template-stub"

    init {
        val htmlTemplate = File(templatePath).readText(Charsets.UTF_8)
        val doc = Jsoup.parse(htmlTemplate)
        page = doc
        body = doc.body()
    }

    fun htmlPage(): String {
        return page.toString()
    }

    protected fun createNameDescription(parsedClasses: List<ParsedClass>): ArrayList<Pair<String, String>> {
        val classDescription = ArrayList<Pair<String, String>>()
        for (item in parsedClasses) {
            classDescription.add(Pair(item.name, item.docs.toString()))
        }
        return classDescription
    }

    protected fun deleteTemplateThrash() {
        val body = page.body()
        val toDelete = body.select("#$deleteId")
        for (item in toDelete) {
            item.remove()
        }
    }

}