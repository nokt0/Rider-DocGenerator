package org.bois.htmlGenerator

import org.bois.parser.ParsedClass
import java.io.File


class IndexPageCreator : AbstractPageCreator() {


    fun create(parsedClasses: HashMap<String, ArrayList<ParsedClass>>) {
        readTemplate(indexPageTemplate())
        for ((key, value) in parsedClasses) {
            val description = createNameDescription(value)
            addNamespace(key,description)
        }
        deleteTemplateThrash()
    }

    private fun addNamespace(namespaceName: String, nameDescription: ArrayList<Pair<String, String>>) {
        val table = body?.getElementById("namespaces-table")
        val firstChild = table?.child(0)
        if (firstChild != null) {
            firstChild.after(createNamespaceFragment(namespaceName, nameDescription))
        }
    }

    private fun createClassFragment(className: String, description: String): String {
        return "<tr>\n" +
                "    <td><a href=\"./classes/${className}.html\">${className}</a></td>\n" +
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

    fun indexPageTemplate(): String {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\n" +
                "    <title>Rider-DocGenerator</title>\n" +
                "    <link rel=\"stylesheet\" href=\"assets/bootstrap/css/bootstrap.min.css\">\n" +
                "    <link rel=\"stylesheet\" href=\"assets/css/styles.min.css\">\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <nav class=\"navbar navbar-light navbar-expand-md\" style=\"background-color: #93b7d1;padding-top: 0px;padding-bottom: 0px;\">\n" +
                "        <div class=\"container-fluid\"><a class=\"navbar-brand\" href=\"index.html\" style=\"margin-right: 30px;color: rgba(254,254,254,0.9);\"><img src=\"assets/img/pluginIcon.svg\" width=\"64px\">Rider-DocGenerator</a><button data-toggle=\"collapse\" class=\"navbar-toggler\" data-target=\"#navcol-1\"><span class=\"sr-only\">Toggle navigation</span><span class=\"navbar-toggler-icon\"></span></button>\n" +
                "            <div\n" +
                "                class=\"collapse navbar-collapse\" id=\"navcol-1\">\n" +
                "                <ul class=\"nav navbar-nav\">\n" +
                "                    <li class=\"nav-item\" role=\"presentation\"><a class=\"nav-link active\" href=\"index.html\">Namespaces</a></li>\n" +
                "                    <li class=\"nav-item\" role=\"presentation\"><a class=\"nav-link active\" href=\"#\">Tree</a></li>\n" +
                "                </ul>\n" +
                "        </div>\n" +
                "        </div>\n" +
                "    </nav>\n" +
                "    <div class=\"container\" style=\"padding: 10px;background-color: rgba(0,0,0,0.06);\">\n" +
                "        <div class=\"table-responsive\">\n" +
                "            <table class=\"table\">\n" +
                "                <thead>\n" +
                "                    <tr>\n" +
                "                        <th class=\"table-header-name\">Namespaces\n" +
                "                            <header></header>\n" +
                "                            <header></header>\n" +
                "                        </th>\n" +
                "                    </tr>\n" +
                "                </thead>\n" +
                "                <tbody id=\"namespaces-table\">\n" +
                "                    <tr id=\"template-stub\">\n" +
                "                        <td>\n" +
                "                            <div><a class=\"btn btn-link\" data-toggle=\"collapse\" aria-expanded=\"true\" aria-controls=\"collapse-1\" href=\"#collapse-1\" role=\"button\">Namespace1</a>\n" +
                "                                <div class=\"collapse show\" id=\"collapse-1\">\n" +
                "                                    <div class=\"table-responsive\">\n" +
                "                                        <table class=\"table\">\n" +
                "                                            <thead>\n" +
                "                                                <tr>\n" +
                "                                                    <th class=\"table-secondary table-secondary-header\">Class</th>\n" +
                "                                                    <th class=\"table-secondary table-secondary-header\">Description</th>\n" +
                "                                                </tr>\n" +
                "                                            </thead>\n" +
                "                                            <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td><a href=\"class.html\">Class1</a></td>\n" +
                "                                                    <td>Description for class 1</td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td><a href=\"class.html\">Class2</a></td>\n" +
                "                                                    <td>Description for class 2</td>\n" +
                "                                                </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr id=\"template-stub\">\n" +
                "                        <td>\n" +
                "                            <div><a class=\"btn btn-link\" data-toggle=\"collapse\" aria-expanded=\"false\" aria-controls=\"collapse-2\" href=\"#collapse-2\" role=\"button\">Namespace2</a>\n" +
                "                                <div class=\"collapse\" id=\"collapse-2\">\n" +
                "                                    <p>Collapse content.</p>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                    <tr id=\"template-stub\">\n" +
                "                        <td>\n" +
                "                            <div><a class=\"btn btn-link\" data-toggle=\"collapse\" aria-expanded=\"false\" aria-controls=\"collapse-3\" href=\"#collapse-3\" role=\"button\">Namepace3</a>\n" +
                "                                <div class=\"collapse\" id=\"collapse-3\">\n" +
                "                                    <p>Collapse content.</p>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <main></main>\n" +
                "    <script src=\"assets/js/jquery.min.js\"></script>\n" +
                "    <script src=\"assets/bootstrap/js/bootstrap.min.js\"></script>\n" +
                "</body>\n" +
                "\n" +
                "</html>"
    }
}