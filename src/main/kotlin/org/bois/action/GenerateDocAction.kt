package org.bois.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.ui.tree.Navigatable
import org.jetbrains.annotations.NotNull


class GenerateDocAction : AnAction() {
    override fun update(e: AnActionEvent) {
        // Using the event, evaluate the context, and enable or disable the action.
    }

    override fun actionPerformed(@NotNull event: AnActionEvent) {
        // Using the event, create and show a dialog

        // Using the event, create and show a dialog
        val currentProject: Project? = event.project
        val dlgMsg = StringBuffer(event.presentation.text.toString() + " Selected!")
        val dlgTitle: String = event.presentation.description
        // If an element is selected in the editor, add info about it.
        // If an element is selected in the editor, add info about it.
        val nav: com.intellij.pom.Navigatable? = event.getData(CommonDataKeys.NAVIGATABLE)
        if (nav != null) {
            dlgMsg.append(java.lang.String.format("\nSelected Element: %s", nav.toString()))
        }
        Messages.showMessageDialog(currentProject, dlgMsg.toString(), dlgTitle, Messages.getInformationIcon())
    }
}