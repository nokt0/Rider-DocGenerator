package org.bois.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import org.bois.ui.PathDialogWrapper


public class GenerateDocAction : AnAction() {

    override fun update(e: AnActionEvent) {

    }

    override fun actionPerformed(event: AnActionEvent) {
        val currentProject: Project? = event.project
        val  wrapper = PathDialogWrapper(currentProject?.basePath)
        wrapper.showAndGet()
    }
}