package org.bois.htmlGenerator

import org.bois.parser.ParsedClass
import java.io.File

class ClassPageCreator : AbstractPageCreator() {


    fun create(parsedClass: ParsedClass) {
        readTemplate(classPageTemplate())
        parsedClass.namespace?.let { changeClassName(it) }
        changeClassName(parsedClass.name)
        for (cls in parsedClass.parents) {
            addSuperinterface(cls)
        }
        changeDescription(parsedClass.docs.toString())
        for (block in parsedClass.insideBlocks) {
            if (block.className != null) {
                addMethod(block.header, block.className, block.docs.toString())
                addMethodDetails(block.header, block.className, block.docs.toString())
            }
        }

        deleteTemplateThrash()

    }

    fun changeNamespaceName(name: String) {
        val namespaceTitle = body?.getElementById("namespace-title")
        if (namespaceTitle != null) {
            namespaceTitle.append(createLink(name))
        }
    }

    fun changeClassName(name: String) {
        val className = body?.getElementById("class-title")
        if (className != null) {
            className.text(name)
        }
    }

    fun addSuperinterface(name: String) {
        val interfaces = body?.getElementById("superinterfaces")
        if (interfaces != null) {
            interfaces.append(createLink(name,true))
        }
    }

    private fun createLink(name: String, separator: Boolean = false): String {
        var res: String
        if (separator) {
            res = "<a href=\"$name.html\">$name,</a>"
        } else {
            res = "<a href=\"$name.html\">$name</a>"
        }

        return res
    }

    fun addSuperclass(name: String) {
        val superclass = body?.getElementById("superclasses")
        if (superclass != null) {
            superclass.append(createLink(name))
        }
    }

    fun changeDescription(description: String) {
        val descriptionContainer = body?.getElementById("description")
        if (descriptionContainer != null) {
            descriptionContainer.text(description)
        }
    }

    fun addVariable(modifierType: String, variable: String, description: String) {
        val variableFirstRow = body?.getElementById("table-variables-first-row")
        if (variableFirstRow != null) {
            variableFirstRow.after(createRow(modifierType, variable, description))
        }
    }

    fun addMethod(modifierType: String, variable: String, description: String) {
        val variableFirstRow = body?.getElementById("table-methods-first-row")
        if (variableFirstRow != null) {
            variableFirstRow.after(createRow(modifierType, variable, description))
        }
    }

    private fun createRow(modifierType: String, signature: String, description: String): String {
        return "<tr>\n" +
                "    <td class=\"table-light table-secondary-header\">$modifierType</td>\n" +
                "    <td class=\"table-light table-secondary-header\"><a href=\"#\">$signature</a></td>\n" +
                "    <td class=\"table-light table-secondary-header\">$description</td>\n" +
                "</tr>"
    }

    fun addMethodDetails(methodSignature: String, methodName: String, description: String) {
        val methodDetails = body?.getElementById("method-details")
        if (methodDetails != null) {
            methodDetails.after(createMethodDetailsTable(methodSignature, methodName, description))
        }
    }

    fun createMethodDetailsTable(methodSignature: String, methodName: String, description: String): String {
        return "<div class=\"table-responsive\">\n" +
                "    <table class=\"table\">\n" +
                "        <thead>\n" +
                "            <tr>\n" +
                "                <th class=\"table-info\" style=\"background-color: rgb(112,206,226);\">$methodName</th>\n" +
                "            </tr>\n" +
                "        </thead>\n" +
                "        <tbody>\n" +
                "            <tr>\n" +
                "                <td class=\"table-light\">$methodSignature</td>\n" +
                "                <p style=\"font-size: 14px;\">$description</p>" +
                "            </tr>\n" +
                "            <tr></tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "</div>"
    }

