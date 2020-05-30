package org.bois.htmlGenerator

import org.bois.parser.ParsedClass
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.io.File

abstract class AbstractPageCreator() : IPageCreator {

    override var page: Document? = null
    protected var body: Element? = null
    protected var counter = 1
    protected val deleteId = "template-stub"
    protected var templatePath: File? = null

    fun readTemplate(template: String) {
        val doc = Jsoup.parse(template)
        page = doc
        body = doc.body()
    }

    override fun htmlPage(): String {
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
        if (body != null) {
            val toDelete = body!!.select("#$deleteId")
            for (item in toDelete) {
                item.remove()
            }
        }
    }

}