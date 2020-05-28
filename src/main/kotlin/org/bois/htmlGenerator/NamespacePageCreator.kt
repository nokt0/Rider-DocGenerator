package org.bois.htmlGenerator

import org.bois.parser.HeaderType
import org.bois.parser.ParsedClass

class NamespacePageCreator(val namespaceName: String) :
    AbstractPageCreator("src/main/resources/html_templates/namespace/namespace.html") {
    private val types = HashSet<HeaderType>()


    private fun createContainTypes(parsedClasses: ArrayList<ParsedClass>) {
        for (item in parsedClasses) {
            if (item.type != null) {
                types.add(item.type)
            }
        }
    }

    fun create(parsedClasses: ArrayList<ParsedClass>) {
        createContainTypes(parsedClasses)
        val firstChild = body.getElementById("heading-container")
        val containers = addTypeContainers(parsedClasses)
        firstChild.after(containers)
        deleteTemplateThrash()
    }

    private fun addTypeContainers(parsedClasses: ArrayList<ParsedClass>): String {
        var containers = ""
        for (type in types) {
            val header = when (type) {
                HeaderType.ABSTRACT_CLASS -> "Abstract Class"
                HeaderType.ENUM -> "Enum"
                HeaderType.CLASS -> "Class"
                HeaderType.INTERFACE -> "Interface"
                HeaderType.STRUCT -> "Struct"
                else -> throw Exception()
            }
            val filtered = parsedClasses.filter { it.type == type }
            val nameDescription = createNameDescription(filtered)
            containers += createTypeContainer(header, nameDescription)
        }
        return containers;
    }

    private fun createClassRow(className: String, description: String): String {
        return "<tr>\n" +
                "    <td style=\"font-size: 14px;\"><a href=\"./class.html\">$className</a></td>\n" +
                "    <td style=\"font-size: 14px;\">$description<br /></td>\n" +
                "</tr>"
    }

    private fun createTypeContainer(header: String, classDescription: ArrayList<Pair<String, String>>): String {
        var classes = ""
        for ((name, description) in classDescription) {
            classes += createClassRow(name, description)
        }

        return "<div class=\"container\">\n" +
                "    <div class=\"table-responsive\">\n" +
                "        <table class=\"table\">\n" +
                "            <thead>\n" +
                "                <tr>\n" +
                "                    <th class=\"table-header-name\">$header Summary</th>\n" +
                "                </tr>\n" +
                "            </thead>\n" +
                "            <tbody>\n" +
                "                <tr>\n" +
                "                    <td class=\"table-secondary table-secondary-header\"><strong>$header</strong></td>\n" +
                "                    <td class=\"table-secondary table-secondary-header\"><strong>Description</strong></td>\n" +
                "                </tr>\n" +
                "                $classes" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "</div>"
    }


}