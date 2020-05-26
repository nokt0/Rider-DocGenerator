package org.bois.htmlGenerator

import org.bois.parser.ParsedClass

class ClassPageCreator : AbstractPageCreator("src/main/resources/class/class.html") {

    fun create(parsedClass: ParsedClass) {

    }

    fun changeNamespaceName(name: String) {
        val namespaceTitle = body.getElementById("namespace-title")
        namespaceTitle.append(createLink(name))
    }

    fun changeClassName(name: String) {
        val className = body.getElementById("class-title")
        className.text(name)
    }

    fun addSuperinterface(name: String) {
        val interfaces = body.getElementById("superinterfaces")
        interfaces.append(createLink(name))
    }

    private fun createLink(name: String): String {
        return "<a href=\"/classes/$name.html\">$name</a>"
    }

    fun addSuperclass(name: String) {
        val superclass = body.getElementById("superclasses")
        superclass.append(createLink(name))
    }

    fun changeDescription(description: String) {
        val descriptionContainer = body.getElementById("description")
        descriptionContainer.text(description)
    }

    fun addVariable(modifierType: String, variable: String, description: String) {
        val variableFirstRow = body.getElementById("table-variables-first-row")
        variableFirstRow.after(createRow(modifierType, variable, description))
    }

    fun addMethod(modifierType: String, variable: String, description: String) {
        val variableFirstRow = body.getElementById("table-methods-first-row")
        variableFirstRow.after(createRow(modifierType, variable, description))
    }

    private fun createRow(modifierType: String, signature: String, description: String): String {
        return "<tr>\n" +
                "    <td class=\"table-light table-secondary-header\">$modifierType</td>\n" +
                "    <td class=\"table-light table-secondary-header\"><a href=\"#\">$signature</a></td>\n" +
                "    <td class=\"table-light table-secondary-header\">$description</td>\n" +
                "</tr>"
    }

    fun addMethodDetails(methodSignature: String, methodName: String, description: String) {
        val methodDetails = body.getElementById("method-details")
        methodDetails.after(createMethodDetailsTable(methodSignature, methodName, description))
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


}