    fun classPageTemplate(): String {
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
                "        <div class=\"container-fluid\"><a class=\"navbar-brand\" href=\"./index.html\" style=\"color: rgba(255,255,255,0.9);margin-right: 30px;\"><img src=\"../assets/img/pluginIcon.svg\" width=\"64px\">Rider-DocGenerator</a><button data-toggle=\"collapse\" class=\"navbar-toggler\" data-target=\"#navcol-1\"><span class=\"sr-only\">Toggle navigation</span><span class=\"navbar-toggler-icon\"></span></button>\n" +
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
                "        <div class=\"container\">\n" +
                "            <h6 id=\"namespace-title\" style=\"padding-top: 10px;\">Namespace:&nbsp;<a id=\"template-stub\" href=\"#\">Name</a></h6>\n" +
                "            <h4 id=\"class-title\">Class Name</h4>\n" +
                "            <div></div>\n" +
                "            <div>\n" +
                "                <p id=\"superinterfaces\"><strong>All superinterfaces:&nbsp;</strong><br><a id=\"template-stub\" href=\"#\">Interface1</a></p>\n" +
                "                <p id=\"superclasses\"><strong>All known implementing superclasses:&nbsp;</strong><br><a id=\"template-stub\" href=\"#\">Interface1</a></p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </main>\n" +
                "    <div class=\"container\">\n" +
                "        <p id=\"description\">Class description...</p>\n" +
                "    </div>\n" +
                "    <div class=\"container border rounded-0 border-secondary\" style=\"padding: 20px;background-color: rgba(0,0,0,0.05);margin-bottom: 10px;\">\n" +
                "        <h5>Variables Summary</h5>\n" +
                "        <div class=\"table-responsive\">\n" +
                "            <table class=\"table\">\n" +
                "                <thead>\n" +
                "                    <tr>\n" +
                "                        <th class=\"table-header-name\">All Methods</th>\n" +
                "                    </tr>\n" +
                "                </thead>\n" +
                "                <tbody>\n" +
                "                    <tr id=\"table-variables-first-row\">\n" +
                "                        <td class=\"table-secondary table-secondary-header\">Modifier and Type</td>\n" +
                "                        <td class=\"table-secondary table-secondary-header\">Variable</td>\n" +
                "                        <td class=\"table-secondary table-secondary-header\">Description</td>\n" +
                "                    </tr>\n" +
                "                    <tr id=\"template-stub\">\n" +
                "                        <td class=\"table-light table-secondary-header\">public void</td>\n" +
                "                        <td class=\"table-light table-secondary-header\"><a href=\"#\">Method1()</a></td>\n" +
                "                        <td class=\"table-light table-secondary-header\">Just Method</td>\n" +
                "                    </tr>\n" +
                "                    <tr id=\"template-stub\">\n" +
                "                        <td class=\"table-secondary table-secondary-header\">public void</td>\n" +
                "                        <td class=\"table-secondary table-secondary-header\"><a href=\"#\">Merhod2()</a></td>\n" +
                "                        <td class=\"table-secondary table-secondary-header\">Cell 3</td>\n" +
                "                    </tr>\n" +
                "                    <tr id=\"template-stub\">\n" +
                "                        <td class=\"table-light table-secondary-header\">Cell 1</td>\n" +
                "                        <td class=\"table-light table-secondary-header\"><a href=\"#\">Method3(int s)</a></td>\n" +
                "                        <td class=\"table-light table-secondary-header\">Cell 3</td>\n" +
                "                    </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"container border rounded-0 border-secondary\" style=\"padding: 20px;background-color: rgba(0,0,0,0.05);margin-bottom: 10px;\">\n" +
                "        <h5>Method Summary</h5>\n" +
                "        <div class=\"table-responsive\">\n" +
                "            <table class=\"table\">\n" +
                "                <thead>\n" +
                "                    <tr>\n" +
                "                        <th class=\"table-header-name\">All Methods</th>\n" +
                "                    </tr>\n" +
                "                </thead>\n" +
                "                <tbody>\n" +
                "                    <tr id=\"table-methods-first-row\">\n" +
                "                        <td class=\"table-secondary table-secondary-header\">Modifier and Type</td>\n" +
                "                        <td class=\"table-secondary table-secondary-header\">Method</td>\n" +
                "                        <td class=\"table-secondary table-secondary-header\">Description</td>\n" +
                "                    </tr>\n" +
                "                    <tr id=\"template-stub\">\n" +
                "                        <td class=\"table-light table-secondary-header\">public void</td>\n" +
                "                        <td class=\"table-light table-secondary-header\"><a href=\"#\">Method1()</a></td>\n" +
                "                        <td class=\"table-light table-secondary-header\">Just Method</td>\n" +
                "                    </tr>\n" +
                "                    <tr id=\"template-stub\">\n" +
                "                        <td class=\"table-secondary table-secondary-header\">public void</td>\n" +
                "                        <td class=\"table-secondary table-secondary-header\"><a href=\"#\">Merhod2()</a></td>\n" +
                "                        <td class=\"table-secondary table-secondary-header\">Cell 3</td>\n" +
                "                    </tr>\n" +
                "                    <tr id=\"template-stub\">\n" +
                "                        <td class=\"table-light table-secondary-header\">Cell 1</td>\n" +
                "                        <td class=\"table-light table-secondary-header\"><a href=\"#\">Method3(int s)</a></td>\n" +
                "                        <td class=\"table-light table-secondary-header\">Cell 3</td>\n" +
                "                    </tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div class=\"container border rounded-0 border-secondary\" style=\"padding: 20px;background-color: rgba(0,0,0,0.05);margin-top: 10px;\">\n" +
                "        <h5 id=\"method-details\">Method Details</h5>\n" +
                "        <div class=\"table-responsive\">\n" +
                "            <table class=\"table\">\n" +
                "                <thead>\n" +
                "                    <tr>\n" +
                "                        <th class=\"table-info\" style=\"background-color: rgb(112,206,226);\">Method Name</th>\n" +
                "                    </tr>\n" +
                "                </thead>\n" +
                "                <tbody>\n" +
                "                    <tr>\n" +
                "                        <td class=\"table-light\">void Method1()</td>\n" +
                "                    </tr>\n" +
                "                    <tr></tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "        <div class=\"table-responsive\">\n" +
                "            <table class=\"table\">\n" +
                "                <thead>\n" +
                "                    <tr>\n" +
                "                        <th class=\"table-info\" style=\"background-color: rgb(112,206,226);\">Method Name2</th>\n" +
                "                    </tr>\n" +
                "                </thead>\n" +
                "                <tbody>\n" +
                "                    <tr>\n" +
                "                        <td class=\"table-light\">void Method2()</td>\n" +
                "                    </tr>\n" +
                "                    <tr></tr>\n" +
                "                </tbody>\n" +
                "            </table>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <script src=\"../assets/js/jquery.min.js\"></script>\n" +
                "    <script src=\"../assets/bootstrap/js/bootstrap.min.js\"></script>\n" +
                "</body>\n" +
                "\n" +
                "</html>"
    }

}