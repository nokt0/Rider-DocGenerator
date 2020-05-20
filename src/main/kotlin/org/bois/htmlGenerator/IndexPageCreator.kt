package org.bois.htmlGenerator

import org.bois.parser.ParsedClass
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File


class IndexPageCreator {

    private val page: Document
    private var counter = 1
    private val deleteId = "template-stub"

    init {
        val htmlTemplate = File("src/main/resources/html_templates/index/index.html").readText(Charsets.UTF_8)
        val doc = Jsoup.parse(htmlTemplate)
        page = doc
    }

    fun create(parsedClasses: HashMap<String, ArrayList<ParsedClass>>) {
        for ((key, value) in parsedClasses) {
            val description = createNameDescription(value)
            addNamespace(key,description)
        }
        deleteTemplateThrash()
    }

    fun htmlPage(): String {
        return page.toString()
    }

    private fun createNameDescription(parsedClasses: ArrayList<ParsedClass>): ArrayList<Pair<String, String>> {
        val classDescription = ArrayList<Pair<String, String>>()
        for (item in parsedClasses) {
            classDescription.add(Pair(item.name, item.docs.toString()))
        }
        return classDescription
    }

    private fun addNamespace(namespaceName: String, nameDescription: ArrayList<Pair<String, String>>) {
        val body = page.body()
        val table = body.getElementById("namespaces-table")
        val firstChild = table.child(0)
        firstChild.after(createNamespaceFragment(namespaceName, nameDescription))
    }

    private fun createClassFragment(className: String, description: String): String {
        return "<tr>\n" +
                "    <td><a href=\"./${className}.html\">${className}</a></td>\n" +
                "    <td>${description}</td>\n" +
                "</tr>"
    }

    private fun createNamespaceFragment(namespaceName: String, nameDescription: List<Pair<String, String>>): String {
        var classes = ""

        for ((className, description) in nameDescription) {
            classes += createClassFragment(className, description) + System.lineSeparator()
        }

        val fragment =
            "<tr>\n" +
                    "    <td>\n" +
                    "        <div><a class=\"btn btn-link\" data-toggle=\"collapse\" aria-expanded=\"true\" aria-controls=\"collapse-1\"\n" +
                    "                href=\"#collapse-$counter\" role=\"button\">$namespaceName</a>\n" +
                    "            <div class=\"collapse show\" id=\"collapse-$counter\">\n" +
                    "                <div class=\"table-responsive\">\n" +
                    "                    <table class=\"table\">\n" +
                    "                        <thead>\n" +
                    "                        <tr>\n" +
                    "                            <th class=\"table-secondary table-secondary-header\">Class</th>\n" +
                    "                            <th class=\"table-secondary table-secondary-header\">Description</th>\n" +
                    "                        </tr>\n" +
                    "                        </thead>\n" +
                    "                        <tbody>\n" +
                    "                        $classes" +
                    "                        </tbody>\n" +
                    "                    </table>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </td>\n" +
                    "</tr>"

        counter++
        return fragment
    }

    private fun deleteTemplateThrash(){
        val body = page.body()
        val toDelete = body.select("#$deleteId")
        for(item in toDelete){
            item.remove()
        }

    }
}