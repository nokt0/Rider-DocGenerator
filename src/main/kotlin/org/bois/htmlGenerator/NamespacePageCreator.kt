package org.bois.htmlGenerator

import org.bois.parser.HeaderType
import org.bois.parser.ParsedClass
import java.io.File

class NamespacePageCreator(val namespaceName: String) : AbstractPageCreator(){
    private val types = HashSet<HeaderType>()

    private fun createContainTypes(parsedClasses: ArrayList<ParsedClass>) {
        for (item in parsedClasses) {
            if (item.type != null) {
                types.add(item.type)
            }
        }
    }

    fun create(parsedClasses: ArrayList<ParsedClass>) {
        readTemplate(namespacePageTemplate())
        createContainTypes(parsedClasses)
        val firstChild = body?.getElementById("heading-container")
        val containers = addTypeContainers(parsedClasses)
        if (firstChild != null) {
            firstChild.after(containers)
        }
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

    fun namespacePageTemplate(): String {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\n" +
                "    <title>Rider-DocGenerator</title>\n" +
                "    <link rel=\"stylesheet\" href=\"../assets/bootstrap/css/bootstrap.min.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"../assets/css/styles.min.css\">\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <nav class=\"navbar navbar-light navbar-expand-md\" style=\"background-color: #93b7d1;padding-top: 0px;padding-bottom: 0px;\">\n" +
                "        <div class=\"container-fluid\"><a class=\"navbar-brand text-light\" href=\"./index.html\" style=\"margin-right: 30px;\"><img src=\"../assets/img/pluginIcon.svg\" width=\"64px\">Rider-DocGenerator</a><button data-toggle=\"collapse\" class=\"navbar-toggler\" data-target=\"#navcol-1\"><span class=\"sr-only\">Toggle navigation</span><span class=\"navbar-toggler-icon\"></span></button>\n" +
                "            <div\n" +
                "                class=\"collapse navbar-collapse\" id=\"navcol-1\">\n" +
                "                <ul class=\"nav navbar-nav\">\n" +
                "                    <li class=\"nav-item\" role=\"presentation\"><a class=\"nav-link active\" href=\"./index.html\">Namespaces</a></li>\n" +
                "                    <li class=\"nav-item\" role=\"presentation\"><a class=\"nav-link active\" href=\"#\">Tree</a></li>\n" +
                "                </ul>\n" +
                "        </div>\n" +
                "        </div>\n" +
                "    </nav>\n" +
                "    <main>\n" +
                "        <div class=\"container\" id=\"heading-container\">\n" +
                "            <h1 id=\"namespace-title\">Namespace name</h1>\n" +
                "        </div>\n" +
                "        <div class=\"container\" id=\"template-stub\">\n" +
                "            <div class=\"table-responsive\" style=\"margin-top: 10px;\">\n" +
                "                <table class=\"table\">\n" +
                "                    <thead>\n" +
                "                        <tr>\n" +
                "                            <th class=\"table-header-name\">Interface Summary</th>\n" +
                "                        </tr>\n" +
                "                    </thead>\n" +
                "                    <tbody>\n" +
                "                        <tr>\n" +
                "                            <td class=\"table-secondary table-secondary-header\"><strong>Interface</strong></td>\n" +
                "                            <td class=\"table-secondary table-secondary-header\"><strong>Description</strong></td>\n" +
                "                        </tr>\n" +
                "                        <tr id=\"template-stub\">\n" +
                "                            <td style=\"font-size: 14px;\"><a href=\"class.html\">Interface1</a></td>\n" +
                "                            <td style=\"font-size: 14px;\">Description of Interafce</td>\n" +
                "                        </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"container\" id=\"template-stub\">\n" +
                "            <div class=\"table-responsive\">\n" +
                "                <table class=\"table\">\n" +
                "                    <thead>\n" +
                "                        <tr>\n" +
                "                            <th class=\"table-header-name\">Abstract Class Summary</th>\n" +
                "                        </tr>\n" +
                "                    </thead>\n" +
                "                    <tbody>\n" +
                "                        <tr>\n" +
                "                            <td class=\"table-secondary table-secondary-header\"><strong>Abstract Class</strong></td>\n" +
                "                            <td class=\"table-secondary table-secondary-header\"><strong>Description</strong></td>\n" +
                "                        </tr>\n" +
                "                        <tr id=\"template-stub\">\n" +
                "                            <td style=\"font-size: 14px;\"><a href=\"class.html\">AbstractClass1</a></td>\n" +
                "                            <td style=\"font-size: 14px;\">Description of Abstract class<br></td>\n" +
                "                        </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"container\" id=\"template-stub\">\n" +
                "            <div class=\"table-responsive\">\n" +
                "                <table class=\"table\">\n" +
                "                    <thead>\n" +
                "                        <tr>\n" +
                "                            <th class=\"table-header-name\">Enum Summary</th>\n" +
                "                        </tr>\n" +
                "                    </thead>\n" +
                "                    <tbody>\n" +
                "                        <tr>\n" +
                "                            <td class=\"table-secondary table-secondary-header\"><strong>Enum</strong></td>\n" +
                "                            <td class=\"table-secondary table-secondary-header\"><strong>Description</strong></td>\n" +
                "                        </tr>\n" +
                "                        <tr id=\"template-stub\">\n" +
                "                            <td style=\"font-size: 14px;\"><a href=\"class.html\">Enum</a></td>\n" +
                "                            <td style=\"font-size: 14px;\">Description of Enum<br></td>\n" +
                "                        </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"container\" id=\"template-stub\">\n" +
                "            <div class=\"table-responsive\">\n" +
                "                <table class=\"table\">\n" +
                "                    <thead>\n" +
                "                        <tr>\n" +
                "                            <th class=\"table-header-name\">Struct Summary</th>\n" +
                "                        </tr>\n" +
                "                    </thead>\n" +
                "                    <tbody>\n" +
                "                        <tr>\n" +
                "                            <td class=\"table-secondary table-secondary-header\"><strong>Struct</strong></td>\n" +
                "                            <td class=\"table-secondary table-secondary-header\"><strong>Description</strong></td>\n" +
                "                        </tr>\n" +
                "                        <tr id=\"template-stub\">\n" +
                "                            <td style=\"font-size: 14px;\"><a href=\"class.html\">Struct</a></td>\n" +
                "                            <td style=\"font-size: 14px;\">Description of Struct<br></td>\n" +
                "                        </tr>\n" +
                "                    </tbody>\n" +
                "                </table>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </main>\n" +
                "    <script src=\"../assets/js/jquery.min.js\"></script>\n" +
                "    <script src=\"../assets/bootstrap/js/bootstrap.min.js\"></script>\n" +
                "</body>\n" +
                "\n" +
                "</html>"
    }

}